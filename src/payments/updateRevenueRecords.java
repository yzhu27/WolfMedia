package payments;

import config.Connect;
import config.Result;

import java.util.Scanner;

public class updateRevenueRecords {

    public static Result run(Scanner reader) {
        System.out.println("+------------------------------------+");
        System.out.println("| Please Submit the Following Inputs |");
        System.out.println("+------------------------------------+");
        System.out.println("");

        System.out.println("RevDate: YYYY-MM-DD");
        String RevDate = reader.nextLine();
        reader.nextLine();

        System.out.println("RevAmount: ");
        float RevAmount = reader.nextFloat();
        reader.nextLine();

        return execute(RevDate, RevAmount);
    }

    public static Result execute(String RevDate, float RevAmount) {

        String sql =
                "UPDATE RevenueRecords" +
                        "SET RevAmount = %.2f" +
                        "WHERE RevDate = '%s'" +
                        ";";

        sql = String.format(sql, RevAmount, RevDate);
        return Connect.executeUpdate(sql);
    }
}
