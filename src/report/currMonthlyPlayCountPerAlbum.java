package report;
import java.util.Scanner;
import java.sql.*;
import util.DBTablePrinter;
import util.*;


public class currMonthlyPlayCountPerAlbum {

	public static String execute(int AlbumID) {
		String sql = 
		"SELECT sum AS Playcount, AlbumID, AlName FROM " +
		" (SELECT SUM(Playcount) as sum, s.AlbumID, AlName " + 
		"FROM Albums as al JOIN Songs as s on al.AlbumID=s.AlbumID GROUP BY AlbumID) as summ " +
		"WHERE AlbumID=%d; ";

		sql = String.format(sql, AlbumID);
		return queryExecuter.execute(sql);
	}

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
