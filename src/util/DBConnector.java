package util;
import java.sql.*;

/**
 * A utility class for connecting to a MariaDB database.
 */
public class DBConnector {
    /* Static Data - change to your user credentials */
    private static final String user = "codespace"; // username
    private static final String password = "1107"; // password
    private static final String jdbcURL = "jdbc:mariadb://localhost:3306/wolfmedia"; // url

    /**
     * Establishes a connection with a MariaDB database.
     *
     * @return a Connection object representing the connection to the database.
     * @throws ClassNotFoundException if the MariaDB JDBC driver class cannot be found.
     * @throws SQLException if an error occurs while connecting to the database.
     */
    static Connection connect() throws ClassNotFoundException, SQLException {
        Class.forName("org.mariadb.jdbc.Driver");
        return DriverManager.getConnection(jdbcURL, user, password);
    }

}
