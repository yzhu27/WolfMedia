package process;

import java.util.Scanner;

import config.Connect;
import config.Result;

/**
 * This program is used to perform updateAlbumInfo API operation by updating the Albums table.
 */

public class updateAlbumInfo {

    public static Result execute(String sql) {
        return Connect.executeUpdate(sql);
    }

    public static void showDetails(String tableName){
        String sql = String.format("SELECT * FROM " + tableName + ";");
        Connect.executeQuery(sql);
    }

    public static Result run(Scanner reader) {
        System.out.println("+------------------------------------+");
        System.out.println("|          Album Details Before      |");
        System.out.println("+------------------------------------+");
        System.out.println("");

        showDetails("Albums");

        System.out.println("| Please Submit the following details: |");


        System.out.println("Album ID: ");
        int albumID = reader.nextInt();
        reader.nextLine();

        System.out.println("Attribute to be updated: ");
        String attributeName = reader.nextLine();

        System.out.println("New attribute value: ");

        String sql = "";

        if(attributeName.equals("AlName") || attributeName.equals("Edition")){
            String updatedAttributeValue = reader.nextLine();

            sql = "UPDATE Albums SET %s='%s' WHERE AlbumID = (%d);" + "\n" + "\n";
            sql = String.format(sql, attributeName, updatedAttributeValue, albumID);

        } else if(attributeName.equals("ReleaseYear")){
            int updatedAttributeValue = reader.nextInt();
            reader.nextLine();

            sql = "UPDATE Albums SET %s=%d WHERE AlbumID = (%d);" + "\n" + "\n";
            sql = String.format(sql, attributeName, updatedAttributeValue, albumID);

        }

        return execute(sql);
        
        System.out.println("+------------------------------------+");
        System.out.println("|          Album Details After       |");
        System.out.println("+------------------------------------+");
        System.out.println("");

        showDetails("Albums");
    }
}
