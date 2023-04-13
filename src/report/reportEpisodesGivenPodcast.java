package report;

import java.sql.SQLException;
import java.util.Scanner;

import util.DBTablePrinter;
import util.queryExecuter;

public class reportEpisodesGivenPodcast {

	/**
	 * Executes the SQL query to retrieve all the episodes of the given podcast.
	 * 
	 * @param PID the ID of the podcast to retrieve episodes for
	 * @return a string containing the query result
	 */
	public static String execute(int PID) {

		String sql = "SELECT * FROM PodcastEpisodes WHERE PID=%d;";

		sql = String.format(sql, PID);

		return queryExecuter.execute(sql);
	}

	/**
	 * Runs the reportEpisodesGivenPodcast program. Prompts the user for the PID of the podcast to generate a report for.
	 * 
	 * @param reader a Scanner object to read user input
	 * @return a string containing the query result
	 * @throws SQLException if there is an error executing the SQL query
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

		System.out.println("PID: ");
		int PID = reader.nextInt();
		reader.nextLine();

		return execute(PID);
	}
}
