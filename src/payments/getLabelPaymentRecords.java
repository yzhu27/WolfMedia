package payments;

import util.*;
import java.sql.*;

import java.util.Scanner;


/**
 *  This Class used to display label payment records table.
 */


public class getLabelPaymentRecords {
    public static String execute() {

        String sql =
                "SELECT * FROM LabelPaymentRecords;";

        return queryExecuter.execute(sql);
    }

    public static String run(Scanner reader) throws SQLException{
        System.out.println("+------------------------------------+");
        System.out.println("|     LabelPaymentRecords Details    |");
        System.out.println("+------------------------------------+");
        System.out.println("");

        return execute();
    }
}
