package report;
import java.sql.SQLException;
import java.util.Scanner;

import util.*;
import util.DBTablePrinter;

public class reportSubscribersAndRatingPerPodcastPerGivenTimePeriod {


    public static String execute(int PID, String startDate, String endDate) {

		String sql = 
            "SELECT PRSubscribers,PRRating FROM PodcastRecords " + "\n" +
            "WHERE PID=%d" + "\n" +
            "AND PRDate BETWEEN '%s' AND '%s';"
		;
        
		sql = String.format(sql, PID, startDate, endDate);
        
		return queryExecuter.execute(sql);
	}

    public static String run(Scanner reader) throws SQLException {
        System.out.println("+------------------------------------+");
		System.out.println("|           Podcasts Details         |");
		System.out.println("+------------------------------------+");
		System.out.println("");

		DBTablePrinter.printTable("Podcasts");

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
