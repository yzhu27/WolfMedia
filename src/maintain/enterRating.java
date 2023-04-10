package maintain;

import config.Connect;
import config.Result;
import java.util.Scanner;

public class enterRating {
    public static Result run(Scanner reader) {
        System.out.println("+------------------------------------+");
        System.out.println("| Reset the rating of a podcast to 0 |");
        System.out.println("+------------------------------------+");
        System.out.println("");

        System.out.println("PodcastID: ");
        int PID = reader.nextInt();
        reader.nextLine();

        System.out.println("Select attribute to update:" + "\n" +
                "1. PRating"
        );
        int choice = reader.nextInt();
        reader.nextLine();

        String attribute;
        if (choice == 1){
            attribute = "PRating";
        } else {
            return new Result(false, "Invalid input");
        }

        // System.out.println("New Value: ");
        // int newValue = reader.nextInt();

        return execute(PID, attribute, 0);
    }

    public static Result execute(int ID, String attribute, int newValue) {

        String sql =
                "UPDATE Podcasts" +
                        "SET %s = %d"  +
                        "WHERE PID = %d" +
                        ";"
                ;

        sql = String.format(sql, attribute, newValue, ID);
        return Connect.executeUpdate(sql);
    }
}
