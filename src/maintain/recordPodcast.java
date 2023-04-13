package maintain;

import util.queryExecuter;

import java.util.Scanner;

public class recordPodcast {
    public static String run(Scanner reader) {
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
        float PRating = reader.nextFloat();
        reader.nextLine();

        return execute(PRDate, PID, PRSubscribers, PRating);
    }
    public static String execute(String Date, int ID, int subs, float rating) {

        String sql =
            "INSERT INTO PodcastRecords VALUES " +
            "('%s', %d, %d, %.2f) " +
            ";"
        ;
        sql = String.format(sql, Date, ID, subs, rating);

        return queryExecuter.execute(sql);
    }
}
