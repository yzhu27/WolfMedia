package payments;

import util.*;
import java.sql.*;

import java.util.Scanner;

/**
 *  This Class used to add a new payment record for artist in ArtistPaymentRecords table.
 */

public class addArtistPaymentRecord {

    public static String execute(String PayDate, int ArtistID, float paymentToArtists) {

        String sql =
                "INSERT INTO ArtistPaymentRecords VALUES " +
                        "('%s', %d, %.2f)" +
                        ";"
        ;
        sql = String.format(sql, PayDate, ArtistID, paymentToArtists);

        return queryExecuter.execute(sql);
    }

    public static String run(Scanner reader) throws SQLException{
        System.out.println("+------------------------------------+");
        System.out.println("| Please Submit the Following Inputs |");
        System.out.println("+------------------------------------+");
        System.out.println("");

        System.out.println("ArtistID: ");
        int ArtistID = reader.nextInt();
        reader.nextLine();

        System.out.println("PayDate: YYYY-MM-DD");
        String PayDate = reader.next();
        reader.nextLine();

        System.out.println("PayAmount: ");
        float PayAmount = reader.nextFloat();
        reader.nextLine();

        return execute(PayDate, ArtistID, PayAmount);
    }
}
