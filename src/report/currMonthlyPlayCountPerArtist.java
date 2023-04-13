package report;
import java.util.Scanner;
import java.sql.*;
import util.DBTablePrinter;
import util.*;


public class currMonthlyPlayCountPerArtist {

	public static String execute(int ArtistID) {
		String sql = 
		"SELECT sum AS Playcount, ArtistID, Name FROM " +
		" (SELECT SUM(Playcount) as sum, s.ArtistID, Name " + 
		"FROM Artists as ar JOIN Songs as s on ar.ArtistID=s.ArtistID GROUP BY ArtistID) as summ " +
		"WHERE ArtistID=%d; ";

		sql = String.format(sql, ArtistID);
		return queryExecuter.execute(sql);
	}

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
