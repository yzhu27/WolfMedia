package maintain;

import config.Connect;
import config.Result;
import java.util.Scanner;

public class findSongsGivenArtist {
    public static Result run(Scanner reader) {
        System.out.println("+------------------------------------+");
        System.out.println("| Please Submit the Following Inputs |");
        System.out.println("+------------------------------------+");
        System.out.println("");

        System.out.println("Artist ID: ");
        int AID = reader.nextInt();
        reader.nextLine();

        String attribute = "ArtistID";

        return execute(AID, attribute);
    }

    public static Result execute(int ID, String attribute) {

        String sql =
            "SELECT * " +
            "FROM Songs "  +
            "WHERE %s = %d " +
            ";"
        ;

        sql = String.format(sql, attribute, ID);
        return Connect.executeQuery(sql);
    }
}
