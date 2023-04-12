package payments;

import util.queryExecuter;
import config.Result;

import java.util.Scanner;

public class getRevenueRecords {
    public static Result execute() {

        String sql =
                "SELECT * FROM RevenueRecords;";

        return queryExecuter.execute(sql);
    }

    public static Result run(Scanner reader) {
        System.out.println("+------------------------------------+");
        System.out.println("|        RevenueRecords Details      |");
        System.out.println("+------------------------------------+");
        System.out.println("");

        return execute();
    }
}
