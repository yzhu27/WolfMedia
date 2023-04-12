package process;

import java.util.Scanner;

import util.queryExecuter;
import config.Result;

public class deleteAlbumInfo {

    public static void showDetails(String tableName){
        String sql = String.format("SELECT * FROM " + tableName + ";");
        System.out.println("sql::" + sql);
		queryExecuter.execute(sql);
    }

    public static Result execute(int albumID) {

        Result result = null;

        String sql = "DELETE FROM Albums WHERE AlbumID = %d;";
        
		sql = String.format(sql, albumID);

		return queryExecuter.execute(sql);
	}

	public static void main(String[] args) {
		
	}



    public static Result run(Scanner reader) {

		System.out.println("+------------------------------------+");
		System.out.println("|         Album Details               |");
		System.out.println("+------------------------------------+");
		System.out.println("");

        showDetails("Albums");

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
