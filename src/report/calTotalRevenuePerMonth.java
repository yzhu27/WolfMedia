package report;

import util.queryExecuter;

import java.sql.SQLException;
import java.util.Scanner;

public class calTotalRevenuePerMonth {
    public static String execute(String year) {

        String startDate = year.concat("-01");
        String endDate = year.concat("-31");

        String sql =
                "SELECT SUM(RevAmount) FROM RevenueRecords " + "\n" +
                        "WHERE RevDate between '%s' and '%s';"
                ;

        sql = String.format(sql, startDate, endDate);

        return queryExecuter.execute(sql);
    }



    public static String run(Scanner reader) throws SQLException {

        System.out.println("+------------------------------------+");
        System.out.println("| Please Submit the Following Inputs |");
        System.out.println("+------------------------------------+");
        System.out.println("");

        System.out.println("Month: YYYY-MM");
        String year = reader.nextLine();


        return execute(year);
    }
}
