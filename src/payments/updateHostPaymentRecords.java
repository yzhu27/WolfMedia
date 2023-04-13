package payments;

import util.*;
import java.sql.*;

import java.util.Scanner;

public class updateHostPaymentRecords {

    public static String run(Scanner reader) throws SQLException{
        System.out.println("+------------------------------------+");
        System.out.println("| Please Submit the Following Inputs |");
        System.out.println("+------------------------------------+");
        System.out.println("");

        System.out.println("PayDate: ");
        String PayDate = reader.nextLine();
        reader.nextLine();

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
            return "Error: Invalid Input";
        }

        System.out.println("New Value: ");
        String newValue = reader.nextLine();

        return execute(PHID, attribute, PayDate, newValue);
    }

    public static String execute(int PHID, String attribute, String PayDate, String newValue) {

        if (attribute == "PayDate"){
            String sql =
                    "UPDATE HostPaymentRecords " +
                            "SET %s = '%s' "  +
                            "WHERE PayDate = '%s' AND HostID = %d" +
                            ";"
                    ;

            sql = String.format(sql, attribute, newValue, PayDate, PHID);
            return queryExecuter.execute(sql);
        }
        else {
            float tmp = Float.parseFloat(newValue);
            String sql =
                    "UPDATE HostPaymentRecords " +
                            "SET %s = %.2f "  +
                            "WHERE PayDate = '%s' AND HostID = %d " +
                            ";"
                    ;

            sql = String.format(sql, attribute, tmp, PayDate, PHID);
            return queryExecuter.execute(sql);
        }
    }
}
