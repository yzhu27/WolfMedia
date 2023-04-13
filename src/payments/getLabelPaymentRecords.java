package payments;

import util.queryExecuter;


import java.util.Scanner;

public class getLabelPaymentRecords {
    public static String execute() {

        String sql =
                "SELECT * FROM LabelPaymentRecords;";

        return queryExecuter.execute(sql);
    }

    public static String run(Scanner reader) {
        System.out.println("+------------------------------------+");
        System.out.println("|     LabelPaymentRecords Details    |");
        System.out.println("+------------------------------------+");
        System.out.println("");

        return execute();
    }
}
