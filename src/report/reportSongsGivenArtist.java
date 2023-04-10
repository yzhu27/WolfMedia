package report;
import java.util.Scanner;

import config.Connect;
import config.Result;
import config.Transaction;

public class reportSongsGivenArtist {
    public static void showDetails(String tableName){
        String sql = String.format("SELECT * FROM " + tableName + ";");
		Connect.executeQuery(sql);
    }

    public static Result execute(int ArtistID) {

		String sql = 
			"SELECT * FROM Songs WHERE ArtistID=%d;"
		;
        
		sql = String.format(sql, ArtistID);
        
		return Connect.executeUpdate(sql);
	}

    public static Result run(Scanner reader) {
        System.out.println("+------------------------------------+");
		System.out.println("|           Artist Details           |");
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
