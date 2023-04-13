package report;
import java.util.Scanner;

import util.queryExecuter;

import util.queryExecuter;

public class reportEpisodesGivenPodcast {
    public static void showDetails(String tableName){
        String sql = String.format("SELECT * FROM " + tableName + ";");
		queryExecuter.execute(sql);
    }

    public static String execute(int PID) {

		String sql = 
			"SELECT * FROM PodcastEpisodes WHERE PID=%d;"
		;
        
		sql = String.format(sql, PID);
        
		return queryExecuter.execute(sql);
	}

    public static String run(Scanner reader) {
        System.out.println("+------------------------------------+");
		System.out.println("|           Podcasts Details         |");
		System.out.println("+------------------------------------+");
		System.out.println("");

		showDetails("Podcasts");

		System.out.println("+------------------------------------+");
		System.out.println("| Please Submit the Following Inputs |");
		System.out.println("+------------------------------------+");
		System.out.println("");

		System.out.println("PID: ");
		int PID = reader.nextInt();
		reader.nextLine();

		return execute(PID);
	}
}
