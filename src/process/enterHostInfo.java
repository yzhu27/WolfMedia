package process;

import java.util.Scanner;

import util.*;
import java.sql.*;

/**
 *  This Class used for executing the enterHostInfo API operation by updating the PodcastHosts Table.
 */

public class enterHostInfo {
    
    public static String run(Scanner reader) throws SQLException{
        System.out.println("+------------------------------------+");
        System.out.println("| Please Submit the Following Inputs |");
        System.out.println("+------------------------------------+");
        System.out.println("");

        System.out.println("Podcast Host ID: ");
        int phID = reader.nextInt();
        reader.nextLine();

        System.out.println("First Name: ");
        String phFName = reader.nextLine();

        System.out.println("Last Name: ");
        String phLName = reader.nextLine();

        System.out.println("Email: ");
        String phEmail = reader.nextLine();

        System.out.println("City: ");
        String phCity = reader.nextLine();

        return execute(phID, phFName, phLName, phEmail, phCity);    
    }

    public static String execute(int phID, String phFName, String phLName, String phEmail, String phCity) {
        
        String sql = 
            "INSERT INTO PodcastHosts VALUES "  + "\n" + "\t" +
                "(%d, '%s', '%s', '%s', '%s')"  + "\n" +
            ";"
        ;
        
        sql = String.format(sql, phID, phFName, phLName, phEmail, phCity);
        
        return queryExecuter.execute(sql);
    }

    public static void main(String[] args) {

        
    }
}
