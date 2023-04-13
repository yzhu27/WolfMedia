package util;

import java.sql.*;

import config.Init;

/**
 * A utility class for executing SQL queries and updates.
 */
public class queryExecuter {

    /**
     * Executes an SQL query or update, and prints the results to the console or to
     * a table, depending on the query type.
     *
     * @param sql a String representing the SQL query or update to execute.
     * @return a String indicating the result of the execution.
     */
    public static String execute(String sql) {
        boolean isQuery = false;
        if (sql.substring(0, 6).equalsIgnoreCase("select")) {
            isQuery = true;
        }
        try (Connection connection = DBConnector.connect()) {

            try (Statement statement = connection.createStatement()) {
                if (isQuery) {
                    ResultSet resultSet = statement.executeQuery(sql);
                    if (resultSet != null) {
                        DBTablePrinter.printResultSet(resultSet);
                    }
                } else {
                    statement.executeUpdate(sql);
                    // print new table after update info
                    for (int i = 0; i < Init.tables.length; i++) {
                        if (sql.contains(Init.tables[i])) {
                            DBTablePrinter.printTable(Init.tables[i]);
                        }
                    }
                }
            } catch (SQLException error) {
                return "Error: Problem Executing SQL Query";
            } finally {
                if (connection != null) {
                    try {
                        connection.close();
                    } catch (SQLException error) {
                        return "Close JDBC connection failed.\n" + error;
                    }
                }
            }
        } catch (ClassNotFoundException | SQLException error) {
            return "Unable to Connect Database\n" + error;
        }
        return "success";
    }
}
