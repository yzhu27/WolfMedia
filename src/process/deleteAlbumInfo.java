package process;

import java.util.Scanner;
import java.sql.*;
import util.*;

/**
 * Class used for executing the deleteAlbumInfo API operation by updating the Albums Table.
 */

public class deleteAlbumInfo {



    public static String execute(int albumID) {

        String result = null;

        String sql = "DELETE FROM Albums WHERE AlbumID = %d;";
        
		sql = String.format(sql, albumID);

		return queryExecuter.execute(sql);
	}

	public static void main(String[] args) {
		
	}



    public static String run(Scanner reader) throws SQLException{

		System.out.println("+------------------------------------+");
		System.out.println("|         Album Details               |");
		System.out.println("+------------------------------------+");
		System.out.println("");

        DBTablePrinter.printTable("Albums");

		System.out.println("+------------------------------------+");
		System.out.println("| Please Submit the Following Inputs |");
		System.out.println("+------------------------------------+");
		System.out.println("");

		System.out.println("Album ID: ");
		int albumID = reader.nextInt();
		reader.nextLine();

		return execute(albumID);	
	}

}
