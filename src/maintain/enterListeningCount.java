package maintain;

import util.*;
import java.sql.*;
import java.util.Scanner;

public class enterListeningCount {
    public static String run(Scanner reader) throws SQLException{
        System.out.println("+--------------------------------------------------+");
        System.out.println("| Reset the listen count of a Podcast Episode to 0 |");
        System.out.println("+--------------------------------------------------+");
        System.out.println("");

        DBTablePrinter.printTable("PodcastEpisodes");

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

        return execute(PID, PEID, attribute, 0);
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
