package report;
import java.util.Scanner;

import config.Connect;
import config.Result;
public class calTotalRevenue {
    public static Result execute(String startDate, String endDate) {


		String sql = 
            "SELECT SUM(RevAmount) FROM RevenueRecords " + "\n" +
            "WHERE RevDate between '%s' and '%s';"
		;
        
		sql = String.format(sql, startDate, endDate);
        
		return Connect.executeQuery(sql);
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
