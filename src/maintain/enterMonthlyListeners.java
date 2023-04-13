package maintain;

import util.*;
import java.sql.*;
import java.util.Scanner;

public class enterMonthlyListeners {
    public static String run(Scanner reader) throws SQLException{
        System.out.println("+------------------------------------------------------+");
        System.out.println("| Reset the monthly listener numbers of an artist to 0 |");
        System.out.println("+------------------------------------------------------+");
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

        // System.out.println("New Value: ");
        // int newValue = reader.nextInt();

        return execute(AID, attribute, 0);
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
