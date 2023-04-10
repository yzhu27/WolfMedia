import java.util.Scanner;
import java.sql.*;

import config.Connect;
import config.Result;

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

        System.out.println("Attribute you want to update: ");
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
    }
}
