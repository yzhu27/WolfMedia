package maintain;

import config.Connect;
import config.Result;
import java.util.Scanner;

public class enterTotalSubscribers {
    public static Result run(Scanner reader) {
        System.out.println("+---------------------------------------------------------+");
        System.out.println("| Reset the number of total subscribers of a Podcast to 0 |");
        System.out.println("+---------------------------------------------------------+");
        System.out.println("");

        System.out.println("PodcastID: ");
        int PID = reader.nextInt();
        reader.nextLine();

        System.out.println("Select attribute to update:" + "\n" +
                "1. PSubscribers"
        );
        int choice = reader.nextInt();
        reader.nextLine();

        String attribute;
        if (choice == 1){
            attribute = "PSubscribers";
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
