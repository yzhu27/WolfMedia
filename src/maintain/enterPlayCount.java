package maintain;

import config.Connect;
import config.Result;

import java.util.Scanner;

/**
 * Class used for executing the enterPlayCount API operation.
 */

public class enterPlayCount {    
    public static Result run(Scanner reader) {
        System.out.println("+-------------------------------------+");
        System.out.println("| Reset the play count of a song to 0 |");
        System.out.println("+-------------------------------------+");
        System.out.println("");

        System.out.println("SongID: ");
        int SongID = reader.nextInt();
        reader.nextLine();

        System.out.println("Select attribute to update:" + "\n" +
                "1. Playcount"
        );
        int choice = reader.nextInt();
        reader.nextLine();

        String attribute;
        if (choice == 1){
            attribute = "Playcount";
        } else {
            return new Result(false, "Invalid input");
        }

        // System.out.println("New Value: ");
        // int newValue = reader.nextInt();

        return execute(SongID, attribute, 0);
    }

    public static Result execute(int SongID, String attribute, int newValue) {

        String sql =
            "UPDATE Songs " +
            "SET %s = %d "  +
            "WHERE SongID = %d " +
            ";"
        ;

        sql = String.format(sql, attribute, newValue, SongID);
        return Connect.executeUpdate(sql);
    }
}
