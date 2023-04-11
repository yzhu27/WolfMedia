package process;

import java.util.Scanner;

import config.Connect;
import config.Result;

/**
 * This program is used to perform enterPodcastInfo API operation by updating the Podcasts table.
 */

public class enterPodcastInfo {

    public static Result run(Scanner reader) {

        System.out.println("| Please Submit the following details: |");

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
        double rating = reader.nextDouble();
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

    public static Result execute(int podcastID, String name, String language, String country, double rating, int subscribers, int episodeCount, int phID) {
        
        String sql = 
            "INSERT INTO Podcasts VALUES "  + "\n" + "\t" +
                "(%d, '%s', '%s', '%s', %.2f, %d, %d, %d)"  + "\n" +
            ";"+ "\n" + "\n"
        ;
        
        sql = String.format(sql, podcastID, name, language, country, rating, subscribers, episodeCount, phID);
        
        return Connect.executeUpdate(sql);
    }

    public static void main(String[] args) {
        
    }

}
