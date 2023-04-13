package payments;

import util.*;
import java.sql.*;

import java.util.Scanner;


/**
 *  This Class used to display host payment records table.
 */


public class getHostPaymentRecords {

    public static String execute() {

        String sql =
                "SELECT * FROM HostPaymentRecords;";

        return queryExecuter.execute(sql);
    }

    public static String run(Scanner reader) throws SQLException{
        System.out.println("+------------------------------------+");
        System.out.println("|      HostPaymentRecords Details    |");
        System.out.println("+------------------------------------+");
        System.out.println("");

        return execute();
    }
}
