package payments;

import config.Connect;
import config.Result;

import java.util.Scanner;

public class getLabelPaymentRecords {
    public static Result execute() {

        String sql =
                "SELECT * FROM LabelPaymentRecords;";

        return Connect.executeQuery(sql);
    }

    public static Result run(Scanner reader) {
        System.out.println("+------------------------------------+");
        System.out.println("|     LabelPaymentRecords Details    |");
        System.out.println("+------------------------------------+");
        System.out.println("");

        return execute();
    }
}
