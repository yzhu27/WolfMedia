package process;

import java.sql.*;
import java.util.Scanner;

import config.Connect;
import config.Result;

public class enterEpisodeInfo {
    
    public static Result run(Scanner reader) {
        System.out.println("+------------------------------------+");
        System.out.println("| Please Submit the Following Inputs |");
        System.out.println("+------------------------------------+");
        System.out.println("");
        
        System.out.println("Podcast Episode ID: ");
        int peID = reader.nextInt();
        reader.nextLine();

        System.out.println("Podcast ID: ");
        int pID = reader.nextInt();
        reader.nextLine();

        System.out.println("Episode Title: ");
        String peTitle = reader.nextLine();

        System.out.println("Duration (hh:mm:ss): ");
        String peDuration = reader.nextLine();

        System.out.println("Release Date (yyyy-mm-dd): ");
        String peReleaseDate = reader.nextLine();

        System.out.println("Listener Count: ");
        int listenerCount = reader.nextInt();
        reader.nextLine();

        System.out.println("Ad Count: ");
        int adCount = reader.nextInt();
        reader.nextLine();

        return execute(peID, pID, peTitle, peDuration, peReleaseDate, listenerCount, adCount);
    }

    public static Result execute(int peID, int pID, String peTitle, String peDuration, String peReleaseDate, int listenerCount, int adCount) {
        String sql = "INSERT INTO PodcastEpisodes (PEID, PID, PETitle, PEDuration, PEReleaseDate, ListenerCount, AdCount) VALUES (%d, %d, '%s', '%s', '%s', %d, %d);";

        sql = String.format(sql, peID, pID, peTitle, peDuration, peReleaseDate, listenerCount, adCount);

        return Connect.executeUpdate(sql);
    }

}
