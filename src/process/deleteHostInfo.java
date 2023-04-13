package process;

import java.util.Scanner;

import util.*;
import java.sql.*;

/**
 *  This Class used for executing the deleteHostInfo API operation by updating the PodcastHosts Table.
 */

public class deleteHostInfo {



    public static String execute(int hostID) {

        String result = null;

        String sql = 
			"DELETE FROM PodcastHosts WHERE PHID = %d;";
		;
        
		sql = String.format(sql, hostID);

		return queryExecuter.execute(sql);
	}

    public static String run(Scanner reader) throws SQLException{

		System.out.println("+------------------------------------+");
		System.out.println("|         Podcast Host Details       |");
		System.out.println("+------------------------------------+");
		System.out.println("");

        DBTablePrinter.printTable("PodcastHosts");

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
