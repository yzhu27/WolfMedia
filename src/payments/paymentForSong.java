package payments;

import util.*;
import java.sql.*;


import java.util.ArrayList;
import java.util.Scanner;




public class paymentForSong {

    private static final String user = "mwang39";												// username
    private static final String password = "200476023";											// password
    private static final String jdbcURL = "jdbc:mariadb://classdb2.csc.ncsu.edu:3306/"+user;

    private static Connection connect() throws ClassNotFoundException, SQLException {
        Class.forName("org.mariadb.jdbc.Driver");
        return DriverManager.getConnection(jdbcURL, user, password);
    }

    public static float calMonthlyRoyalties(int SongID){

        ResultSet resultSet = null;
        float MonthlyRoyalties = 0.0f;

        String sql = "SELECT RoyaltyRate * Playcount AS MonthlyRoyalties " +
                "FROM Songs " +
                "WHERE SongID = %d;" ;

        sql = String.format(sql,SongID);

        try (Connection connection = connect()) {

            try (Statement statement = connection.createStatement()) {

                resultSet = statement.executeQuery(sql);

                while(resultSet.next()){
                    MonthlyRoyalties = resultSet.getFloat("MonthlyRoyalties");
                }

            } catch (SQLException error) {
                return 0.0f;
            }

        } catch (ClassNotFoundException | SQLException e) {

            String errorMsg = "Unable to Connect Using jdbcURL: " + "jdbc:mariadb://classdb2.csc.ncsu.edu:3306/";
            System.err.println(errorMsg);
            return 0;

        }

        return MonthlyRoyalties;
    }

    public static boolean checkNoDuplicate(String table, int ID, String Paydate){

        int flag = 0;
        String id = "LabelID";

        ResultSet resultSet = null;
        if (table.equals("ArtistPaymentRecords")){
            id = "ArtistID";
        }
        String sql = "SELECT COUNT(*) AS COUNT " +
                "FROM %s " +
                "WHERE %s = %d AND PayDate = '%s';";

        sql = String.format(sql, table, id, ID, Paydate);

        try (Connection connection = connect()) {

            try (Statement statement = connection.createStatement()) {

                resultSet = statement.executeQuery(sql);

                while(resultSet.next()){
                    flag = resultSet.getInt("COUNT");
                }

            } catch (SQLException error) {
                return false;
            }

        } catch (ClassNotFoundException | SQLException e) {

            String errorMsg = "Unable to Connect Using jdbcURL: " + "jdbc:mariadb://classdb2.csc.ncsu.edu:3306/";
            System.err.println(errorMsg);
            return false;

        }
        if (flag == 0){
            return true;
        }
        else {
            return false;
        }
    }
    public static int findRecordLabelGivenSong(int SongID){

        ResultSet resultSet = null;
        int RLID = 0;

        String sql = "SELECT rl.RLID FROM RecordLabels AS rl " +
                "JOIN Artists AS ar ON rl.RLID=ar.RLID " +
                "WHERE ar.ArtistID = (SELECT ArtistID FROM Songs WHERE SongID = %d);";

        sql = String.format(sql,SongID);

        try (Connection connection = connect()) {

            try (Statement statement = connection.createStatement()) {

                resultSet = statement.executeQuery(sql);

                while(resultSet.next()){
                    RLID = resultSet.getInt("RLID");
                }

            } catch (SQLException error) {
                return 0;
            }

        } catch (ClassNotFoundException | SQLException e) {

            String errorMsg = "Unable to Connect Using jdbcURL: " + "jdbc:mariadb://classdb2.csc.ncsu.edu:3306/";
            System.err.println(errorMsg);
            return 0;

        }

        return RLID;
    }

