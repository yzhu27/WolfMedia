package process;

import java.util.Scanner;
import java.sql.*;

import util.*;

/**
 * Class used for executing the assignSongToArtist API operation.
 */
public class assignSongToArtist {

	public static String run(Scanner reader) throws SQLException{
		System.out.println("+------------------------------------+");
		System.out.println("| Please Submit the Following Inputs |");
		System.out.println("+------------------------------------+");
		System.out.println("");

		System.out.println("Song ID: ");
		int songID = reader.nextInt();
		reader.nextLine();

		System.out.println("Artist ID: ");
		int artistID = reader.nextInt();
		reader.nextLine();
		
		return execute(songID, artistID);	
	}

	public static String execute(int songID, int artistID) {
		
		String sql = 
			"INSERT INTO Collaborate VALUES " +
				"(%s,%s);";
        
		sql = String.format(sql, songID, artistID);
        
		return queryExecuter.execute(sql);
	}

	public static void main(String[] args) {
		
	}

}
