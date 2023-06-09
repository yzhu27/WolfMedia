package payments;

import util.*;
import java.sql.*;

import java.util.Scanner;

/**
 *  This Class used for updating a payment record in LabelPaymentRecords table.
 */

public class updateLabelPaymentRecords {

    public static String run(Scanner reader) throws SQLException{
        System.out.println("+------------------------------------+");
		System.out.println("|         Record Labels Details      |");
		System.out.println("+------------------------------------+");
		System.out.println("");

		DBTablePrinter.printTable("RecordLabels");

        System.out.println("+------------------------------------+");
        System.out.println("| Please Submit the Following Inputs |");
        System.out.println("+------------------------------------+");
        System.out.println("");

        System.out.println("PayDate: ");
        String PayDate = reader.nextLine();
        reader.nextLine();

        System.out.println("RLID: ");
        int RLID = reader.nextInt();
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

        return execute(RLID, attribute, PayDate, newValue);
    }

    public static String execute(int RLID, String attribute, String PayDate, String newValue) {

        if (attribute == "PayDate"){
            String sql =
                    "UPDATE LabelPaymentRecords " +
                            "SET %s = '%s' "  +
                            "WHERE PayDate = '%s' AND LabelID = %d " +
                            ";"
                    ;

            sql = String.format(sql, attribute, newValue, PayDate, RLID);
            return queryExecuter.execute(sql);
        }
        else {
            float tmp = Float.parseFloat(newValue);
            String sql =
                    "UPDATE LabelPaymentRecords " +
                            "SET %s = %.2f "  +
                            "WHERE PayDate = '%s' AND LabelID = %d " +
                            ";"
                    ;

            sql = String.format(sql, attribute, tmp, PayDate, RLID);
            return queryExecuter.execute(sql);
        }
    }
}
