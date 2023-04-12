package process;

import java.util.Scanner;
import java.sql.*;

import config.Connect;
import config.Result;

public class updateAlbumInfo {

//    public static Result execute(String sql) {
//        return Connect.executeUpdate(sql);
//    }
//

    public static void showDetails(String tableName){
        String sql = String.format("SELECT * FROM " + tableName + ";");
        Connect.executeQuery(sql);
    }

    public static Result run(Scanner reader) {
        System.out.println("+------------------------------------+");
        System.out.println("|          Album Details            |");
        System.out.println("+------------------------------------+");
        System.out.println("");

        showDetails("Albums");

        System.out.println("+------------------------------------+");
        System.out.println("| Please Submit the Following Inputs |");
        System.out.println("+------------------------------------+");
        System.out.println("");

        System.out.println("Album ID: ");
        int albumID = reader.nextInt();
        reader.nextLine();

        System.out.println("Select attribute to update:" + "\n" +
                "1. Album Name" + "\n" +
                "2. Edition"
        );
        int choice = reader.nextInt();
        reader.nextLine();

        String attribute;
        if (choice == 1){
            attribute = "AlName";
        }else if(choice == 2){
            attribute = "Edition";
        }else {
            return new Result(false, "Invalid input");
        }

        System.out.println("New Value: ");
        String newValue = reader.nextLine();

        return execute(albumID, attribute, newValue);

//        System.out.println("Attribute you want to update: ");
//        String attributeName = reader.nextLine();
//
//        System.out.println("New attribute value: ");
//
//        String sql = "";
//
//        if(attributeName.equals("AlName") || attributeName.equals("Edition")){
//            String updatedAttributeValue = reader.nextLine();
//
//            sql = "UPDATE Albums SET %s='%s' WHERE AlbumID = (%d);" + "\n" + "\n";
//            sql = String.format(sql, attributeName, updatedAttributeValue, albumID);
//
//        } else if(attributeName.equals("ReleaseYear")){
//            int updatedAttributeValue = reader.nextInt();
//            reader.nextLine();
//
//            sql = "UPDATE Albums SET %s=%d WHERE AlbumID = (%d);" + "\n" + "\n";
//            sql = String.format(sql, attributeName, updatedAttributeValue, albumID);
//
//        }
//
//        return execute(sql);
    }
    public static Result execute(int ID, String attribute, String newValue) {

        String sql =
                "UPDATE Albums " +
                        "SET %s = '%s' "  +
                        "WHERE AlbumID = %d " +
                        ";"
                ;

        sql = String.format(sql, attribute, newValue, ID);
        return Connect.executeUpdate(sql);
    }
}
