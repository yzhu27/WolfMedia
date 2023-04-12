package process;

import java.util.Scanner;
import java.sql.*;

import config.Connect;
import config.Result;
import maintain.getTables;

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

        System.out.println("Select attribute to update:" + "\n" +
                "1. Song title" + "\n" +
                "2. Release country of a Song" + "\n" +
                "3. Language of a Song" + "\n" +
                "4. Release date of a Song" + "\n" +
                "5. Duration of a Song" + "\n" +
                "6. Royalty rate of a Song" + "\n" +
                "7. Royalty paid of a Song" + "\n" +
                "8. The number of Play Count of a Song" + "\n" +
                "9. Artist of a Song" + "\n" +
                "10. Album of a Song" + "\n" +
                "11. Track number of a Song"
        );
        int choice = reader.nextInt();
        reader.nextLine();

        String attribute;
        if (choice == 1){
            attribute = "Title";
        }else if(choice == 2){
            attribute = "ReleaseCountry";
        }else if(choice == 3){
            attribute = "Slanguage";
        }else if(choice == 4){
            attribute = "ReleaseDate";
        }else if(choice == 5){
            attribute = "Duration";
        }else if(choice == 6){
            attribute = "RoyaltyRate";
        }else if(choice == 7){
            attribute = "RoyaltyPaid";
        }else if(choice == 8){
            attribute = "Playcount";
        }else if(choice == 9) {
            attribute = "ArtistID";
        }else if(choice == 10) {
            attribute = "AlbumID";
        }else if(choice == 11) {
            attribute = "TrackNumber";
        }else {
            return new Result(false, "Invalid input");
        }

        System.out.println("New Value: ");
        String newValue = reader.nextLine();

        return execute(songID, attribute, newValue);

//        System.out.println("Attribute you want to update: ");
//        String attributeName = reader.nextLine();
//
//        System.out.println("New attribute value: ");
//
//        String sql = "";
//
//        if(attributeName.equals("Title") || attributeName.equals("ReleaseCountry") || attributeName.equals("Slanguage") || attributeName.equals("ReleaseDate")){
//            String updatedAttributeValue = reader.nextLine();
//
//            sql = "UPDATE Songs SET %s='%s' WHERE SongID = (%d);" + "\n" + "\n";
//            sql = String.format(sql, attributeName, updatedAttributeValue, songID);
//
//        } else if(attributeName.equals("Duration") || attributeName.equals("RoyaltyRate") || attributeName.equals("RoyaltyPaid")){
//            float updatedAttributeValue = reader.nextFloat();
//            reader.nextLine();
//
//            sql = "UPDATE Songs SET %s=%f WHERE SongID = (%d);" + "\n" + "\n";
//            sql = String.format(sql, attributeName, updatedAttributeValue, songID);
//
//        } else if(attributeName.equals("Playcount") || attributeName.equals("ArtistID") || attributeName.equals("AlbumID") || attributeName.equals("TrackNumber")){
//            int updatedAttributeValue = reader.nextInt();
//            reader.nextLine();
//
//            sql = "UPDATE Songs SET %s=%d WHERE SongID = (%d);" + "\n" + "\n";
//            sql = String.format(sql, attributeName, updatedAttributeValue, songID);
//        }
//
//        return execute(sql);
    }
    public static Result execute(int ID, String attribute, String newValue) {

        String sql;

        if(attribute == "RoyaltyRate"){
            sql = "UPDATE Songs " +
                    "SET %s = %.2f "  +
                    "WHERE SongID = %d " +
                    ";";

            sql = String.format(sql, attribute, Float.parseFloat(newValue), ID);

        }else if(attribute == "Playcount" || attribute == "ArtistID" || attribute == "AlbumID" || attribute == "TrackNumber"){
            sql = "UPDATE Songs " +
                    "SET %s = %d "  +
                    "WHERE SongID = %d " +
                    ";";

            sql = String.format(sql, attribute, Integer.parseInt(newValue), ID);
        }else{
            sql = "UPDATE Songs " +
                    "SET %s = '%s' "  +
                    "WHERE SongID = %d " +
                    ";";
            sql = String.format(sql, attribute, newValue, ID);
        }
//        String sql =
//                "UPDATE Podcasts " +
//                        "SET %s = '%s' "  +
//                        "WHERE PID = %d " +
//                        ";"
//                ;

        return Connect.executeUpdate(sql);
    }
}
