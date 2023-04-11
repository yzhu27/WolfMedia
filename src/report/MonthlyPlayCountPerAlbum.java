package report;
import java.util.Scanner;

import config.Connect;
import config.Result;

public class MonthlyPlayCountPerAlbum {
	public static void showDetails(String tableName){
        String sql = String.format("SELECT * FROM " + tableName + ";");
		Connect.executeQuery(sql);
    }

	public static Result execute(int AlbumID) {
		String sql = 
		"SELECT summ.AlbumID, summ.AlName, summ.sum FROM " +
		"(SELECT SUM(s.Playcount) as sum, ar.* " +
		"FROM Albums AS ar " +
		"JOIN Songs AS s ON ar.AlbumID=s.AlbumID GROUP BY AlbumID) as summ " +
		"WHERE AlbumID=%d;";

		sql = String.format(sql, AlbumID);
		return Connect.executeQuery(sql);
	}

	public static Result run(Scanner reader) {
		System.out.println("+------------------------------------+");
		System.out.println("|            Artist Details          |");
		System.out.println("+------------------------------------+");
		System.out.println("");

		showDetails("Albums");

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
