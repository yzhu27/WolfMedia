package report;

import java.sql.SQLException;
import java.util.Scanner;

import util.DBTablePrinter;
import util.queryExecuter;

public class MonthlyPlayCountPerSong {

	/**
	 * Executes the query to get the monthly play count of the song with the given SongID.
	 *
	 * @param SongID The ID of the song to get monthly play count for.
	 * @return A string containing the monthly play count of the song.
	 */
	public static String execute(int SongID) {

		String sql = "SELECT Playcount FROM Songs WHERE SongID=%d;";

		sql = String.format(sql, SongID);

		return queryExecuter.execute(sql);
	}

	/**
	 * Prompts the user to input a song ID and returns the monthly play count for that song.
	 *
	 * @param reader The Scanner object to read user input.
	 * @return A string containing the monthly play count of the song.
	 * @throws SQLException If there's an error executing the SQL query.
	 */
	public static String run(Scanner reader) throws SQLException {
		System.out.println("+------------------------------------+");
		System.out.println("|             Song Details           |");
		System.out.println("+------------------------------------+");
		System.out.println("");

		DBTablePrinter.printTable("Songs");

		System.out.println("+------------------------------------+");
		System.out.println("| Please Submit the Following Inputs |");
		System.out.println("+------------------------------------+");
		System.out.println("");

		System.out.println("Song ID: ");
		int SongID = reader.nextInt();
		reader.nextLine();

		return execute(SongID);
	}
}
