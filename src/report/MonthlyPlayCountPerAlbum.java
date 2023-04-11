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
		"SELECT * FROM " +
		"(SELECT SUM(SRPlaycount) AS sum, Title, AlbumID, SRDate FROM  " +
		"(SELECT s.* FROM Albums as al  " +
		"JOIN Songs as s ON al.AlbumID=s.AlbumID) AS songalbum " +
		"JOIN SongRecords as sr on sr.SongID=songalbum.SongID GROUP BY SRDate, AlbumID ) as summ " +
		"WHERE AlbumID=%d; ";

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
