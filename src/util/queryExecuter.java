package util;

import java.sql.*;

import config.Result;

public class queryExecuter {
    public static Result execute(String sql) {
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
                }
            } catch (SQLException error) {
                return new Result(false, "Problem Executing SQL Query");
            }

        } catch (ClassNotFoundException | SQLException e) {

            String errorMsg = "Unable to Connect Database";
            return new Result(false, errorMsg);

        }

        return new Result(true, "");
    }

    
}
