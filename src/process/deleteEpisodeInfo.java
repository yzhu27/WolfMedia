package process;

import java.util.Scanner;

import config.Connect;
import config.Result;

public class deleteEpisodeInfo {

    public static void showDetails(String tableName){
        String sql = String.format("SELECT * FROM " + tableName + ";");
        System.out.println("sql::" + sql);
		Connect.executeQuery(sql);
    }

    public static Result execute(int episodeID,int podcastID) {

        Result result = null;

        String sql = 
			"DELETE FROM PodcastEpisodes WHERE PEID = %d AND PID = %d;";
        
		sql = String.format(sql, episodeID, podcastID);

		return Connect.executeUpdate(sql);
	}

	public static void main(String[] args) {
		
	}



    public static Result run(Scanner reader) {

		System.out.println("+------------------------------------+");
		System.out.println("|         Episode Details            |");
		System.out.println("+------------------------------------+");
		System.out.println("");

        showDetails("PodcastEpisodes");

		System.out.println("+------------------------------------+");
		System.out.println("| Please Submit the Following Inputs |");
		System.out.println("+------------------------------------+");
		System.out.println("");
      
    	System.out.println("Podcast ID: ");
    	int podcastID = reader.nextInt();
		reader.nextLine();

		System.out.println("Episode ID: ");
		int episodeID = reader.nextInt();
		reader.nextLine();

		return execute(episodeID, podcastID);	
	}

}
