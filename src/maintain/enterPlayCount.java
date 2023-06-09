package maintain;

import util.*;
import java.sql.*;

import java.util.Scanner;

/**

/**
 *  This Class used to reset Playcount to zero.
 */

public class enterPlayCount {    
    public static String run(Scanner reader) throws SQLException{
        System.out.println("+-------------------------------------+");
        System.out.println("| Reset the play count of a song to 0 |");
        System.out.println("+-------------------------------------+");
        System.out.println("");

        DBTablePrinter.printTable("Songs");

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
