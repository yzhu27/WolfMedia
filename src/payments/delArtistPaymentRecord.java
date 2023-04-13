package payments;

import util.queryExecuter;


import java.util.Scanner;

public class delArtistPaymentRecord {

    public static String execute(String PayDate, int ArtistID) {

        String sql =
                "DELETE FROM ArtistPaymentRecords WHERE PayDate = '%s' AND ArtistID = %d;";

        sql = String.format(sql, PayDate, ArtistID);

        return queryExecuter.execute(sql);
    }

    public static String run(Scanner reader) {
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

        return execute(PayDate, ArtistID);
    }
}
