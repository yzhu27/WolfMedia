package report;
import java.util.Scanner;

import util.queryExecuter;


public class MonthlyPlayCountPerArtist {
	public static void showDetails(String tableName){
        String sql = String.format("SELECT * FROM " + tableName + ";");
		queryExecuter.execute(sql);
    }

	public static String execute(int ArtistID) {
		String sql = 
		"SELECT * FROM " +
		"(SELECT SUM(SRPlaycount) AS sum, Title, ArtistID, SRDate FROM  " +
		"(SELECT s.* FROM Artists as al  " +
		"JOIN Songs as s ON al.ArtistID=s.ArtistID) AS songalbum " +
		"JOIN SongRecords as sr on sr.SongID=songalbum.SongID GROUP BY SRDate, ArtistID ) as summ " +
		"WHERE ArtistID=%d; ";

		sql = String.format(sql, ArtistID);
		return queryExecuter.execute(sql);
	}

	public static String run(Scanner reader) {
		System.out.println("+------------------------------------+");
		System.out.println("|            Artist Details          |");
		System.out.println("+------------------------------------+");
		System.out.println("");

		showDetails("Artists");

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
