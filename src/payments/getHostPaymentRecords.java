package payments;

import util.queryExecuter;


import java.util.Scanner;

public class getHostPaymentRecords {

    public static String execute() {

        String sql =
                "SELECT * FROM HostPaymentRecords;";

        return queryExecuter.execute(sql);
    }

    public static String run(Scanner reader) {
        System.out.println("+------------------------------------+");
        System.out.println("|      HostPaymentRecords Details    |");
        System.out.println("+------------------------------------+");
        System.out.println("");

        return execute();
    }
}
