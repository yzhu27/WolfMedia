package process;

import java.util.Scanner;
import java.sql.*;

import config.Connect;
import config.Result;

public class assignEpisodeToPodcast {

    public static Result execute(String sql) {
        return Connect.executeUpdate(sql);
    }

    public static void showDetails(String tableName){
        String sql = String.format("SELECT * FROM " + tableName + ";");
        Connect.executeQuery(sql);
    }

    public static Result run(Scanner reader) {
        System.out.println("+-------------------------------+");
        System.out.println("|      PodcastEpisode Details   |");
        System.out.println("+-------------------------------+");
        System.out.println("");

        showDetails("PodcastEpisodes");

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

        String sql = "";
        sql = "UPDATE PodcastEpisodes SET PID=%d WHERE PEID=(%d);" + "\n" + "\n";
        sql = String.format(sql, pID, peID);

        return execute(sql);
    }
}
