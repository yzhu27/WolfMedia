package payments;

import config.Connect;
import config.Result;

import java.util.Scanner;

public class addArtistPaymentRecord {

    public static Result execute(String PayDate, int ArtistID, float paymentToArtists) {

        String sql =
                "INSERT INTO ArtistPaymentRecords VALUES " +
                        "(%s, '%d', '%.2f')" +
                        ";"
        ;
        sql = String.format(sql, PayDate, ArtistID, paymentToArtists);

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
        reader.nextLine();

        System.out.println("PayAmount: ");
        float PayAmount = reader.nextFloat();
        reader.nextLine();

        return execute(PayDate, ArtistID, PayAmount);
    }
}
