package payments;

import config.Connect;
import config.Result;

import java.util.Scanner;

public class delArtistPaymentRecord {

    public static Result execute(String PayDate, int ArtistID) {

        String sql =
                "DELETE FROM ArtistPaymentRecords WHERE PayDate = '%s' AND ArtistID = %d;";

        sql = String.format(sql, PayDate, ArtistID);

        return Connect.executeQuery(sql);
    }

    public static Result run(Scanner reader) {
        System.out.println("+------------------------------------+");
        System.out.println("| Please Submit the Following Inputs |");
        System.out.println("+------------------------------------+");
        System.out.println("");

        System.out.println("ArtistID: ");
        int ArtistID = reader.nextInt();
        reader.nextLine();

        System.out.println("PayDate: YYYY-MM-DD");
        String PayDate = reader.nextLine();


        return execute(PayDate, ArtistID);
    }
}
