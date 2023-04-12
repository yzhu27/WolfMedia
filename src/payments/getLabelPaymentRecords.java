package payments;

import util.queryExecuter;
import config.Result;

import java.util.Scanner;

public class getLabelPaymentRecords {
    public static Result execute() {

        String sql =
                "SELECT * FROM LabelPaymentRecords;";

        return queryExecuter.execute(sql);
    }

    public static Result run(Scanner reader) {
        System.out.println("+------------------------------------+");
        System.out.println("|     LabelPaymentRecords Details    |");
        System.out.println("+------------------------------------+");
        System.out.println("");

        return execute();
    }
}
