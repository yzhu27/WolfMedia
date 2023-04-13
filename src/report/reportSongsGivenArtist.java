package report;
import java.sql.SQLException;
import java.util.Scanner;

import util.DBTablePrinter;
import util.*;


public class reportSongsGivenArtist {


    public static String execute(int ArtistID) {

		String sql = 
			"SELECT * FROM Songs WHERE ArtistID=%d;"
		;
        
		sql = String.format(sql, ArtistID);
        
		return queryExecuter.execute(sql);
	}

    public static String run(Scanner reader) throws SQLException {
        System.out.println("+------------------------------------+");
		System.out.println("|           Artist Details           |");
		System.out.println("+------------------------------------+");
		System.out.println("");

		DBTablePrinter.printTable("Artists");

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
