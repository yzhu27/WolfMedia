package report;

import java.util.Scanner;
import java.sql.*;
import util.DBTablePrinter;
import util.*;


public class reportSongsGivenAlbum {


    public static String execute(int AlbumID) {

		String sql = 
			"SELECT * FROM Songs WHERE AlbumID = %d;";
        
		sql = String.format(sql, AlbumID);
        
		return queryExecuter.execute(sql);
	}

    public static String run(Scanner reader) throws SQLException{
        System.out.println("+------------------------------------+");
		System.out.println("|            Album Details           |");
		System.out.println("+------------------------------------+");
		System.out.println("");

		DBTablePrinter.printTable("Albums");

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
