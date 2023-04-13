package payments;

import util.queryExecuter;


import java.util.Scanner;
public class delRevenueRecord {

    public static String execute(String RevDate) {

        String sql =
                "DELETE FROM RevenueRecords WHERE RevDate = '%s';";
        sql = String.format(sql, RevDate);

        return queryExecuter.execute(sql);
    }

    public static String run(Scanner reader) {
        System.out.println("+------------------------------------+");
        System.out.println("| Please Submit the Following Inputs |");
        System.out.println("+------------------------------------+");
        System.out.println("");

        System.out.println("RevDate: YYYY-MM-DD");
        String RevDate = reader.next();
        reader.next();

        return execute(RevDate);
    }
}
