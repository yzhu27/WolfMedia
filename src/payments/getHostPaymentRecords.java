package payments;

import config.Connect;
import config.Result;

import java.util.Scanner;

public class getHostPaymentRecords {

    public static Result execute() {

        String sql =
                "SELECT * FROM HostPaymentRecords;";

        return Connect.executeQuery(sql);
    }

    public static Result run(Scanner reader) {
        System.out.println("+------------------------------------+");
        System.out.println("|      HostPaymentRecords Details    |");
        System.out.println("+------------------------------------+");
        System.out.println("");

        return execute();
    }
}
