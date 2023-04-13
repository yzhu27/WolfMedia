package report;
import java.util.Scanner;

import util.queryExecuter;


public class reportSongsGivenArtist {
    public static void showDetails(String tableName){
        String sql = String.format("SELECT * FROM " + tableName + ";");
		queryExecuter.execute(sql);
    }

    public static String execute(int ArtistID) {

		String sql = 
			"SELECT * FROM Songs WHERE ArtistID=%d;"
		;
        
		sql = String.format(sql, ArtistID);
        
		return queryExecuter.execute(sql);
	}

    public static String run(Scanner reader) {
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
