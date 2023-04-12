package config;

/**
 * We use this class to connect to the database and execute SQL statements and transactions.
 */

import java.sql.*;

import util.*;
// import Result;

public class Connect {

    /* Static Data - change to your user credentials */
    private static final String user = "codespace";												// username
    private static final String password = "1107";											// password
    private static final String jdbcURL = "jdbc:mariadb://localhost:3306/wolfmedia";	// url

    /* Instance Fields (none at the moment, everything is static) */


    /**
     * Function used for establishing a connection with MariaDB.
     */
    private static Connection connect() throws ClassNotFoundException, SQLException {
        Class.forName("org.mariadb.jdbc.Driver");
        return DriverManager.getConnection(jdbcURL, user, password);
    }


    /**
     * We execute SQL queries and updates as a single transaction.
     *
     * Before executing all the statements in the transaction, the connection's
     * auto commit is set to "false". This prevents all executed queries and
     * updates from committing until after we commit the transaction. This is
     * done after all statements are executed. Furthermore, should any of these
     * statements or the commit fail, we rollback the transaction after catching
     * the corresponding SQLException. Lastly, we set the auto commit back to
     * "true" before the connection is automatically closed at the end of the
     * function.
     *
     * Note that there is no explicit "connection.close()" call, since the connection
     * is automatically closed once the "try/except" block completes. This
     * function also prints the entire result set that is returned by the query
     * directly to the console.
     */
    public static Result executeTransaction(Transaction transaction) {

        try (Connection connection = connect()) {

            /* set auto commit to false - i.e. run statements as single transaction */
            connection.setAutoCommit(false);

            try (Statement statement = connection.createStatement()) {

                /* execute each query and update statement in the transaction */
                for (Transaction.TransactionOp operation : transaction.statements) {
                    if (operation.type == Transaction.StatementType.QUERY) {
                        ResultSet resultSet = statement.executeQuery(operation.sql);
                        if (resultSet != null) {
                            // printResultSet(resultSet);
                            DBTablePrinter.printResultSet(resultSet);
                        }
                    } else if (operation.type == Transaction.StatementType.UPDATE) {
                        statement.executeUpdate(operation.sql);
                    }
                }

                /* commit the executed statements */
                connection.commit();

            } catch (SQLException error) {

                /* rollback the transaction if anything should fail to commit */
                connection.rollback();

                return new Result(false, "Problem Executing Transaction");

            } finally {

                /* set auto commit back to true (just in case, even though connection closes automatically) */
                connection.setAutoCommit(true);

            }

        } catch (ClassNotFoundException | SQLException e) {

            String errorMsg = "Unable to Connect Using jdbcURL: " + jdbcURL;
            return new Result(false, errorMsg);

        }

        return new Result(true, "");
    }


    /**
     * Function used for executing a single SQL query on the database.
     *
     * Note that there is no explicit "connection.close()" call, since the connection
     * is automatically closed once the "try/except" block completes. This
     * function also prints the entire result set that is returned by the query
     * directly to the console.
     */
    public static Result executeQuery(String sql) {

        try (Connection connection = connect()) {

            try (Statement statement = connection.createStatement()) {
                ResultSet resultSet = statement.executeQuery(sql);
                if (resultSet != null) {
                    // just print results to console
                    // printResultSet(resultSet);
                    DBTablePrinter.printResultSet(resultSet);
                }
            } catch (SQLException error) {
                return new Result(false, "Problem Executing SQL Query");
            }

        } catch (ClassNotFoundException | SQLException e) {

            String errorMsg = "Unable to Connect Using jdbcURL: " + jdbcURL;
            return new Result(false, errorMsg);

        }

        return new Result(true, "");
    }

    public static Result execute(String sql) {
        boolean isQuery = false;
        if (sql.substring(0, 6).equalsIgnoreCase("select")) {
            isQuery = true;
        }

        try (Connection connection = connect()) {

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

            String errorMsg = "Unable to Connect Using jdbcURL: " + jdbcURL;
            return new Result(false, errorMsg);

        }

        return new Result(true, "");
    }


    /**
     * Function used for executing a single SQL update statement on the database.
     *
     * Note that there is no explicit "connection.close()" call, since the
     * connection is automatically closed once the "try/except" block completes.
     */
    public static Result executeUpdate(String sql) {

        try (Connection connection = connect()) {

            try (Statement statement = connection.createStatement()) {
                statement.executeUpdate(sql);
            } catch (SQLException error) {
                return new Result(false, "Problem Executing SQL Update");
            }

        } catch (ClassNotFoundException | SQLException e) {

            String errorMsg = "Unable to Connect Using jdbcURL: " + jdbcURL;
            return new Result(false, errorMsg);

        }

        return new Result(true, "");
    }
}
