package process;

import java.util.Scanner;

import util.*;
import java.sql.*;

public class deleteSongInfo {



    public static String execute(int songID) {

        String result = null;

        String sql = 
			"DELETE FROM Songs WHERE SongID = %d"  + "\n" + "\t" +
				 "\n" +
			";" + "\n" + "\n"
		;
        
		sql = String.format(sql, songID);

		return queryExecuter.execute(sql);
	}

	public static void main(String[] args) {
		
	}



    public static String run(Scanner reader) throws SQLException{

		System.out.println("+------------------------------------+");
		System.out.println("|         Song Details               |");
		System.out.println("+------------------------------------+");
		System.out.println("");

        DBTablePrinter.printTable("Songs");

		System.out.println("+------------------------------------+");
		System.out.println("| Please Submit the Following Inputs |");
		System.out.println("+------------------------------------+");
		System.out.println("");

		System.out.println("Song ID: ");
		int songID = reader.nextInt();
		reader.nextLine();

		return execute(songID);	
	}

}
