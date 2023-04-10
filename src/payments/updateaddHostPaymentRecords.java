package payments;

import config.Connect;
import config.Result;

import java.util.Scanner;

public class updateaddHostPaymentRecords {

    public static Result run(Scanner reader) {
        System.out.println("+------------------------------------+");
        System.out.println("| Please Submit the Following Inputs |");
        System.out.println("+------------------------------------+");
        System.out.println("");

        System.out.println("PHID: ");
        int PHID = reader.nextInt();
        reader.nextLine();

        System.out.println("Select attribute to update:" + "\n" +
                "1. PayDate (YYYY-MM-YY)" + "\n" +
                "2. PayAmount"
        );
        int choice = reader.nextInt();
        reader.nextLine();

        String attribute;
        if (choice == 1){
            attribute = "PayDate";
        } else if (choice == 2){
            attribute = "PayAmount";
        } else {
            return new Result(false, "Invalid input");
        }

        System.out.println("New Value: ");
        String newValue = reader.nextLine();

        return execute(PHID, attribute, newValue);
    }

    public static Result execute(int PHID, String attribute, String newValue) {

        if (attribute == "PayDate"){
            String sql =
                    "UPDATE HostPaymentRecords" +
                            "SET %s = '%s'"  +
                            "WHERE HostID = %d" +
                            ";"
                    ;

            sql = String.format(sql, attribute, newValue, PHID);
            return Connect.executeUpdate(sql);
        }
        else {
            float tmp = Float.parseFloat(newValue);
            String sql =
                    "UPDATE HostPaymentRecords" +
                            "SET %s = %.2f"  +
                            "WHERE HostID = %d" +
                            ";"
                    ;

            sql = String.format(sql, attribute, newValue, PHID);
            return Connect.executeUpdate(sql);
        }
    }
}
