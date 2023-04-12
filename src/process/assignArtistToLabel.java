package process;

import java.util.Scanner;
import config.Connect;
import config.Result;

/**
 * This program is used to perform assignArtistToLabel API operation, by updating the artists table.
 */

public class assignArtistToLabel {

    public static Result execute(String sql) {
        return Connect.executeUpdate(sql);
    }

    public static void showDetails(String tableName){
        String sql = String.format("SELECT * FROM " + tableName + ";");
        Connect.executeQuery(sql);
    }

    public static Result run(Scanner reader) {
        System.out.println("+------------------------------------+");
        System.out.println("|           Artist Details            |");
        System.out.println("+------------------------------------+");
        System.out.println("");

        showDetails("Artists");

        System.out.println("| Please Submit the Following details: |");

        System.out.println("Artist ID: ");
        int artistID = reader.nextInt();
        reader.nextLine();

         System.out.println("RecordLabel ID: ");
        int rlID = reader.nextInt();
        reader.nextLine();

        String sql = "";
        sql = "UPDATE Artists SET RLID=%d WHERE ArtistID=(%d);" + "\n" + "\n";
        sql = String.format(sql, rlID, artistID);

        return execute(sql);
    }
}
