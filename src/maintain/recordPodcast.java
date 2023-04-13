package maintain;

import util.*;
import java.sql.*;
import java.util.Scanner;


/**
 *  This Class is used to add new podcast record to the PpodcastRecords table.
 */

public class recordPodcast {


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
