package payments;

import config.Connect;
import config.Result;

import java.util.Scanner;

public class addLabelPaymentRecord {

    public static Result execute(String PayDate, int RLID, float payAmount) {

        String sql =
                "INSERT INTO LabelPaymentRecords VALUES " +
                        "('%s', %d, %.2f)" +
                        ";"
                ;
        sql = String.format(sql, PayDate, RLID, payAmount);

        return Connect.executeQuery(sql);
    }

    public static Result run(Scanner reader) {
        System.out.println("+------------------------------------+");
        System.out.println("| Please Submit the Following Inputs |");
        System.out.println("+------------------------------------+");
        System.out.println("");

        System.out.println("RLID: ");
        int RLID = reader.nextInt();
        reader.nextLine();

        System.out.println("PayDate: YYYY-MM-DD");
        String PayDate = reader.nextLine();
        reader.nextLine();

        System.out.println("PayAmount: ");
        float PayAmount = reader.nextFloat();
        reader.nextLine();

        return execute(PayDate, RLID, PayAmount);
    }

}
