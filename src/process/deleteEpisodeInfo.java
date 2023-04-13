package process;

import java.util.Scanner;

import util.*;
import java.sql.*;

public class deleteEpisodeInfo {



    public static String execute(int episodeID,int podcastID) {

        String result = null;

        String sql = 
			"DELETE FROM PodcastEpisodes WHERE PEID = %d AND PID = %d;";
        
		sql = String.format(sql, episodeID, podcastID);

		return queryExecuter.execute(sql);
	}

	public static void main(String[] args) {
		
	}



    public static String run(Scanner reader) throws SQLException{

		System.out.println("+------------------------------------+");
		System.out.println("|         Episode Details            |");
		System.out.println("+------------------------------------+");
		System.out.println("");

        DBTablePrinter.printTable("PodcastEpisodes");

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
