package process;

import java.util.Scanner;

import config.Connect;
import config.Result;

/**
 * This program is used to perform assignEpisodeToPodcast API operation by updating the PodcastEpisodes table.
 */

public class assignEpisodeToPodcast {

    public static Result execute(String sql) {
        return Connect.executeUpdate(sql);
    }

    public static void showDetails(String tableName){
        String sql = String.format("SELECT * FROM " + tableName + ";");
        Connect.executeQuery(sql);
    }

    public static Result run(Scanner reader) {
        System.out.println("+-------------------------------------------+");
        System.out.println("|           PodcastEpisodes Table Details   |");
        System.out.println("+-------------------------------------------+");
        System.out.println("");

        showDetails("PodcastEpisodes");

        System.out.println("| Please Submit the following details: |");

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
