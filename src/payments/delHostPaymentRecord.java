package payments;

import util.*;
import java.sql.*;

import java.util.Scanner;


/**
 *  This Class used to delete a payment record in HostPaymentRecords table.
 */

public class delHostPaymentRecord {

    public static String execute(String PayDate, int PHID) {

        String sql =
                "DELETE FROM HostPaymentRecords WHERE PayDate = '%s' AND HostID = %d;";

        sql = String.format(sql, PayDate, PHID);

        return queryExecuter.execute(sql);
    }

    public static String run(Scanner reader) throws SQLException{
        System.out.println("+------------------------------------+");
        System.out.println("| Please Submit the Following Inputs |");
        System.out.println("+------------------------------------+");
        System.out.println("");

        System.out.println("PHID: ");
        int PHID = reader.nextInt();
        reader.nextLine();

        System.out.println("PayDate: YYYY-MM-DD");
        String PayDate = reader.nextLine();


        return execute(PayDate, PHID);
    }
}
