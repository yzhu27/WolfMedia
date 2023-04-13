package process;

import java.util.Scanner;
import java.sql.*;

import util.*;


public class assignSongToAlbum {

    public static String execute(String sql) {
        return queryExecuter.execute(sql);
    }



    public static String run(Scanner reader) throws SQLException{
        System.out.println("+------------------------------------+");
        System.out.println("|           Song Details            |");
        System.out.println("+------------------------------------+");
        System.out.println("");

        DBTablePrinter.printTable("Songs");

        System.out.println("+------------------------------------+");
        System.out.println("| Please Submit the Following Inputs |");
        System.out.println("+------------------------------------+");
        System.out.println("");

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
