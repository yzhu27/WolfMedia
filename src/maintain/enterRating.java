package maintain;

import util.*;
import java.sql.*;
import java.util.Scanner;


/**
 *  This Class used to reset podcast rating to zero.
 */

public class enterRating {
    public static String run(Scanner reader) throws SQLException{
        System.out.println("+------------------------------------+");
        System.out.println("| Reset the rating of a podcast to 0 |");
        System.out.println("+------------------------------------+");
        System.out.println("");

        DBTablePrinter.printTable("Podcasts");

        System.out.println("PodcastID: ");
        int PID = reader.nextInt();
        reader.nextLine();

        System.out.println("Select attribute to update:" + "\n" +
                "1. PRating"
        );
        int choice = reader.nextInt();
        reader.nextLine();

        String attribute;
        if (choice == 1){
            attribute = "PRating";
        } else {
            return "Error: Invalid Input";
        }

        // System.out.println("New Value: ");
        // int newValue = reader.nextInt();

        return execute(PID, attribute, 0);
    }

    public static String execute(int ID, String attribute, int newValue) {

        String sql =
            "UPDATE Podcasts " +
            "SET %s = %d "  +
            "WHERE PID = %d " +
            ";"
        ;

        sql = String.format(sql, attribute, newValue, ID);
        return queryExecuter.execute(sql);
    }
}
