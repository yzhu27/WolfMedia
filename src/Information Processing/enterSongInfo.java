import java.sql.*;
import java.util.Scanner;

public class enterSongInfo {

    public static Result run(Scanner reader) {
        System.out.println("+------------------------------------+");
        System.out.println("| Please Submit the Following Inputs |");
        System.out.println("+------------------------------------+");
        System.out.println("");

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
        System.out.println("\n");
        System.out.println("Unit Test for AddSong");
        System.out.println("===============================");
        //execute(1, "Bohemian Rhapsody", 6.07f, 1000000, "1975-10-31", "United Kingdom", "English", 0.1f, 1000f, 1, 1, 1);
    }
}

