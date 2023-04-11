package process;

import java.util.Scanner;

import config.Connect;
import config.Result;

public class updateSongInfo {

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

        System.out.println("Attribute you want to update: ");
        String attributeName = reader.nextLine();

        System.out.println("New attribute value: ");

        String sql = "";

        if(attributeName.equals("Title") || attributeName.equals("ReleaseCountry") || attributeName.equals("Slanguage") || attributeName.equals("ReleaseDate")){
            String updatedAttributeValue = reader.nextLine();

            sql = "UPDATE Songs SET %s='%s' WHERE SongID = (%d);" + "\n" + "\n";
            sql = String.format(sql, attributeName, updatedAttributeValue, songID);

        } else if(attributeName.equals("Duration") || attributeName.equals("RoyaltyRate") || attributeName.equals("RoyaltyPaid")){
            float updatedAttributeValue = reader.nextFloat();
            reader.nextLine();

            sql = "UPDATE Songs SET %s=%f WHERE SongID = (%d);" + "\n" + "\n";
            sql = String.format(sql, attributeName, updatedAttributeValue, songID);

        } else if(attributeName.equals("Playcount") || attributeName.equals("ArtistID") || attributeName.equals("AlbumID") || attributeName.equals("TrackNumber")){
            int updatedAttributeValue = reader.nextInt();
            reader.nextLine();

            sql = "UPDATE Songs SET %s=%d WHERE SongID = (%d);" + "\n" + "\n";
            sql = String.format(sql, attributeName, updatedAttributeValue, songID);
        }

        return execute(sql);
    }
}
