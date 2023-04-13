package report;

import java.sql.SQLException;
import java.util.Scanner;

import util.DBTablePrinter;
import util.queryExecuter;

public class calTotalPaymentsToLabelPerGivenTimePeriod {

	/**
	 * Executes the SQL query to calculate the total payments to a label within a given time period.
	 *
	 * @param startDate the start date of the time period.
	 * @param endDate the end date of the time period.
	 * @return a string containing the result of the SQL query.
	 */
	public static String execute(int RLID, String startDate, String endDate) {

		String sql = "SELECT SUM(PayAmount) FROM LabelPaymentRecords " + "\n" +
				"WHERE LabelID=%d AND PayDate between '%s' and '%s';";

		sql = String.format(sql, RLID, startDate, endDate);

		return queryExecuter.execute(sql);
	}

	/**
	 * Runs the process of calculating the total payments to a label within a given time period.
	 *
	 * @param reader the scanner object to read user inputs.
	 * @return a string containing the result of the SQL query.
	 * @throws SQLException if there's an error executing the SQL query.
	 */
	public static String run(Scanner reader) throws SQLException {

		System.out.println("+------------------------------------+");
		System.out.println("|            Label  Details          |");
		System.out.println("+------------------------------------+");
		System.out.println("");

		DBTablePrinter.printTable("RecordLabels");

		System.out.println("+------------------------------------+");
		System.out.println("| Please Submit the Following Inputs |");
		System.out.println("+------------------------------------+");
		System.out.println("");

		System.out.println("Label ID: ");
		int RLID = reader.nextInt();
		reader.nextLine();

		System.out.println("Start Date (YYYY-MM-DD): ");
		String startDate = reader.nextLine();
		reader.nextLine();

		System.out.println("End Date (YYYY-MM-DD): ");
		String endDate = reader.nextLine();
		reader.nextLine();

		return execute(RLID, startDate, endDate);
	}
}
