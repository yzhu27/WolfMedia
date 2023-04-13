package maintain;

import util.*;
import java.sql.*;
import java.util.Scanner;

public class updateRating {
    public static String run(Scanner reader) throws SQLException{
        System.out.println("+------------------------------------+");
		System.out.println("|           Podcasts Details         |");
		System.out.println("+------------------------------------+");
		System.out.println("");

		DBTablePrinter.printTable("Podcasts");

        System.out.println("+------------------------------------+");
        System.out.println("| Please Submit the Following Inputs |");
        System.out.println("+------------------------------------+");
        System.out.println("");

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

        System.out.println("New Value: ");
        float newValue = reader.nextFloat();

        return execute(PID, attribute, newValue);
    }

    public static String execute(int ID, String attribute, float newValue) {

        String sql =
            "UPDATE Podcasts " +
            "SET %s = %.2f "  +
            "WHERE PID = %d " +
            ";"
        ;

        sql = String.format(sql, attribute, newValue, ID);
        return queryExecuter.execute(sql);
    }
}
