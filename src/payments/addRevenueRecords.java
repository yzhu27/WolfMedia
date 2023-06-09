package payments;

import util.*;
import java.sql.*;

import java.util.Scanner;


/**
 *  This Class used to add a revenue record in RevenueRecords table.
 */

public class addRevenueRecords {

    public static String execute(String RevDate, float RevAmount) {

        String sql =
                "INSERT INTO RevenueRecords VALUES " +
                        "('%s', %.2f)" +
                        ";"
                ;
        sql = String.format(sql, RevDate, RevAmount);

        return queryExecuter.execute(sql);
    }

    public static String run(Scanner reader) throws SQLException{
        System.out.println("+------------------------------------+");
        System.out.println("| Please Submit the Following Inputs |");
        System.out.println("+------------------------------------+");
        System.out.println("");

        System.out.println("RevDate: YYYY-MM-DD");
        String RevDate = reader.nextLine();
        reader.nextLine();

        System.out.println("RevAmount: ");
        float RevAmount = reader.nextFloat();
        reader.nextLine();

        return execute(RevDate, RevAmount);
    }
}