    public static int findArtistGivenSong(int SongID){

        ResultSet resultSet = null;
        int ArtistID = 0;

        String sql = "SELECT ar.ArtistID FROM Artists AS ar " +
                "JOIN Songs AS s ON ar.ArtistID=s.ArtistID " +
                "WHERE s.SongID = %d;";

        sql = String.format(sql,SongID);

        try (Connection connection = connect()) {

            try (Statement statement = connection.createStatement()) {

                resultSet = statement.executeQuery(sql);

                while(resultSet.next()){
                    ArtistID = resultSet.getInt("ArtistID");
                }

            } catch (SQLException error) {
                return 0;
            }

        } catch (ClassNotFoundException | SQLException e) {

            String errorMsg = "Unable to Connect Using jdbcURL: " + "jdbc:mariadb://classdb2.csc.ncsu.edu:3306/";
            System.err.println(errorMsg);
            return 0;

        }

        return ArtistID;
    }

    public static  ArrayList<Integer> findCollaboratorGivenSong(int SongID){

        ResultSet resultSet = null;
        //int CollaboratorID = 0;
        ArrayList<Integer> Collaborators = new ArrayList<Integer>();


        String sql = "SELECT ArtistID FROM Collaborate " +
                "WHERE SongID = %d;";

        sql = String.format(sql,SongID);

        try (Connection connection = connect()) {

            try (Statement statement = connection.createStatement()) {

                resultSet = statement.executeQuery(sql);

                while(resultSet.next()){
                    //CollaboratorID = resultSet.getInt("ArtistID");
                    Collaborators.add(resultSet.getInt("ArtistID"));
                }

            } catch (SQLException error) {
                return Collaborators;
            }

        } catch (ClassNotFoundException | SQLException e) {

            String errorMsg = "Unable to Connect Using jdbcURL: " + "jdbc:mariadb://classdb2.csc.ncsu.edu:3306/";
            System.err.println(errorMsg);
            return Collaborators;

        }

        //return CollaboratorID;
        return Collaborators;
    }



    public static String execute(int SongID, int RLID, int ArtistID, ArrayList<Integer> CollaboratorID, String PayDate, float paymentToRL, float paymentToArtists) {

        /* Execute Transaction via Connect and return result */
        try (Connection connection = connect()) {

            /* set auto commit to false - i.e. run statements as single transaction */
            connection.setAutoCommit(false);

            try (Statement statement = connection.createStatement()) {
                /* execute each query and update statement in the transaction */

                /* Statement 1 in Transaction (add payment record to LabelPaymentRecords Table) */
                /* ------------------------------------------------------------------ */
                if (checkNoDuplicate("LabelPaymentRecords", RLID, PayDate)){
                    String sql =
                            "INSERT INTO LabelPaymentRecords VALUES " +
                                    "('%s', %d, %.2f)" +
                                    ";"
                            ;
                    sql = String.format(sql, PayDate, RLID, paymentToRL);
                    statement.executeUpdate(sql);
                } else {
                    String sql =
                            "UPDATE LabelPaymentRecords " +
                                    "SET PayAmount = PayAmount + %.2f "  +
                                    "WHERE PayDate = '%s' AND LabelID = %d;";

                    sql = String.format(sql, paymentToRL, PayDate, RLID);
                    statement.executeUpdate(sql);
                }

                /* ------------------------------------------------------------------ */

                /* Statement 2 in Transaction (add payment record of Artist to ArtistPaymentRecords Table) */
                /* ------------------------------------------------------------------ */

                if (checkNoDuplicate("ArtistPaymentRecords", ArtistID, PayDate)){
                    String sql =
                            "INSERT INTO ArtistPaymentRecords VALUES " +
                                    "('%s', %d, %.2f)" +
                                    ";"
                            ;
                    sql = String.format(sql, PayDate, ArtistID, paymentToArtists);
                    statement.executeUpdate(sql);
                } else {
                    String sql =
                            "UPDATE ArtistPaymentRecords " +
                                    "SET PayAmount = PayAmount + %.2f "  +
                                    "WHERE PayDate = '%s' AND ArtistID = %d;";

                    sql = String.format(sql, paymentToArtists, PayDate, ArtistID);
                    statement.executeUpdate(sql);
                }
                /* ------------------------------------------------------------------ */

                /* Statement 3 in Transaction (add payment record of Collaborator to ArtistPaymentRecords Table) */
                /* ------------------------------------------------------------------ */
                if (CollaboratorID.size() != 0) {
                    for (int i = 0; i < CollaboratorID.size(); i++) {
                        int collaborator = CollaboratorID.get(i);


                        if (checkNoDuplicate("ArtistPaymentRecords", collaborator, PayDate)) {
                            String sql =
                                    "INSERT INTO ArtistPaymentRecords VALUES " +
                                            "('%s', %d, %.2f)" +
                                            ";";
                            sql = String.format(sql, PayDate, collaborator, paymentToArtists);
                            statement.executeUpdate(sql);
                        } else {
                            String sql =
                                    "UPDATE ArtistPaymentRecords " +
                                            "SET PayAmount = PayAmount + %.2f " +
                                            "WHERE PayDate = '%s' AND ArtistID = %d;";

                            sql = String.format(sql, paymentToArtists, PayDate, collaborator);
                            statement.executeUpdate(sql);
                        }
                    }
                }
                /* ------------------------------------------------------------------ */

                /* Statement 4 in Transaction (renew royalty_paid of the given song) */
                /* ------------------------------------------------------------------ */
                String sql =
                        "UPDATE Songs SET RoyaltyPaid = 'yes' WHERE SongID = %d;"
                ;
                sql = String.format(sql, SongID);
                statement.executeUpdate(sql);
                /* ------------------------------------------------------------------ */

                /* commit the executed statements */
                connection.commit();

            } catch (SQLException error) {

                /* rollback the transaction if anything should fail to commit */
                connection.rollback();

                return "Error: Problem Executing Transaction";

            } finally {

                /* set auto commit back to true (just in case, even though connection closes automatically) */
                connection.setAutoCommit(true);

            }

        } catch (ClassNotFoundException | SQLException e) {

            String errorMsg = "Unable to Connect Using jdbcURL: " + jdbcURL;
            return "error:"+errorMsg;

        }

        return "success";

    }

