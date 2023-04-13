package payments;

import util.*;
import java.sql.*;

import java.util.Scanner;

public class delLabelPaymentRecord {

    public static String execute(String PayDate, int LabelID) {

        String sql =
                "DELETE FROM LabelPaymentRecords WHERE PayDate = '%s' AND LabelID = %d;";

        sql = String.format(sql, PayDate, LabelID);

        return queryExecuter.execute(sql);
    }

    public static String run(Scanner reader) throws SQLException{
        System.out.println("+------------------------------------+");
        System.out.println("| Please Submit the Following Inputs |");
        System.out.println("+------------------------------------+");
        System.out.println("");

        System.out.println("LabelID: ");
        int LabelID = reader.nextInt();
        reader.nextLine();

        System.out.println("PayDate: YYYY-MM-DD");
        String PayDate = reader.nextLine();


        return execute(PayDate, LabelID);
    }
}
