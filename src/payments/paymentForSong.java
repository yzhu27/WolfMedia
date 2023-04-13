package payments;

import util.queryExecuter;

import config.Transaction;


import java.util.Scanner;
import java.sql.*;



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

    public static int findCollaboratorGivenSong(int SongID){

        ResultSet resultSet = null;
        int CollaboratorID = 0;


        String sql = "SELECT ArtistID FROM Collaborate " +
                "WHERE SongID = %d;";

        sql = String.format(sql,SongID);

        try (Connection connection = connect()) {

            try (Statement statement = connection.createStatement()) {

                resultSet = statement.executeQuery(sql);

                while(resultSet.next()){
                    CollaboratorID = resultSet.getInt("ArtistID");
                    //Array a = resultSet.getArray("ArtistID");
                    //Int[] Collaborators = (Int[])a.getArray();
                }

            } catch (SQLException error) {
                return 0;
            }

        } catch (ClassNotFoundException | SQLException e) {

            String errorMsg = "Unable to Connect Using jdbcURL: " + "jdbc:mariadb://classdb2.csc.ncsu.edu:3306/";
            System.err.println(errorMsg);
            return 0;

        }

        return CollaboratorID;
        //return Collaborators;
    }

    public static void showDetails(String tableName){
        String sql = String.format("SELECT * FROM " + tableName + ";");
        queryExecuter.execute(sql);
    }

    public static String execute(int SongID, float MonthlyRoyalties, int RLID, int ArtistID, int CollaboratorID, String PayDate) {
        float paymentToRL = (float) (MonthlyRoyalties * 0.3);
        float paymentToArtists = 0;
        if (CollaboratorID == 0) {
            paymentToArtists = (float) (MonthlyRoyalties * 0.7);
        }
        else {
            paymentToArtists = (float) (MonthlyRoyalties * 0.7 / 2);
        }

        Transaction transaction = new Transaction();

        /* Statement 1 in Transaction (add payment record to LabelPaymentRecords Table) */
        /* ------------------------------------------------------------------ */
        String sql =
                "INSERT INTO LabelPaymentRecords VALUES " +
                        "('%s', %d, %.2f)" +
                        ";"
                ;
        sql = String.format(sql, PayDate, RLID, paymentToRL);
        transaction.addStatement(sql, Transaction.StatementType.UPDATE);
        /* ------------------------------------------------------------------ */

        /* Statement 2 in Transaction (add payment record to ArtistPaymentRecords Table) */
        /* ------------------------------------------------------------------ */
        if (CollaboratorID == 0) {
            sql =
                    "INSERT INTO ArtistPaymentRecords VALUES " +
                            "('%s', %d, %.2f);"
            ;
            sql = String.format(sql, PayDate, ArtistID, paymentToArtists, PayDate, CollaboratorID, paymentToArtists);
        }
        else {
            sql =
                    "INSERT INTO ArtistPaymentRecords VALUES " +
                            "('%s', %d, %.2f), " +
                            "('%s', %d, %.2f)" +
                            ";"
            ;
            sql = String.format(sql, PayDate, ArtistID, paymentToArtists, PayDate, CollaboratorID, paymentToArtists);
        }

        transaction.addStatement(sql, Transaction.StatementType.UPDATE);
        /* ------------------------------------------------------------------ */

        /* Statement 3 in Transaction (renew royalty_paid of the given song) */
        /* ------------------------------------------------------------------ */
        sql =
                "UPDATE Songs SET RoyaltyPaid = 'yes' WHERE SongID = %d;"
                ;
        sql = String.format(sql, SongID);
        transaction.addStatement(sql, Transaction.StatementType.UPDATE);
        /* ------------------------------------------------------------------ */

        /* Execute Transaction via Connect and return result */
        return Connect.executeTransaction(transaction);
    }

    public static String run(Scanner reader) {
        System.out.println("+------------------------------------+");
        System.out.println("|            Songs Details           |");
        System.out.println("+------------------------------------+");
        System.out.println("");

        showDetails("Songs");

        System.out.println("+------------------------------------+");
        System.out.println("|     ArtistPaymentRecords Details    |");
        System.out.println("+------------------------------------+");
        System.out.println("");

        showDetails("ArtistPaymentRecords");

        System.out.println("+------------------------------------+");
        System.out.println("|     LabelPaymentRecords Details    |");
        System.out.println("+------------------------------------+");
        System.out.println("");

        showDetails("LabelPaymentRecords");

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

        int CollaboratorID = findCollaboratorGivenSong(SongID);

        return execute(SongID, MonthlyRoyalties, RLID, ArtistID, CollaboratorID, PayDate);
    }
}