    public static String run(Scanner reader) throws SQLException{
        System.out.println("+------------------------------------+");
        System.out.println("|            Songs Details           |");
        System.out.println("+------------------------------------+");
        System.out.println("");

        DBTablePrinter.printTable("Songs");

        System.out.println("+------------------------------------+");
        System.out.println("|     ArtistPaymentRecords Details    |");
        System.out.println("+------------------------------------+");
        System.out.println("");

        DBTablePrinter.printTable("ArtistPaymentRecords");

        System.out.println("+------------------------------------+");
        System.out.println("|     LabelPaymentRecords Details    |");
        System.out.println("+------------------------------------+");
        System.out.println("");

        DBTablePrinter.printTable("LabelPaymentRecords");

        System.out.println("+------------------------------------+");
        System.out.println("| Please Submit the Following Inputs |");
        System.out.println("+------------------------------------+");
        System.out.println("");

        System.out.println("SongID : ");
        int SongID = reader.nextInt();
        reader.nextLine();

        System.out.println("PayDate: YYYY-MM-DD");
        String PayDate = reader.nextLine();
        reader.nextLine();

        float MonthlyRoyalties = calMonthlyRoyalties(SongID);

        int RLID = findRecordLabelGivenSong(SongID);

        int ArtistID = findArtistGivenSong(SongID);

        ArrayList<Integer> Collaborators = findCollaboratorGivenSong(SongID);

        float paymentToRL = (float) (MonthlyRoyalties * 0.3);
        float paymentToArtists = 0;
        if (Collaborators.size() == 0) {
            paymentToArtists = (float) (MonthlyRoyalties * 0.7);
        }
        else {
            paymentToArtists = (float) (MonthlyRoyalties * 0.7 / (Collaborators.size()+1));
        }

        return execute(SongID, RLID, ArtistID, Collaborators, PayDate, paymentToRL, paymentToArtists);
    }
}
