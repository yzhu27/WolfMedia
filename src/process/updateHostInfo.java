package process;

import java.util.Scanner;
import java.sql.*;

import util.queryExecuter;
import config.Result;

public class updateHostInfo {

    public static Result execute(String sql) {
        return queryExecuter.execute(sql);
    }

    public static void showDetails(String tableName){
        String sql = String.format("SELECT * FROM " + tableName + ";");
        queryExecuter.execute(sql);
    }

    public static Result run(Scanner reader) {
        System.out.println("+----------------------------------------+");
        System.out.println("|           Podcast Host Details         |");
        System.out.println("+----------------------------------------+");
        System.out.println("");

        showDetails("PodcastHosts");

        System.out.println("+----------------------------------------+");
        System.out.println("| Please Submit the Following Inputs     |");
        System.out.println("+----------------------------------------+");
        System.out.println("");

        System.out.println("Podcast Host ID: ");
        int phID = reader.nextInt();
        reader.nextLine();

//        System.out.println("Attribute you want to update: ");
//        String attributeName = reader.nextLine();

        System.out.println("Select attribute to update:" + "\n" +
                "1. Podcast Host first name" + "\n" +
                "2. Podcast Host last name" + "\n" +
                "3. Podcast Host email" + "\n" +
                "4. Podcast Host city"
        );
        int choice = reader.nextInt();
        reader.nextLine();

        String attribute;
        if (choice == 1){
            attribute = "PHFName";
        }else if(choice == 2){
            attribute = "PHLName";
        }else if(choice == 3){
            attribute = "PHEmail";
        }else if(choice == 4){
            attribute = "PHCity";
        }else {
            return new Result(false, "Invalid input");
        }

        System.out.println("New Value: ");
        String newValue = reader.nextLine();

        return execute(phID, attribute, newValue);
//        System.out.println("New attribute value: ");
//
//        String sql = "";
//
//        if(attributeName.equals("PHFName") || attributeName.equals("PHLName") || attributeName.equals("PHEmail") || attributeName.equals("PHCity")){
//            String updatedAttributeValue = reader.nextLine();
//
//            sql = "UPDATE PodcastHosts SET %s='%s' WHERE PHID = (%d);" + "\n" + "\n";
//            sql = String.format(sql, attributeName, updatedAttributeValue, phID);
//
//        }
//
//        return execute(sql);
    }

    public static Result execute(int ID, String attribute, String newValue) {

        String sql =
                "UPDATE PodcastHosts " +
                        "SET %s = '%s' "  +
                        "WHERE PHID = %d " +
                        ";"
                ;

        sql = String.format(sql, attribute, newValue, ID);
        return queryExecuter.execute(sql);
    }
}
