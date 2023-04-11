package process;

import java.util.Scanner;

import config.Connect;
import config.Result;

/**
 * This program is used to perform enterSongInfo API operation by updating the Songs table.
 */

public class enterSongInfo {

    public static Result run(Scanner reader) {

        System.out.println("| Please Submit the following details: |");


        System.out.println("Song ID: ");
        int songID = reader.nextInt();
        reader.nextLine();

        System.out.println("Title: ");
        String title = reader.nextLine();

        System.out.println("Duration: ");
        float duration = reader.nextFloat();
        reader.nextLine();

        System.out.println("Playcount: ");
        int playcount = reader.nextInt();
        reader.nextLine();

        System.out.println("Release Date (YYYY-MM-DD): ");
        String releaseDate = reader.nextLine();

        System.out.println("Release Country: ");
        String releaseCountry = reader.nextLine();

        System.out.println("Slanguage: ");
        String slanguage = reader.nextLine();

        System.out.println("Royalty Rate: ");
        float royaltyRate = reader.nextFloat();
        reader.nextLine();

        System.out.println("Royalty Paid: ");
        float royaltyPaid = reader.nextFloat();
        reader.nextLine();

        System.out.println("Artist ID: ");
        int artistID = reader.nextInt();
        reader.nextLine();

        System.out.println("Album ID: ");
        int albumID = reader.nextInt();
        reader.nextLine();

        System.out.println("Track Number: ");
        int trackNumber = reader.nextInt();
        reader.nextLine();

        return execute(songID, title, duration, playcount, releaseDate, releaseCountry, slanguage, royaltyRate, royaltyPaid, artistID, albumID, trackNumber);
    }

    public static Result execute(int songID, String title, float duration, int playcount, String releaseDate, String releaseCountry, String slanguage, float royaltyRate, float royaltyPaid, int artistID, int albumID, int trackNumber) {
        String sql =
                "INSERT INTO Songs VALUES "  + "\n" + "\t" +
                        "(%d, '%s', %f, %d, '%s', '%s', '%s', %f, %f, %d, %d, %d)"  + "\n" +
                        ";"
                ;

        sql = String.format(sql, songID, title, duration, playcount, releaseDate, releaseCountry, slanguage, royaltyRate, royaltyPaid, artistID, albumID, trackNumber);

        return Connect.executeUpdate(sql);
    }

    public static void main(String[] args) {
    
    }
}

