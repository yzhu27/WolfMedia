import java.util.Scanner;
import java.sql.*;
/**
 * Class used for executing the assignArtistToAlbum API operation.
 */
public class assignArtistToAlbum {

	public static ExecResult run(Scanner reader) {
		System.out.println("+------------------------------------+");
		System.out.println("| Please Submit the Following Inputs |");
		System.out.println("+------------------------------------+");
		System.out.println("");

		System.out.println("Artist ID: ");
		int artistID = reader.nextInt();
		reader.nextLine();

		System.out.println("Album ID: ");
		int albumID = reader.nextInt();
		reader.nextLine();
		
		return execute(artistID, albumID);	
	}

	public static ExecResult execute(int artistID, int albumID) {
		
		String sql = 
			"INSERT INTO Make VALUES "  + "\n" + "\t" +
				"(%s,%s)"  + "\n" +
			";" + "\n" + "\n"
		;
        
		sql = String.format(sql, artistID, albumID);
        
		return WolfPubDB.executeUpdate(sql);
	}

	public static void main(String[] args) {
		System.out.println("\n");
		System.out.println("Unit Test for assignArtistToAlbum");
		System.out.println("===============================");
		//execute(4, 8);
	}

}
