package report;
import java.sql.SQLException;
import java.util.Scanner;

import util.DBTablePrinter;
import util.queryExecuter;


public class currMonthlyPlayCountPerArtist {

	/**
	 * Executes the SQL query to get current monthly play count per artist for a given artist ID.
	 * 
	 * @param ArtistID the artist ID for which monthly play count is to be calculated
	 * @return a String containing the results of the query
	 */
	public static String execute(int ArtistID) {
		String sql = 
		"SELECT sum AS Playcount, ArtistID, Name FROM " +
		" (SELECT SUM(Playcount) as sum, s.ArtistID, Name " + 
		"FROM Artists as ar JOIN Songs as s on ar.ArtistID=s.ArtistID GROUP BY ArtistID) as summ " +
		"WHERE ArtistID=%d; ";

		sql = String.format(sql, ArtistID);
		return queryExecuter.execute(sql);
	}
	
	/**
	 * Gets input from the user and executes the query to get the monthly play count per artist.
	 * 
	 * @param reader a Scanner object to read input from the user
	 * @return a String containing the results of the query
	 * @throws SQLException if there is an error executing the SQL query
	 */
	public static String run(Scanner reader) throws SQLException{
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

		return execute(ArtistID);	
	}
}
