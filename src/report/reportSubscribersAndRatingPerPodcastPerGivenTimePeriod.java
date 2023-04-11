package report;
import java.util.Scanner;

import config.Connect;
import config.Result;

public class reportSubscribersAndRatingPerPodcastPerGivenTimePeriod {
    public static void showDetails(String tableName){
        String sql = String.format("SELECT * FROM " + tableName + ";");
		Connect.executeQuery(sql);
    }

    public static Result execute(int PID, String startDate, String endDate) {

		String sql = 
            "SELECT PRSubscribers,PRRating FROM PodcastRecords " + "\n" +
            "WHERE PID=%d" + "\n" +
            "AND PRDate BETWEEN '%s' AND '%s';"
		;
        
		sql = String.format(sql, PID, startDate, endDate);
        
		return Connect.executeQuery(sql);
	}

    public static Result run(Scanner reader) {
        System.out.println("+------------------------------------+");
		System.out.println("|           Podcasts Details         |");
		System.out.println("+------------------------------------+");
		System.out.println("");

		showDetails("Podcasts");

		System.out.println("+------------------------------------+");
		System.out.println("| Please Submit the Following Inputs |");
		System.out.println("+------------------------------------+");
		System.out.println("");

		System.out.println("Podcast ID: ");
		int PID = reader.nextInt();
		reader.nextLine();

		System.out.println("Start Date (YYYY-MM-DD): ");
		String startDate = reader.nextLine();
		reader.nextLine();

        System.out.println("End Date (YYYY-MM-DD): ");
		String endDate = reader.nextLine();
        reader.nextLine();

		return execute(PID, startDate, endDate);	
    }
}
