package report;
import java.util.Scanner;
import java.sql.*;
import util.DBTablePrinter;
import util.*;


public class prevMonthlyPlayCountPerSong {


    public static String execute(int SongID) {

		String sql = 
			"SELECT SRDate, SRPlaycount FROM SongRecords WHERE SongID=%d;"
		;
        
		sql = String.format(sql, SongID);
        
		return queryExecuter.execute(sql);
	}

    public static String run(Scanner reader) throws SQLException{
        System.out.println("+------------------------------------+");
		System.out.println("|             Song Details           |");
		System.out.println("+------------------------------------+");
		System.out.println("");

		DBTablePrinter.printTable("Songs");

		System.out.println("+------------------------------------+");
		System.out.println("| Please Submit the Following Inputs |");
		System.out.println("+------------------------------------+");
		System.out.println("");

		System.out.println("Song ID: ");
		int SongID = reader.nextInt();
		reader.nextLine();

		return execute(SongID);	
	}
}
