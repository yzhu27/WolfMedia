package process;

import java.util.Scanner;
import java.sql.*;

import config.Connect;
import config.Result;

/**
 * This program is used to perform assignArtistToAlbum API operation.
 */

public class assignArtistToAlbum {

	public static Result run(Scanner reader) {

		System.out.println("| Please Submit the Following details: |");
		System.out.println("");

		System.out.println("Artist ID: ");
		int artistID = reader.nextInt();
		reader.nextLine();

		System.out.println("Album ID: ");
		int albumID = reader.nextInt();
		reader.nextLine();
		
		return execute(artistID, albumID);	
	}

	public static Result execute(int artistID, int albumID) {
		
		String sql = 
			"INSERT INTO Make VALUES "  + "\n" + "\t" +
				"(%s,%s)"  + "\n" +
			";" + "\n" + "\n"
		;
        
		sql = String.format(sql, artistID, albumID);
        
		return Connect.executeUpdate(sql);
	}

	public static void main(String[] args) {
		
	}

}
