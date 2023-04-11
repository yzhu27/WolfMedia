package report;
import java.util.Scanner;

import config.Connect;
import config.Result;

public class MonthlyPlayCountPerArtist {
	public static void showDetails(String tableName){
        String sql = String.format("SELECT * FROM " + tableName + ";");
		Connect.executeQuery(sql);
    }

	public static Result execute(int ArtistID) {
		String sql = 
		"SELECT summ.ArtistID, summ.Name, summ.sum FROM " +
		"(SELECT SUM(s.Playcount) as sum, ar.* " +
		"FROM Artists AS ar " +
		"JOIN Songs AS s ON ar.ArtistID=s.ArtistID GROUP BY ArtistID) as summ " +
		"WHERE ArtistID=%d;";

		sql = String.format(sql, ArtistID);
		return Connect.executeQuery(sql);
	}

	public static Result run(Scanner reader) {
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
