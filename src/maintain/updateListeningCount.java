package maintain;

import util.*;
import java.sql.*;
import java.util.Scanner;


/**
 *  This Class used to update the listening count in PodcastEpisodes table.
 */


public class updateListeningCount {
    public static String run(Scanner reader) throws SQLException{
        System.out.println("+------------------------------------+");
		System.out.println("|           Podcasts Details         |");
		System.out.println("+------------------------------------+");
		System.out.println("");

		DBTablePrinter.printTable("Podcasts");

        System.out.println("+------------------------------------+");
		System.out.println("|           Episode  Details         |");
		System.out.println("+------------------------------------+");
		System.out.println("");

		DBTablePrinter.printTable("PodcastEpisodes");
        
        System.out.println("+------------------------------------+");
        System.out.println("| Please Submit the Following Inputs |");
        System.out.println("+------------------------------------+");
        System.out.println("");

        System.out.println("Podcast Episode ID: ");
        int PEID = reader.nextInt();
        reader.nextLine();

        System.out.println("Podcast ID: ");
        int PID = reader.nextInt();
        reader.nextLine();

        System.out.println("Select attribute to update:" + "\n" +
                "1. Podcast Episode Listen Count:"
        );
        int choice = reader.nextInt();
        reader.nextLine();

        String attribute;
        if (choice == 1){
            attribute = "ListenerCount";
        } else {
            return "Error: Invalid Input";
        }

        System.out.println("New Value: ");
        int newValue = reader.nextInt();
        reader.nextLine();

        return execute(PID, PEID, attribute, newValue);
    }

    public static String execute(int ID, int ID2, String attribute, int newValue) {

        String sql =
            "UPDATE PodcastEpisodes " +
            "SET %s = %d "  +
            "WHERE PEID = %d " +
            "AND PID = %d " +
            ";"
        ;

        sql = String.format(sql, attribute, newValue, ID2, ID);
        return queryExecuter.execute(sql);
    }
}
