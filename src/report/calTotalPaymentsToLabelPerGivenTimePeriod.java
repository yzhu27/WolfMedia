package report;
import java.util.Scanner;
import java.sql.*;
import util.DBTablePrinter;
import util.*;


public class calTotalPaymentsToLabelPerGivenTimePeriod {
    public static String execute(String startDate, String endDate) {

		String sql = 
            "SELECT SUM(PayAmount) FROM LabelPaymentRecords " + "\n" +
            "WHERE PayDate between '%s' and '%s';"
		;
        
		sql = String.format(sql, startDate, endDate);
        
		return queryExecuter.execute(sql);
	}



    public static String run(Scanner reader) throws SQLException{

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
