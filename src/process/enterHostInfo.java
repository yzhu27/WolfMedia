package process;

import java.util.Scanner;

import config.Connect;
import config.Result;

/**
 * This program is used to perform assignArtistToAlbum API operation by updating the PodcastHosts table.
 */

public class enterHostInfo {
    
    public static Result run(Scanner reader) {
        
        System.out.println("| Please Submit the following details: |");

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

    public static Result execute(int phID, String phFName, String phLName, String phEmail, String phCity) {
        
        String sql = 
            "INSERT INTO PodcastHosts VALUES "  + "\n" + "\t" +
                "(%d, '%s', '%s', '%s', '%s')"  + "\n" +
            ";"
        ;
        
        sql = String.format(sql, phID, phFName, phLName, phEmail, phCity);
        
        return Connect.executeUpdate(sql);
    }

    public static void main(String[] args) {

        
    }
}
