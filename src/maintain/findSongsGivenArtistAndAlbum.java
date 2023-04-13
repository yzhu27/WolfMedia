package maintain;

import util.*;
import java.sql.*;
import java.util.Scanner;

/**
 *  This Class used to find all songs given both artist and album name from songs table.
 */

public class findSongsGivenArtistAndAlbum {


    public static String run(Scanner reader) throws SQLException{
        System.out.println("+------------------------------------+");
		System.out.println("|           Artist Details           |");
		System.out.println("+------------------------------------+");
		System.out.println("");

		DBTablePrinter.printTable("Artists");

        System.out.println("+------------------------------------+");
        System.out.println("| Please Submit the Following Inputs |");
        System.out.println("+------------------------------------+");
        System.out.println("");

        System.out.println("Artist ID: ");
        int AID = reader.nextInt();
        reader.nextLine();

        System.out.println("Album ID: ");
        int AlbumID = reader.nextInt();
        reader.nextLine();

        String attribute = "ArtistID";

        return execute(AID, AlbumID);
    }

    public static String execute(int AID, int AlbumID) {

        String sql =
            "SELECT * " +
            "FROM Songs "  +
            "WHERE ArtistID = %d AND AlbumID = %d" +
            ";"
        ;

        sql = String.format(sql, AID, AlbumID);
        return queryExecuter.execute(sql);
    }
}
