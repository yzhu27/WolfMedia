package process;

import java.util.Scanner;

import config.Connect;
import config.Result;

/**
 * This program is used to perform assignSongToAlbum API operation by updating the Songs table.
 */

public class assignSongToAlbum {

    public static Result execute(String sql) {
        return Connect.executeUpdate(sql);
    }

    public static void showDetails(String tableName){
        String sql = String.format("SELECT * FROM " + tableName + ";");
        Connect.executeQuery(sql);
    }

    public static Result run(Scanner reader) {
        System.out.println("+------------------------------------+");
        System.out.println("|           Songs Table Details            |");
        System.out.println("+------------------------------------+");
        System.out.println("");

        showDetails("Songs");

        System.out.println("| Please Submit the following details: |");

        System.out.println("Song ID: ");
        int songID = reader.nextInt();
        reader.nextLine();

         System.out.println("Album ID: ");
        int albumID = reader.nextInt();
        reader.nextLine();

        String sql = "";
        sql = "UPDATE Songs SET AlbumID=%d WHERE SongID = (%d);" + "\n" + "\n";
        sql = String.format(sql, albumID, songID);

        return execute(sql);
    }
}
