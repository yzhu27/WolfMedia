package maintain;

import util.*;
import java.sql.*;
import java.util.Scanner;

public class updateTotalSubscribers {
    public static String run(Scanner reader) throws SQLException{
        System.out.println("+------------------------------------+");
        System.out.println("| Please Submit the Following Inputs |");
        System.out.println("+------------------------------------+");
        System.out.println("");

        System.out.println("PodcastID: ");
        int PID = reader.nextInt();
        reader.nextLine();

        System.out.println("Select attribute to update:" + "\n" +
                "1. PSubscribers"
        );
        int choice = reader.nextInt();
        reader.nextLine();

        String attribute;
        if (choice == 1){
            attribute = "PSubscribers";
        } else {
            return "Error: Invalid Input";
        }

        System.out.println("New Value: ");
        int newValue = reader.nextInt();

        return execute(PID, attribute, newValue);
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
