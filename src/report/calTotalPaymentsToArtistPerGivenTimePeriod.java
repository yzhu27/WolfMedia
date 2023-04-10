package report;
import java.util.Scanner;

import config.Connect;
import config.Result;
import config.Transaction;

public class calTotalPaymentsToArtistPerGivenTimePeriod {
    public static Result execute(String startDate, String endDate) {

        Result result = null;

		String sql = 
            "SELECT SUM(PayAmount) FROM ArtistPaymentRecords" + "\n" +
            "WHERE PayDate between %s and %s;"
		;
        
		sql = String.format(sql, startDate, endDate);
    
		return Connect.executeUpdate(sql);
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
