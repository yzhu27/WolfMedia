package process;

import java.util.Scanner;

import util.queryExecuter;
import config.Result;

public class deletePodcastInfo {

    public static void showDetails(String tableName){
        String sql = String.format("SELECT * FROM " + tableName + ";");
        System.out.println("sql::" + sql);
		queryExecuter.execute(sql);
    }

    public static Result execute(int podcastID) {

        Result result = null;

        String sql = 
			"DELETE FROM Podcasts WHERE PID = %d"  + "\n" + "\t" +
				 "\n" +
			";" + "\n" + "\n"
		;
        
		sql = String.format(sql, podcastID);

		return queryExecuter.execute(sql);
	}

	public static void main(String[] args) {
		
	}



    public static Result run(Scanner reader) {

		System.out.println("+------------------------------------+");
		System.out.println("|         Podcast Details               |");
		System.out.println("+------------------------------------+");
		System.out.println("");

        showDetails("Podcasts");

		System.out.println("+------------------------------------+");
		System.out.println("| Please Submit the Following Inputs |");
		System.out.println("+------------------------------------+");
		System.out.println("");

		System.out.println("Podcast ID: ");
		int podcastID = reader.nextInt();
		reader.nextLine();

		return execute(podcastID);	
	}

}
