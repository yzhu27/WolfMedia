package process;

import java.util.Scanner;

import util.*;
import java.sql.*;

public class deleteArtistInfo {


    public static String execute(int artistID) {

        String result = null;

        String sql = 
			"DELETE FROM Artists WHERE artistID = %d;";
        
		sql = String.format(sql, artistID);

		return queryExecuter.execute(sql);
	}

	public static void main(String[] args) {
		
	}



    public static String run(Scanner reader) throws SQLException{

		System.out.println("+------------------------------------+");
		System.out.println("|         Artist Details               |");
		System.out.println("+------------------------------------+");
		System.out.println("");

        DBTablePrinter.printTable("Artists");

		System.out.println("+------------------------------------+");
		System.out.println("| Please Submit the Following Inputs |");
		System.out.println("+------------------------------------+");
		System.out.println("");

		System.out.println("Artist ID: ");
		int artistID = reader.nextInt();
		reader.nextLine();

		return execute(artistID);	
	}

}
