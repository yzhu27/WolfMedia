package process;

import java.util.Scanner;

import config.Connect;
import config.Result;

/**
 * This program is used to perform deleteHostInfo API operation by updating the PodcastHosts table.
 */

public class deleteHostInfo {

    public static void showDetails(String tableName){
        String sql = String.format("SELECT * FROM " + tableName + ";");
		Connect.executeQuery(sql);
    }

    public static Result execute(int hostID) {


        String sql = 
			"DELETE FROM PodcastHosts WHERE PHID = %d"  + "\n" + "\t" +
				 "\n" +
			";" + "\n" + "\n"
		;
        
		sql = String.format(sql, hostID);

		return Connect.executeUpdate(sql);
	}

	public static void main(String[] args) {

	}



    public static Result run(Scanner reader) {

		System.out.println("+------------------------------------+");
		System.out.println("|         Podcast Host Details       |");
		System.out.println("+------------------------------------+");
		System.out.println("");

        showDetails("PodcastHosts");

		System.out.println("| Please Submit the following details: |");


		System.out.println("Host ID: ");
		int hostID = reader.nextInt();
		reader.nextLine();

		return execute(hostID);	
	}

}
