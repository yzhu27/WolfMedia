package report;
import java.util.Scanner;
import java.sql.*;
import util.DBTablePrinter;
import util.*;


public class reportEpisodesGivenPodcast {


    public static String execute(int PID) {

		String sql = 
			"SELECT * FROM PodcastEpisodes WHERE PID=%d;"
		;
        
		sql = String.format(sql, PID);
        
		return queryExecuter.execute(sql);
	}

    public static String run(Scanner reader) throws SQLException{
        System.out.println("+------------------------------------+");
		System.out.println("|           Podcasts Details         |");
		System.out.println("+------------------------------------+");
		System.out.println("");

		DBTablePrinter.printTable("Podcasts");

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
