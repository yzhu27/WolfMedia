package maintain;

import util.*;
import java.sql.*;
import java.util.Scanner;


/**
 *  This Class used to update the monthly listeners count in the artists table.
 */


public class updateMonthlyListeners {
    public static String run(Scanner reader) throws SQLException{
        System.out.println("+------------------------------------+");
		System.out.println("|           Artist Details           |");
		System.out.println("+------------------------------------+");
		System.out.println("");

		DBTablePrinter.printTable("Artists");

        System.out.println("+------------------------------------+");
        System.out.println("| Please Submit the Following Inputs |");
        System.out.println("+------------------------------------+");
        System.out.println("");

        System.out.println("ArtistID: ");
        int AID = reader.nextInt();
        reader.nextLine();

        System.out.println("Select attribute to update:" + "\n" +
                "1. MonthlyListeners"
        );
        int choice = reader.nextInt();
        reader.nextLine();

        String attribute;
        if (choice == 1){
            attribute = "MonthlyListeners";
        } else {
            return "Error: Invalid Input";
        }

        System.out.println("New Value: ");
        int newValue = reader.nextInt();

        return execute(AID, attribute, newValue);
    }

    public static String execute(int ID, String attribute, int newValue) {

        String sql =
            "UPDATE Artists " +
            "SET %s = %d "  +
            "WHERE ArtistID = %d " +
            ";"
        ;

        sql = String.format(sql, attribute, newValue, ID);
        return queryExecuter.execute(sql);
    }
}
