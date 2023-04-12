package payments;

import util.queryExecuter;
import config.Result;

import java.util.Scanner;

public class getHostPaymentRecords {

    public static Result execute() {

        String sql =
                "SELECT * FROM HostPaymentRecords;";

        return queryExecuter.execute(sql);
    }

    public static Result run(Scanner reader) {
        System.out.println("+------------------------------------+");
        System.out.println("|      HostPaymentRecords Details    |");
        System.out.println("+------------------------------------+");
        System.out.println("");

        return execute();
    }
}
