package payments;

import config.Connect;
import config.Result;

import java.util.Scanner;
public class delRevenueRecord {

    public static Result execute(String RevDate) {

        String sql =
                "DELETE FROM RevenueRecords WHERE RevDate = '%s';";
        sql = String.format(sql, RevDate);

        return Connect.executeQuery(sql);
    }

    public static Result run(Scanner reader) {
        System.out.println("+------------------------------------+");
        System.out.println("| Please Submit the Following Inputs |");
        System.out.println("+------------------------------------+");
        System.out.println("");

        System.out.println("RevDate: YYYY-MM-DD");
        String RevDate = reader.nextLine();

        return execute(RevDate);
    }
}
