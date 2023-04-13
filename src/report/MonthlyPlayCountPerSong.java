package report;
import java.util.Scanner;

import util.queryExecuter;


public class MonthlyPlayCountPerSong {
    public static void showDetails(String tableName){
        String sql = String.format("SELECT * FROM " + tableName + ";");
		queryExecuter.execute(sql);
    }

    public static String execute(int SongID) {

		String sql = 
			"SELECT Playcount FROM Songs WHERE SongID=%d;"
		;
        
		sql = String.format(sql, SongID);
        
		return queryExecuter.execute(sql);
	}

    public static String run(Scanner reader) {
        System.out.println("+------------------------------------+");
		System.out.println("|             Song Details           |");
		System.out.println("+------------------------------------+");
		System.out.println("");

		showDetails("Songs");

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
