package report;
import java.util.Scanner;

import config.Connect;
import config.Result;

public class reportSongsGivenAlbum {
    public static void showDetails(String tableName){
        String sql = String.format("SELECT * FROM " + tableName + ";");
		Connect.executeQuery(sql);
    }

    public static Result execute(int AlbumID) {

		String sql = 
			"SELECT * FROM Songs WHERE AlbumID = %d;";
        
		sql = String.format(sql, AlbumID);
        
		return Connect.executeQuery(sql);
	}

    public static Result run(Scanner reader) {
        System.out.println("+------------------------------------+");
		System.out.println("|            Album Details           |");
		System.out.println("+------------------------------------+");
		System.out.println("");

		showDetails("Albums");

		System.out.println("+------------------------------------+");
		System.out.println("| Please Submit the Following Inputs |");
		System.out.println("+------------------------------------+");
		System.out.println("");

		System.out.println("Artist ID: ");
		int AlbumID = reader.nextInt();
		reader.nextLine();

		return execute(AlbumID);	
	}
}
