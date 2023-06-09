package process;

import java.util.Scanner;

import util.*;
import java.sql.*;

/**
 *  This Class used for executing the enterPodcastInfo API operation by updating the Podcasts Table.
 */

public class enterPodcastInfo {

    public static String run(Scanner reader) throws SQLException{
        System.out.println("+------------------------------------+");
        System.out.println("| Please Submit the Following Inputs |");
        System.out.println("+------------------------------------+");
        System.out.println("");

        System.out.println("Podcast ID: ");
        int podcastID = reader.nextInt();
        reader.nextLine();

        System.out.println("Name: ");
        String name = reader.nextLine();

        System.out.println("Language: ");
        String language = reader.nextLine();

        System.out.println("Country: ");
        String country = reader.nextLine();

        System.out.println("Rating: ");
        float rating = reader.nextFloat();
        reader.nextLine();

        System.out.println("Subscribers: ");
        int subscribers = reader.nextInt();
        reader.nextLine();

        System.out.println("Episode Count: ");
        int episodeCount = reader.nextInt();
        reader.nextLine();

        System.out.println("Podcast Host ID: ");
        int phID = reader.nextInt();
        reader.nextLine();

        return execute(podcastID, name, language, country, rating, subscribers, episodeCount, phID);   
    }

    public static String execute(int podcastID, String name, String language, String country, float rating, int subscribers, int episodeCount, int phID) {
        
        String sql = 
            "INSERT INTO Podcasts VALUES "  + "\n" + "\t" +
                "(%d, '%s', '%s', '%s', %.1f, %d, %d, %d)"  + "\n" +
            ";"+ "\n" + "\n"
        ;
        
        sql = String.format(sql, podcastID, name, language, country, rating, subscribers, episodeCount, phID);
        
        return queryExecuter.execute(sql);
    }

    public static void main(String[] args) {
        
    }

}
