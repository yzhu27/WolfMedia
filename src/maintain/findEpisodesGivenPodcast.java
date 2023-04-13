package maintain;

import util.*;
import java.sql.*;
import java.util.Scanner;

public class findEpisodesGivenPodcast {


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

        System.out.println("Podcast ID: ");
        int PID = reader.nextInt();
        reader.nextLine();

        String attribute = "PID";

        return execute(PID, attribute);
    }

    public static String execute(int ID, String attribute) {

        String sql =
            "SELECT * " +
            "FROM PodcastEpisodes "  +
            "WHERE %s = %d " +
            ";"
        ;

        sql = String.format(sql, attribute, ID);
        return queryExecuter.execute(sql);
    }
}
