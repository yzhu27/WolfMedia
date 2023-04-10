package payments;

import config.Connect;
import config.Result;
import config.Transaction;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Scanner;
import java.util.Date;
import java.sql.*;
import java.lang.StringBuilder;

public class revenue {

    private static final String user = "mwang39";												// username
    private static final String password = "200476023";											// password
    private static final String jdbcURL = "jdbc:mariadb://classdb2.csc.ncsu.edu:3306/"+user;

    private static Connection connect() throws ClassNotFoundException, SQLException {
        Class.forName("org.mariadb.jdbc.Driver");
        return DriverManager.getConnection(jdbcURL, user, password);
    }

    public static float calMonthlyRevenue(){

        ResultSet resultSet = null;
        float MonthlyRevenue = 0.0f;

        String sql = "SELECT SUM(UMonthlyFee) AS MonthlyRevenue FROM Users WHERE UStatus='active';" ;

        try (Connection connection = connect()) {

            try (Statement statement = connection.createStatement()) {

                resultSet = statement.executeQuery(sql);

                while(resultSet.next()){
                    MonthlyRevenue = resultSet.getInt("MonthlyRevenue");
                }

            } catch (SQLException error) {
                return 0.0f;
            }

        } catch (ClassNotFoundException | SQLException e) {

            String errorMsg = "Unable to Connect Using jdbcURL: " + "jdbc:mariadb://classdb2.csc.ncsu.edu:3306/";
            return 0.0f;

        }

        return MonthlyRevenue;
    }

    public static Result execute(float MonthlyRevenue, String RevDate) {

        String sql =
                "INSERT INTO RevenueRecords VALUES " +
                        "(%s, '%.2f')" +
                        ";"
                ;
        sql = String.format(sql, RevDate, MonthlyRevenue);

        return Connect.executeUpdate(sql);
    }

    public static void showDetails(String tableName){
        String sql = String.format("SELECT * FROM " + tableName + ";");
        Connect.executeQuery(sql);
    }

    public static Result run(Scanner reader) {
        System.out.println("+------------------------------------+");
        System.out.println("|        RevenueRecords Details      |");
        System.out.println("+------------------------------------+");
        System.out.println("");

        showDetails("RevenueRecords");


        System.out.println("+------------------------------------+");
        System.out.println("| Please Submit the Following Inputs |");
        System.out.println("+------------------------------------+");
        System.out.println("");

        System.out.println("Receive Date: YYYY-MM-DD");
        String RevDate = reader.nextLine();
        reader.nextLine();

        float MonthlyRevenue = calMonthlyRevenue();

        return execute(MonthlyRevenue, RevDate);
    }
}
