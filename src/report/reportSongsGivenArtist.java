package report;

import java.sql.SQLException;
import java.util.Scanner;

import util.DBTablePrinter;
import util.*;

public class reportSongsGivenArtist {

	/**
	 * Executes a SQL query to retrieve all the songs belonging to a specific artist.
	 *
	 * @param ArtistID the ID of the artist to retrieve songs for.
	 * @return a string containing the results of the query.
	 */
	public static String execute(int ArtistID) {

		String sql = "SELECT * FROM Songs WHERE ArtistID=%d;";

		sql = String.format(sql, ArtistID);

		return queryExecuter.execute(sql);
	}

	/**
	 * Prompts the user for an artist ID and generates a report of all the songs belonging to that artist.
	 *
	 * @param reader a scanner object for reading user input.
	 * @return a string containing the results of the SQL query.
	 * @throws SQLException if there is an error executing the SQL query.
	 */
	public static String run(Scanner reader) throws SQLException {
		System.out.println("+------------------------------------+");
		System.out.println("|           Artist Details           |");
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

		return execute(ArtistID);
	}
}
