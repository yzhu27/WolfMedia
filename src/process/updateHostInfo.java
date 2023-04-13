package process;

import java.util.Scanner;
import java.sql.*;

import util.*;

/**
 *  This Class used for executing the updateHostInfo API operation by updating the PodcastHosts Table.
 */


public class updateHostInfo {

    public static String execute(String sql) {
        return queryExecuter.execute(sql);
    }



    public static String run(Scanner reader) throws SQLException{
        System.out.println("+----------------------------------------+");
        System.out.println("|           Podcast Host Details         |");
        System.out.println("+----------------------------------------+");
        System.out.println("");

        DBTablePrinter.printTable("PodcastHosts");

        System.out.println("+----------------------------------------+");
        System.out.println("| Please Submit the Following Inputs     |");
        System.out.println("+----------------------------------------+");
        System.out.println("");

        System.out.println("Podcast Host ID: ");
        int phID = reader.nextInt();
        reader.nextLine();


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
            return "Error: Invalid Input";
        }

        System.out.println("New Value: ");
        String newValue = reader.nextLine();

        return execute(phID, attribute, newValue);

    }

    public static String execute(int ID, String attribute, String newValue) {

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
