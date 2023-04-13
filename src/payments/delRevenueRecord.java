package payments;

import util.*;
import java.sql.*;

import java.util.Scanner;


/**
 *  This Class used to delete a revenue record in RevenueRecords table.
 */

public class delRevenueRecord {

    public static String execute(String RevDate) {

        String sql =
                "DELETE FROM RevenueRecords WHERE RevDate = '%s';";
        sql = String.format(sql, RevDate);

        return queryExecuter.execute(sql);
    }

    public static String run(Scanner reader) throws SQLException{
        System.out.println("+------------------------------------+");
        System.out.println("| Please Submit the Following Inputs |");
        System.out.println("+------------------------------------+");
        System.out.println("");

        System.out.println("RevDate: YYYY-MM-DD");
        String RevDate = reader.next();
        reader.next();

        return execute(RevDate);
    }
}
