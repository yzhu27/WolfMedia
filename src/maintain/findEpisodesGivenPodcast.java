package maintain;

import config.Connect;
import config.Result;
import java.util.Scanner;

public class findEpisodesGivenPodcast {
    public static Result run(Scanner reader) {
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

    public static Result execute(int ID, String attribute) {

        String sql =
                "SELECT *" +
                "FROM PodcastEpisodes"  +
                "WHERE %s = %d" +
                ";"
                ;

        sql = String.format(sql, attribute, ID);
        return Connect.executeQuery(sql);
    }
}
