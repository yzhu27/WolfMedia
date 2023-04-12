package process;

import java.util.Scanner;

import config.Connect;
import config.Result;

public class enterAlbumInfo {

    public static Result run(Scanner reader) {
        System.out.println("+------------------------------------+");
        System.out.println("| Please Submit the Following Inputs |");
        System.out.println("+------------------------------------+");
        System.out.println("");

        System.out.println("Album ID: ");
        int albumID = reader.nextInt();
        reader.nextLine();

        System.out.println("Album Name: ");
        String albumName = reader.nextLine();
        reader.nextLine();

        System.out.println("Release Year: ");
        int releaseYear = reader.nextInt();
        reader.nextLine();

        System.out.println("Edition: ");
        String edition = reader.nextLine();
        reader.nextLine();

        return execute(albumID, albumName, releaseYear, edition);	
    }

    public static Result execute(int albumID, String albumName, int releaseYear, String edition) {

        String sql = 
            "INSERT INTO Albums VALUES "  + "\n" + "\t" +
                "(%d, '%s', %d, '%s')"  + "\n" +
            ";"
        ;
        
        sql = String.format(sql, albumID, albumName, releaseYear, edition);
        
        return Connect.executeUpdate(sql);
    }


}
