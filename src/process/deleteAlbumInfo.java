package process;

import java.util.Scanner;

import config.Connect;
import config.Result;

/**
 * This program is used to perform deleteAlbumInfo API operation to delete the Album row in the table.
 */

public class deleteAlbumInfo {

    public static void showDetails(String tableName){
        String sql = String.format("SELECT * FROM " + tableName + ";");
        System.out.println("sql>" + sql);
		Connect.executeQuery(sql);
    }

    public static Result execute(int albumID) {


        String sql = 
			"DELETE FROM Albums WHERE AlbumID = %d"  + "\n" + "\t" +
				 "\n" +
			";" + "\n" + "\n"
		;
        
		sql = String.format(sql, albumID);

		return Connect.executeUpdate(sql);
	}

	public static void main(String[] args) {
		
	}



    public static Result run(Scanner reader) {

		System.out.println("+-------------------------------------+");
		System.out.println("|         Album Table Details         |");
		System.out.println("+-------------------------------------+");
		System.out.println("");

        	showDetails("Albums");

		System.out.println("| Please Submit the following details: |");

		System.out.println("Album ID: ");
		int albumID = reader.nextInt();
		reader.nextLine();

		return execute(albumID);	
	}

}
