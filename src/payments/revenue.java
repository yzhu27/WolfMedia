package payments;

import util.*;
import java.sql.*;

import java.util.Scanner;


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
            System.err.println(errorMsg);
            return 0;

        }

        return MonthlyRevenue;
    }

    public static String execute(float MonthlyRevenue, String RevDate) {

        String sql =
                "INSERT INTO RevenueRecords VALUES " +
                        "('%s', '%.2f')" +
                        ";"
                ;
        sql = String.format(sql, RevDate, MonthlyRevenue);

        return queryExecuter.execute(sql);
    }



    public static String run(Scanner reader) throws SQLException{
        System.out.println("+------------------------------------+");
        System.out.println("|        RevenueRecords Details      |");
        System.out.println("+------------------------------------+");
        System.out.println("");

        DBTablePrinter.printTable("RevenueRecords");


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
