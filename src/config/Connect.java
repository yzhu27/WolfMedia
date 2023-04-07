package config;

/**
 * We use this class to connect to the database and execute SQL statements and transactions.
 */

import java.sql.*;
import java.lang.StringBuilder;
// import Result;

public class Connect {

    /* Static Data - change to your user credentials */
    private static final String user = "mwang39";												// username
    private static final String password = "200476023";											// password
    private static final String jdbcURL = "jdbc:mariadb://classdb2.csc.ncsu.edu:3306/"+user;	// url

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
                            printResultSet(resultSet);
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
                    printResultSet(resultSet);
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


    /**
     * Function used to neatly print the data in a ResultSet returned from the
     * database. This is used in the "executeQuery" method above.
     *
     * To accomplish this, the function scans through the columns and records
     * of the ResultSet to determine the maximum length required for each field.
     * It then uses these values to neatly print the field names and each record
     * neatly in a table like fashion that is output directly to the console.
     */
    public static void printResultSet(ResultSet rs) throws SQLException {
        StringBuilder builder = new StringBuilder();

        ResultSetMetaData rsmd = rs.getMetaData();
        int columnsNumber = rsmd.getColumnCount();

        /* get max length of each column */
        int[] columnSizes = new int[columnsNumber];
        for (int i = 1; i <= columnsNumber; i++) {
            columnSizes[i-1] = rsmd.getColumnName(i).length();
        }
        while (rs.next()) {
            for (int i = 1; i <= columnsNumber; i++) {
                int valueLength = (rs.getString(i) == null) ? 0 : rs.getString(i).length();
                if (valueLength > columnSizes[i-1]) {
                    columnSizes[i-1] = valueLength;
                }
            }
        }

        /* move back to front of result set */
        rs.beforeFirst();

        /* build string that will be printed */
        builder.append('\n');
        getDividerString(columnSizes, builder);
        getHeaderString(columnSizes, rsmd, builder);
        getDividerString(columnSizes, builder);
        while (rs.next()) {
            getRowString(columnSizes, rs, builder);
        }
        getDividerString(columnSizes, builder);
        builder.append('\n');

        /* print the string */
        System.out.print(builder.toString());
    }


    /**
     * Function used in "printResultSet" above to print a divider line.
     */
    private static void getDividerString(int[] columnSizes, StringBuilder builder) {
        for (int i=0; i<columnSizes.length; i++) {
            builder.append("|-");
            for (int j=0; j<columnSizes[i]; j++) {
                builder.append('-');
            }
            builder.append('-');
        }
        builder.append("|\n");
    }


    /**
     * Function used in "printResultSet" the field names/headers.
     */
    private static void getHeaderString(int[] columnSizes, ResultSetMetaData rsmd, StringBuilder builder) throws SQLException {
        for (int i=0; i<columnSizes.length; i++) {
            String formatter = String.format("| %%%ds ", columnSizes[i]);
            builder.append(String.format(formatter, rsmd.getColumnName(i+1)));
        }
        builder.append("|\n");
    }


    /**
     * Function used in "printResultSet" above to print each record on a newline.
     */
    private static void getRowString(int[] columnSizes, ResultSet rs, StringBuilder builder) throws SQLException {
        for (int i=0; i<columnSizes.length; i++) {
            String formatter = String.format("| %%%ds ", columnSizes[i]);
            builder.append(String.format(formatter, rs.getString(i+1)));
        }
        builder.append("|\n");
    }
}
