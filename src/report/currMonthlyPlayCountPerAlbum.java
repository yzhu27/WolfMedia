package report;
import java.sql.SQLException;
import java.util.Scanner;

import util.DBTablePrinter;
import util.queryExecuter;


public class currMonthlyPlayCountPerAlbum {

	/**
	 * Executes the SQL query to calculate current monthly play count for a given album ID.
	 * @param AlbumID the ID of the album to calculate the monthly play count for
	 * @return the result of the SQL query as a string
	 */
	public static String execute(int AlbumID) {
		String sql = 
		"SELECT sum AS Playcount, AlbumID, AlName FROM " +
		" (SELECT SUM(Playcount) as sum, s.AlbumID, AlName " + 
		"FROM Albums as al JOIN Songs as s on al.AlbumID=s.AlbumID GROUP BY AlbumID) as summ " +
		"WHERE AlbumID=%d; ";

		sql = String.format(sql, AlbumID);
		return queryExecuter.execute(sql);
	}

	/**
	 * Gets user input and calls the execute method with the input album ID.
	 * @param reader a scanner object to read user input
	 * @return the result of the execute method as a string
	 * @throws SQLException if there is an error executing the SQL query
	 */
	public static String run(Scanner reader) throws SQLException{
		System.out.println("+------------------------------------+");
		System.out.println("|            Artist Details          |");
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
