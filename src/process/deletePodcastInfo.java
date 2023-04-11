package process;

import java.util.Scanner;

import config.Connect;
import config.Result;

/**
 * This program is used to perform deletePodcastInfo API operation by updating the Podcasts table.
 */

public class deletePodcastInfo {

    public static void showDetails(String tableName){
        String sql = String.format("SELECT * FROM " + tableName + ";");
		Connect.executeQuery(sql);
    }

    public static Result execute(int podcastID) {


        String sql = 
			"DELETE FROM Podcasts WHERE PID = %d"  + "\n" + "\t" +
				 "\n" +
			";" + "\n" + "\n"
		;
        
		sql = String.format(sql, podcastID);

		return Connect.executeUpdate(sql);
	}

	public static void main(String[] args) {
		
	}



    public static Result run(Scanner reader) {

		System.out.println("+------------------------------------+");
		System.out.println("|         Podcast Table Details      |");
		System.out.println("+------------------------------------+");
		System.out.println("");

        showDetails("Podcasts");

		System.out.println("| Please Submit the following details: |");


		System.out.println("Podcast ID: ");
		int podcastID = reader.nextInt();
		reader.nextLine();

		return execute(podcastID);	
	}

}
