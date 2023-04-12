package process;

import java.util.Scanner;

import util.queryExecuter;
import config.Result;

public class deleteArtistInfo {

    public static void showDetails(String tableName){
        String sql = String.format("SELECT * FROM " + tableName + ";");
        System.out.println("sql::" + sql);
		    queryExecuter.execute(sql);
    }

    public static Result execute(int artistID) {

        Result result = null;

        String sql = 
			"DELETE FROM Artists WHERE artistID = %d;";
        
		sql = String.format(sql, artistID);

		return queryExecuter.execute(sql);
	}

	public static void main(String[] args) {
		
	}



    public static Result run(Scanner reader) {

		System.out.println("+------------------------------------+");
		System.out.println("|         Artist Details               |");
		System.out.println("+------------------------------------+");
		System.out.println("");

        showDetails("Artists");

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
