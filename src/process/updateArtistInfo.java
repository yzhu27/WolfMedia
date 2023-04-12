package process;

import java.util.Scanner;
import java.sql.*;

import util.queryExecuter;
import config.Result;

public class updateArtistInfo {

//    public static Result execute(String sql) {
//        return queryExecuter.execute(sql);
//    }

    public static void showDetails(String tableName){
        String sql = String.format("SELECT * FROM " + tableName + ";");
        queryExecuter.execute(sql);
    }

    public static Result run(Scanner reader) {
        System.out.println("+------------------------------------+");
        System.out.println("|         Artist Details             |");
        System.out.println("+------------------------------------+");
        System.out.println("");

        showDetails("Artists");

        System.out.println("+------------------------------------+");
        System.out.println("| Please Submit the Following Inputs |");
        System.out.println("+------------------------------------+");
        System.out.println("");

        System.out.println("Artist ID: ");
        int artistID = reader.nextInt();
        reader.nextLine();

        System.out.println("Select attribute to update:" + "\n" +
                "1. Artist name" + "\n" +
                "2. Artist status" + "\n" +
                "3. Artist type (band/musician/composer)" + "\n" +
                "4. Artist country" + "\n" +
                "5. Artist primary genre"
        );
        int choice = reader.nextInt();
        reader.nextLine();

        String attribute;
        if (choice == 1){
            attribute = "Name";
        }else if(choice == 2){
            attribute = "Status";
        }else if(choice == 3){
            attribute = "Type";
        }else if(choice == 4){
            attribute = "ArtCountry";
        }else if(choice == 5){
            attribute = "PrimaryGenre";
        }else {
            return new Result(false, "Invalid input");
        }

        String newValue;

        if(choice == 3){
            System.out.println("Select type of Artist:" + "\n" +
                    "1. Band" + "\n" +
                    "2. Musician" + "\n" +
                    "3. Composer"
            );
            int type = reader.nextInt();
            if (type == 1){
                newValue = "Band";
            }else if(type == 2){
                newValue = "Musician";
            }else if(type == 3){
                newValue = "Composer";
            }else {
                return new Result(false, "Invalid input");
            }
        }else {
            System.out.println("New Value: ");
            newValue = reader.nextLine();
//            reader.next();
        }

        return execute(artistID, attribute, newValue);

//        System.out.println("Attribute you want to update: ");
//        String attributeName = reader.nextLine();
//
//        System.out.println("New attribute value: ");
//
//        String sql = "";
//
//        if(attributeName.equals("Name") || attributeName.equals("Status") || attributeName.equals("Type") || attributeName.equals("ArtCountry") || attributeName.equals("PrimaryGenre")){
//            String updatedAttributeValue = reader.nextLine();
//
//            sql = "UPDATE Artists SET %s='%s' WHERE ArtistID = (%d);" + "\n" + "\n";
//            sql = String.format(sql, attributeName, updatedAttributeValue, artistID);
//
//        } else if(attributeName.equals("MonthlyListeners") || attributeName.equals("RLID")){
//            int updatedAttributeValue = reader.nextInt();
//            reader.nextLine();
//
//            sql = "UPDATE Artists SET %s=%d WHERE ArtistID = (%d);" + "\n" + "\n";
//            sql = String.format(sql, attributeName, updatedAttributeValue, artistID);
//        }
//
//        return execute(sql);
    }

    public static Result execute(int ID, String attribute, String newValue) {

        String sql =
                "UPDATE Artists " +
                        "SET %s = '%s' "  +
                        "WHERE ArtistID = %d " +
                        ";"
                ;

        sql = String.format(sql, attribute, newValue, ID);
        return queryExecuter.execute(sql);
    }
}
