package report;

import java.sql.SQLException;
import java.util.Scanner;

import util.DBTablePrinter;
import util.queryExecuter;

public class reportSongsGivenAlbum {

	/**
	 * This method executes an SQL query to retrieve all songs belonging to a particular album.
	 * @param AlbumID an integer representing the unique ID of the album to retrieve songs from
	 * @return a string representation of the SQL query results
	 */
	public static String execute(int AlbumID) {

		String sql = "SELECT * FROM Songs WHERE AlbumID = %d;";

		sql = String.format(sql, AlbumID);

		return queryExecuter.execute(sql);
	}

	/**
	 * This method prompts the user to input an album ID and returns the results of the SQL query to retrieve all songs belonging to the album.
	 * @param reader a Scanner object to read user input
	 * @return a string representation of the SQL query results
	 * @throws SQLException if a database access error occurs
	 */
	public static String run(Scanner reader) throws SQLException {
		System.out.println("+------------------------------------+");
		System.out.println("|            Album Details           |");
		System.out.println("+------------------------------------+");
		System.out.println("");

		DBTablePrinter.printTable("Albums");

		System.out.println("+------------------------------------+");
		System.out.println("| Please Submit the Following Inputs |");
		System.out.println("+------------------------------------+");
		System.out.println("");

		System.out.println("Album ID: ");
		int AlbumID = reader.nextInt();
		reader.nextLine();

		return execute(AlbumID);
	}
}
