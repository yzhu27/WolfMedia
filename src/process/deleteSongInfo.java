package process;

import java.util.Scanner;

import config.Connect;
import config.Result;

public class deleteSongInfo {

    public static void showDetails(String tableName){
        String sql = String.format("SELECT * FROM " + tableName + ";");
        System.out.println("sql::" + sql);
		Connect.executeQuery(sql);
    }

    public static Result execute(int songID) {


        String sql = 
			"DELETE FROM Songs WHERE SongID = %d"  + "\n" + "\t" +
				 "\n" +
			";" + "\n" + "\n"
		;
        
		sql = String.format(sql, songID);

		return Connect.executeUpdate(sql);
	}

	public static void main(String[] args) {
		
	}



    public static Result run(Scanner reader) {

		System.out.println("+------------------------------------+");
		System.out.println("|         Song Details               |");
		System.out.println("+------------------------------------+");
		System.out.println("");

        showDetails("Songs");

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
