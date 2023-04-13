package maintain;

import util.*;
import java.sql.*;
import java.util.Scanner;

public class updatePlayCount {
    public static String run(Scanner reader) throws SQLException{
        System.out.println("+------------------------------------+");
        System.out.println("| Please Submit the Following Inputs |");
        System.out.println("+------------------------------------+");
        System.out.println("");

        System.out.println("SongID: ");
        int SongID = reader.nextInt();
        reader.nextLine();

        System.out.println("Select attribute to update:" + "\n" +
                "1. Playcount"
        );
        int choice = reader.nextInt();
        reader.nextLine();

        String attribute;
        if (choice == 1){
            attribute = "Playcount";
        } else {
            return "Error: Invalid Input";
        }

        System.out.println("New Value: ");
        int newValue = reader.nextInt();

        return execute(SongID, attribute, newValue);
    }

    public static String execute(int SongID, String attribute, int newValue) {

        String sql =
            "UPDATE Songs " +
            "SET %s = %d "  +
            "WHERE SongID = %d " +
            ";"
        ;

        sql = String.format(sql, attribute, newValue, SongID);
        return queryExecuter.execute(sql);
    }
}
