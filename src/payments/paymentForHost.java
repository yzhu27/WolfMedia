package payments;

import util.queryExecuter;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Scanner;
import java.util.Date;
import java.sql.*;



public class paymentForHost {
    private static final String user = "mwang39";												// username
    private static final String password = "200476023";											// password
    private static final String jdbcURL = "jdbc:mariadb://classdb2.csc.ncsu.edu:3306/"+user;

    private static Connection connect() throws ClassNotFoundException, SQLException {
        Class.forName("org.mariadb.jdbc.Driver");
        return DriverManager.getConnection(jdbcURL, user, password);
    }

    public static int PEsHostedCurMonthCount(int PHID, String startDate, String endDate){

        ResultSet resultSet = null;
        int releasedEpisodeCount = 0;

        String sql = "SELECT COUNT(*) AS releasedEpisodeCount " +
                "FROM Podcasts AS p " +
                "JOIN PodcastEpisodes AS pe " +
                "ON p.PID=pe.PID " +
                "WHERE p.PHID=%d AND " +
                "pe.PEReleaseDate BETWEEN '%s' AND '%s';" ;

        sql = String.format(sql,PHID, startDate, endDate);

        try (Connection connection = connect()) {

            try (Statement statement = connection.createStatement()) {

                resultSet = statement.executeQuery(sql);

                while(resultSet.next()){
                    releasedEpisodeCount = resultSet.getInt("releasedEpisodeCount");
                }

            } catch (SQLException error) {
                return 0;
            }

        } catch (ClassNotFoundException | SQLException e) {

            String errorMsg = "Unable to Connect Using jdbcURL: " + "jdbc:mariadb://classdb2.csc.ncsu.edu:3306/";
            System.err.println(errorMsg);
            return 0;

        }

        return releasedEpisodeCount;
    }

    public static int adCountCurMonth(int PHID, String startDate, String endDate){

        ResultSet resultSet = null;
        int adCount = 0;

        String sql = "SELECT SUM(adCount) AS adCount " +
                "FROM Podcasts AS p " +
                "JOIN PodcastEpisodes AS pe " +
                "ON p.PID=pe.PID " +
                "WHERE p.PHID=%d AND " +
                "pe.PEReleaseDate BETWEEN '%s' AND '%s';" ;

        sql = String.format(sql,PHID, startDate, endDate);

        try (Connection connection = connect()) {

            try (Statement statement = connection.createStatement()) {

                resultSet = statement.executeQuery(sql);

                while(resultSet.next()){
                    adCount = resultSet.getInt("adCount");
                }

            } catch (SQLException error) {
                return 0;
            }

        } catch (ClassNotFoundException | SQLException e) {

            String errorMsg = "Unable to Connect Using jdbcURL: " + "jdbc:mariadb://classdb2.csc.ncsu.edu:3306/";
            System.err.println(errorMsg);
            return 0;

        }

        return adCount;
    }

    public static void showDetails(String tableName){
        String sql = String.format("SELECT * FROM " + tableName + ";");
        System.out.println("sql::" + sql);
        queryExecuter.execute(sql);
    }

    public static void showPEhosted(int PHID){
        String sql = "SELECT p.PName, p.PHID, pe.PETitle, pe.PEReleaseDate " +
                "FROM Podcasts AS p " +
                "JOIN PodcastEpisodes AS pe " +
                "ON p.PID=pe.PID " +
                "WHERE p.PHID = %d;";

        sql = String.format(sql,PHID);
        queryExecuter.execute(sql);
    }


    public static String execute(int PHID, float PayAmount, String PayDate) {

        String sql =
                "INSERT INTO HostPaymentRecords VALUES " +
                        "('%s', %d, %.2f)" +
                        ";"
                ;
        sql = String.format(sql, PayDate, PHID, PayAmount);

        return queryExecuter.execute(sql);
    }

    public static String run(Scanner reader) throws ParseException {
        System.out.println("+------------------------------------+");
        System.out.println("|     HostPaymentRecords Details     |");
        System.out.println("+------------------------------------+");
        System.out.println("");

        showDetails("HostPaymentRecords");


        System.out.println("+------------------------------------+");
        System.out.println("| Please Submit the Following Inputs |");
        System.out.println("+------------------------------------+");
        System.out.println("");

        System.out.println("PHID : ");
        int PHID = reader.nextInt();
        reader.nextLine();

        System.out.println("PayDate: YYYY-MM-DD");
        String PayDate = reader.nextLine();

        System.out.println("+------------------------------------+");
        System.out.println("|PodcastEpisodes Hosted By Given Host|");
        System.out.println("+------------------------------------+");
        System.out.println("");

        showPEhosted(PHID);

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try{
            date = df.parse(PayDate);
        } catch (ParseException e){
            e.printStackTrace();
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.MONTH, -1);
        String startDate = df.format(cal.getTime());

        cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, -1);
        String endDate = df.format(cal.getTime());


        float flatFee = 10;
        int releasedEpisodeCount = PEsHostedCurMonthCount(PHID, startDate, endDate);
        int adCount = adCountCurMonth(PHID, startDate, endDate);
        float PayAmount = releasedEpisodeCount * flatFee + adCount;


        return execute(PHID, PayAmount, PayDate);
    }

}
