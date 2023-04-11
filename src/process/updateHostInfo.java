package process;

import java.util.Scanner;

import config.Connect;
import config.Result;

/**
 * This program is used to perform updateHostInfo API operation by updating the PodcastHosts table.
 */

public class updateHostInfo {

    public static Result execute(String sql) {
        return Connect.executeUpdate(sql);
    }

    public static void showDetails(String tableName){
        String sql = String.format("SELECT * FROM " + tableName + ";");
        Connect.executeQuery(sql);
    }

    public static Result run(Scanner reader) {
        System.out.println("+----------------------------------------+");
        System.out.println("|     Podcast Host Details before        |");
        System.out.println("+----------------------------------------+");
        System.out.println("");

        showDetails("PodcastHosts");

        System.out.println("| Please Submit the following details:  |");


        System.out.println("Podcast Host ID: ");
        int phID = reader.nextInt();
        reader.nextLine();

        System.out.println("Attribute you want to update: ");
        String attributeName = reader.nextLine();

        System.out.println("New attribute value: ");

        String sql = "";

        if(attributeName.equals("PHFName") || attributeName.equals("PHLName") || attributeName.equals("PHEmail") || attributeName.equals("PHCity")){
            String updatedAttributeValue = reader.nextLine();

            sql = "UPDATE PodcastHosts SET %s='%s' WHERE PHID = (%d);" + "\n" + "\n";
            sql = String.format(sql, attributeName, updatedAttributeValue, phID);

        }

        return execute(sql);
        
        System.out.println("+----------------------------------------+");
        System.out.println("|     Podcast Host Details after         |");
        System.out.println("+----------------------------------------+");
        System.out.println("");

        showDetails("PodcastHosts");
        

    }
}
