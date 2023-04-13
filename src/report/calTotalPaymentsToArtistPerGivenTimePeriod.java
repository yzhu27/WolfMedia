package report;

import java.sql.SQLException;
import java.util.Scanner;

import util.DBTablePrinter;
import util.queryExecuter;

public class calTotalPaymentsToArtistPerGivenTimePeriod {

	/**
	 * Executes the SQL query to calculate the total payment amount made 
	 * to the artist during the given time period.
	 * 
	 * @param startDate a String representing the start date of the time period in the format of "YYYY-MM-DD".
	 * @param endDate a String representing the end date of the time period in the format of "YYYY-MM-DD".
	 * @return a String representing the result of the query execution.
	 */
	public static String execute(int ArtistID, String startDate, String endDate) {

		String sql = "SELECT SUM(PayAmount) FROM ArtistPaymentRecords " +
				"WHERE ArtistID = %d AND PayDate BETWEEN '%s' AND '%s';";

		sql = String.format(sql, ArtistID, startDate, endDate);

		return queryExecuter.execute(sql);
	}

	/**
	 * Runs the program by prompting the user to enter the start date and end date of the time period, 
	 * and executing the SQL query to calculate the total payment amount made to the artist during the given time period.
	 * 
	 * @param reader a Scanner object used to read user input from the console.
	 * @return a String representing the result of the query execution.
	 * @throws SQLException if a database access error occurs or this method is called on a closed connection.
	 */
	public static String run(Scanner reader) throws SQLException {
		
		System.out.println("+------------------------------------+");
		System.out.println("|            Artist Details          |");
		System.out.println("+------------------------------------+");
		System.out.println("");

		DBTablePrinter.printTable("Artists");

		System.out.println("+------------------------------------+");
		System.out.println("| Please Submit the Following Inputs |");
		System.out.println("+------------------------------------+");
		System.out.println("");

		System.out.println("Artist ID: ");
		int ArtistID = reader.nextInt();
		reader.nextLine();

		System.out.println("Start Date (YYYY-MM-DD): ");
		String startDate = reader.nextLine();
		reader.nextLine();

		System.out.println("End Date (YYYY-MM-DD): ");
		String endDate = reader.nextLine();
		reader.nextLine();

		return execute(ArtistID, startDate, endDate);
	}
}
