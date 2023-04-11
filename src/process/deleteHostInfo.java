package process;

import java.util.Scanner;

import config.Connect;
import config.Result;

public class deleteHostInfo {

    public static void showDetails(String tableName){
        String sql = String.format("SELECT * FROM " + tableName + ";");
        System.out.println("sql::" + sql);
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
		System.out.println("\n");
		System.out.println("Unit Test for deleteHostInfo");
		System.out.println("===============================");
		execute(100);
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
