package payments;

import util.*;
import java.sql.*;

import java.util.Scanner;

public class addHostPaymentRecord {

    public static String execute(String PayDate, int PHID, float payAmount) {

        String sql =
                "INSERT INTO HostPaymentRecords VALUES " +
                        "('%s', %d, %.2f)" +
                        ";"
                ;
        sql = String.format(sql, PayDate, PHID, payAmount);

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
        String PayDate = reader.next();
        reader.nextLine();

        System.out.println("PayAmount: ");
        float PayAmount = reader.nextFloat();
        reader.nextLine();

        return execute(PayDate, PHID, PayAmount);
    }
}
