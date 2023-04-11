package process;

import java.util.Scanner;
import java.sql.*;

import config.Connect;
import config.Result;

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
        System.out.println("|           Song Details            |");
        System.out.println("+------------------------------------+");
        System.out.println("");

        showDetails("Songs");

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
