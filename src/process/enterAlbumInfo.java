package process;

import java.util.Scanner;

import config.Connect;
import config.Result;

/**
 * This program is used to perform enterAlbumInfo API operation by updating the Albums table.
 */

public class enterAlbumInfo {

    public static Result run(Scanner reader) {
        
        System.out.println("| Please Submit the following details: |");


        System.out.println("Album ID: ");
        int albumID = reader.nextInt();
        reader.nextLine();

        System.out.println("Album Name: ");
        String albumName = reader.nextLine();

        System.out.println("Release Year: ");
        int releaseYear = reader.nextInt();
        reader.nextLine();

        System.out.println("Edition: ");
        String edition = reader.nextLine();

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

    public static void main(String[] args) {
       
    }

}
