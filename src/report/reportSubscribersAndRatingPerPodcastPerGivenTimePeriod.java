package report;

import java.sql.SQLException;
import java.util.Scanner;

import util.DBTablePrinter;
import util.queryExecuter;

public class reportSubscribersAndRatingPerPodcastPerGivenTimePeriod {

	/**
	 * This method retrieves the number of subscribers and rating for a given podcast within a given time period.
	 * 
	 * @param PID the ID of the podcast
	 * @param startDate the start date of the time period (in the format YYYY-MM-DD)
	 * @param endDate the end date of the time period (in the format YYYY-MM-DD)
	 * @return a string containing the number of subscribers and rating for the given podcast within the given time period
	 */
	public static String execute(int PID, String startDate, String endDate) {

		String sql = "SELECT PRSubscribers,PRRating FROM PodcastRecords " + "\n" +
				"WHERE PID=%d" + "\n" +
				"AND PRDate BETWEEN '%s' AND '%s';";

		sql = String.format(sql, PID, startDate, endDate);

		return queryExecuter.execute(sql);
	}

	/**
	 * This method runs the reportSubscribersAndRatingPerPodcastPerGivenTimePeriod class.
	 * 
	 * @param reader a scanner object for user input
	 * @return a string containing the number of subscribers and rating for the given podcast within the given time period
	 * @throws SQLException if a database access error occurs
	 */
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
