package process;

import java.util.Scanner;
import java.sql.*;

import util.*;

/**
 *  This Class used for executing the updateAlbumInfo API operation by updating the Albums Table.
 */


public class updateAlbumInfo {
    private static final String user = "mwang39";												// username
    private static final String password = "200476023";											// password
    private static final String jdbcURL = "jdbc:mariadb://classdb2.csc.ncsu.edu:3306/"+user;

    private static Connection connect() throws ClassNotFoundException, SQLException {
        Class.forName("org.mariadb.jdbc.Driver");
        return DriverManager.getConnection(jdbcURL, user, password);
    }



    public static String run(Scanner reader) throws SQLException{
        System.out.println("+------------------------------------+");
        System.out.println("|          Album Details            |");
        System.out.println("+------------------------------------+");
        System.out.println("");

        DBTablePrinter.printTable("Albums");

        System.out.println("+------------------------------------+");
        System.out.println("| Please Submit the Following Inputs |");
        System.out.println("+------------------------------------+");
        System.out.println("");

        System.out.println("Album ID: ");
        int albumID = reader.nextInt();
        reader.nextLine();

        System.out.println("New AlName: ");
        String newName = reader.nextLine();

        System.out.println("New ReleaseYear: ");
        int newYear = reader.nextInt();
        reader.nextLine();

        System.out.println("New Edition: ");
        String newEdition = reader.nextLine();

        return execute(albumID, newName, newYear, newEdition);

    }
    public static String execute(int ID, String newName, int newYear, String newEdition) {

        /* Execute Transaction via Connect and return result */
        try (Connection connection = connect()) {

            /* set auto commit to false - i.e. run statements as single transaction */
            connection.setAutoCommit(false);

            try (Statement statement = connection.createStatement()) {
                /* execute each query and update statement in the transaction */

                /* Statement 1 in Transaction (Update Alname attribute) */
                /* ------------------------------------------------------------------ */
                String sql =
                        "UPDATE Albums SET Alname = '%s' WHERE AlbumID = %d;";

                sql = String.format(sql, newName, ID);
                statement.executeUpdate(sql);
                /* ------------------------------------------------------------------ */

                /* Statement 2 in Transaction (Update ReleaseYear attribute) */
                /* ------------------------------------------------------------------ */
                sql =
                        "UPDATE Albums SET ReleaseYear = %d WHERE AlbumID = %d;";

                sql = String.format(sql, newYear, ID);
                statement.executeUpdate(sql);
                /* ------------------------------------------------------------------ */

                /* Statement 3 in Transaction (Update Edition attribute) */
                /* ------------------------------------------------------------------ */
                sql =
                        "UPDATE Albums SET Edition = '%s' WHERE AlbumID = %d;";

                sql = String.format(sql, newEdition, ID);
                statement.executeUpdate(sql);
                /* ------------------------------------------------------------------ */

                /* commit the executed statements */
                connection.commit();

            } catch (SQLException error) {

                /* rollback the transaction if anything should fail to commit */
                connection.rollback();

                return "error: Problem Executing Transaction";

            } finally {

                /* set auto commit back to true (just in case, even though connection closes automatically) */
                connection.setAutoCommit(true);

            }

        } catch (ClassNotFoundException | SQLException e) {

            String errorMsg = "Unable to Connect Using jdbcURL: " + jdbcURL;
            return "error"+errorMsg;

        }

        return "success";
    }
}
