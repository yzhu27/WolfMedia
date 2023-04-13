package report;

import java.sql.SQLException;
import java.util.Scanner;

import util.queryExecuter;

public class calTotalRevenue {
	/**
	 * Executes the SQL query to calculate the total revenue between a given time period.
	 * @param startDate the start date of the time period as a string in the format "YYYY-MM-DD"
	 * @param endDate the end date of the time period as a string in the format "YYYY-MM-DD"
	 * @return the total revenue generated between the given time period as a string
	 */
	public static String execute(String startDate, String endDate) {

		String sql = "SELECT SUM(RevAmount) FROM RevenueRecords " + "\n" +
				"WHERE RevDate between '%s' and '%s';";

		sql = String.format(sql, startDate, endDate);

		return queryExecuter.execute(sql);
	}

	/**
	 * Takes user input for start and end dates of the time period, and returns the total revenue generated in that time period.
	 * @param reader a scanner object for reading user input
	 * @return the total revenue generated between the given time period as a string
	 * @throws SQLException if there is an error executing the SQL query
	 */
	public static String run(Scanner reader) throws SQLException {

		System.out.println("+------------------------------------+");
		System.out.println("| Please Submit the Following Inputs |");
		System.out.println("+------------------------------------+");
		System.out.println("");

		System.out.println("Start Date (YYYY-MM-DD): ");
		String startDate = reader.nextLine();
		reader.nextLine();

		System.out.println("End Date (YYYY-MM-DD): ");
		String endDate = reader.nextLine();
		reader.nextLine();

		return execute(startDate, endDate);
	}
}
