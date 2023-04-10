import java.util.Scanner;
import java.sql.*;
/**
 * Class used for executing the assignSongToArtist API operation.
 */
public class assignSongToArtist {

	public static ExecResult run(Scanner reader) {
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

	public static ExecResult execute(int songID, int artistID) {
		
		String sql = 
			"INSERT INTO Edits VALUES "  + "\n" + "\t" +
				"(%s,%s)"  + "\n" +
			";" + "\n" + "\n"
		;
        
		sql = String.format(sql, songID, artistID);
        
		return WolfPubDB.executeUpdate(sql);
	}

	public static void main(String[] args) {
		System.out.println("\n");
		System.out.println("Unit Test for assignSongToArtist");
		System.out.println("===============================");
		//execute(4, 8);
	}

}
