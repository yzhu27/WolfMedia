package maintain;

import config.Connect;
import config.Result;
import java.util.Scanner;

public class recordPodcast {
    public static Result run(Scanner reader) {
        System.out.println("+------------------------------------+");
        System.out.println("| Please Submit the Following Inputs |");
        System.out.println("+------------------------------------+");
        System.out.println("");

        System.out.println("PodcastID: ");
        int PID = reader.nextInt();
        reader.nextLine();

        System.out.println("PodcastRecordDate: YYYY-MM-DD");
        String PRDate = reader.nextLine();
        reader.nextLine();

        System.out.println("PodcastSubscribers: ");
        int PRSubscribers = reader.nextInt();
        reader.nextLine();

        System.out.println("PodcastRating: ");
        int PRating = reader.nextInt();
        reader.nextLine();

        return execute(PRDate, PID, PRSubscribers, PRating);
    }
    public static Result execute(String Date, int ID, int subs, int rating) {

        String sql =
            "INSERT INTO PodcastRecords VALUES " +
            "(%s, '%d', '%d', '%d') " +
            ";"
        ;
        sql = String.format(sql, Date, ID, subs, rating);

        return Connect.executeQuery(sql);
    }
}
