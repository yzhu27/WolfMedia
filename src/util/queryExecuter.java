package util;

import java.sql.*;

import config.Init;


public class queryExecuter {
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
                        // just print results to console
                        // printResultSet(resultSet);
                        DBTablePrinter.printResultSet(resultSet);
                    }
                } else {
                    statement.executeUpdate(sql);

                    for (int i=0; i<Init.tables.length; i++) {
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
                        return "Close JDBC connection failed.\n"+error;
                    }
                }
            }

        } catch (ClassNotFoundException | SQLException error) {
            return "Unable to Connect Database\n"+error;

        }

        return "success";
    }

    
}
