package util;
import java.sql.*;
public class DBConnector {
    /* Static Data - change to your user credentials */
    private static final String user = "codespace";												// username
    private static final String password = "1107";											// password
    private static final String jdbcURL = "jdbc:mariadb://localhost:3306/wolfmedia";	// url

    /* Instance Fields (none at the moment, everything is static) */


    /**
     * Function used for establishing a connection with MariaDB.
     */
    static Connection connect() throws ClassNotFoundException, SQLException {
        Class.forName("org.mariadb.jdbc.Driver");
        return DriverManager.getConnection(jdbcURL, user, password);
    }

}
