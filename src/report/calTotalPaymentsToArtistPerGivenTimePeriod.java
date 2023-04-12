package report;
import java.util.Scanner;

import util.queryExecuter;
import config.Result;
import util.*;
public class calTotalPaymentsToArtistPerGivenTimePeriod {
    public static Result execute(String startDate, String endDate) {


		String sql = 
            "SELECT SUM(PayAmount) FROM ArtistPaymentRecords " +
            "WHERE PayDate BETWEEN '%s' AND '%s';";
        
		sql = String.format(sql, startDate, endDate);
    
		return queryExecuter.execute(sql);
	}



    public static Result run(Scanner reader) {

		System.out.println("+------------------------------------+");
		System.out.println("| Please Submit the Following Inputs |");
		System.out.println("+------------------------------------+");
		System.out.println("");

		System.out.println("Start Date (YYYY-MM-DD): ");
		String startDate = reader.nextLine();
		reader.nextLine();

        System.out.println("End Date (YYYY-MM-DD): ");
		String endDate = reader.nextLine();
        reader.nextLine();

		return execute(startDate, endDate);	
	}
}
