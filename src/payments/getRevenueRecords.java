package payments;

import util.queryExecuter;


import java.util.Scanner;

public class getRevenueRecords {
    public static String execute() {

        String sql =
                "SELECT * FROM RevenueRecords;";

        return queryExecuter.execute(sql);
    }

    public static String run(Scanner reader) {
        System.out.println("+------------------------------------+");
        System.out.println("|        RevenueRecords Details      |");
        System.out.println("+------------------------------------+");
        System.out.println("");

        return execute();
    }
}
