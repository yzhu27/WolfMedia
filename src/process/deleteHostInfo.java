package process;

import java.util.Scanner;

import util.queryExecuter;
import config.Result;

public class deleteHostInfo {

    public static void showDetails(String tableName){
        String sql = String.format("SELECT * FROM " + tableName + ";");
        System.out.println("sql::" + sql);
		queryExecuter.execute(sql);
    }

    public static Result execute(int hostID) {

        Result result = null;

        String sql = 
			"DELETE FROM PodcastHosts WHERE PHID = %d;";
		;
        
		sql = String.format(sql, hostID);

		return queryExecuter.execute(sql);
	}

    public static Result run(Scanner reader) {

		System.out.println("+------------------------------------+");
		System.out.println("|         Podcast Host Details       |");
		System.out.println("+------------------------------------+");
		System.out.println("");

        showDetails("PodcastHosts");

		System.out.println("+------------------------------------+");
		System.out.println("| Please Submit the Following Inputs |");
		System.out.println("+------------------------------------+");
		System.out.println("");

		System.out.println("Host ID: ");
		int hostID = reader.nextInt();
		reader.nextLine();

		return execute(hostID);	
	}

}
