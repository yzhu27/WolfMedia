package report;

import java.sql.SQLException;
import java.util.Scanner;

import util.queryExecuter;

public class calTotalPaymentsToHostPerGivenTimePeriod {

	/**
	* Executes a SQL query to calculate the total payments to host during a given time period.
	*
	* @param startDate the start date of the time period (in the format YYYY-MM-DD).
	* @param endDate the end date of the time period (in the format YYYY-MM-DD).
	* @return a string representing the result of the SQL query.
	*/
	public static String execute(String startDate, String endDate) {

		String sql = "SELECT SUM(PayAmount) FROM HostPaymentRecords " + "\n" +
				"WHERE PayDate between '%s' and '%s';";

		sql = String.format(sql, startDate, endDate);

		return queryExecuter.execute(sql);
	}

	/**
	 * Runs the calTotalPaymentsToHostPerGivenTimePeriod report.
	 *
	 * @param reader a scanner object for input.
	 * @return a string representing the result of the SQL query.
	 * @throws SQLException if an SQL exception occurs.
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
