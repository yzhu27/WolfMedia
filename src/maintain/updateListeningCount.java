package maintain;

import config.Connect;
import config.Result;
import java.util.Scanner;

public class updateListeningCount {
    public static Result run(Scanner reader) {
        System.out.println("+------------------------------------+");
        System.out.println("| Please Submit the Following Inputs |");
        System.out.println("+------------------------------------+");
        System.out.println("");

        System.out.println("Podcast Episode ID: ");
        int PEID = reader.nextInt();
        reader.nextLine();

        System.out.println("Podcast ID: ");
        int PID = reader.nextInt();
        reader.nextLine();

        System.out.println("Select attribute to update:" + "\n" +
                "1. Podcast Episode Listen Count:"
        );
        int choice = reader.nextInt();
        reader.nextLine();

        String attribute;
        if (choice == 1){
            attribute = "ListenerCount";
        } else {
            return new Result(false, "Invalid input");
        }

        System.out.println("New Value: ");
        int newValue = reader.nextInt();
        reader.nextLine();

        return execute(PID, PEID, attribute, newValue);
    }

    public static Result execute(int ID, int ID2, String attribute, int newValue) {

        String sql =
            "UPDATE PodcastEpisodes " +
            "SET %s = %d "  +
            "WHERE PEID = %d " +
            "AND PID = %d " +
            ";"
        ;

        sql = String.format(sql, attribute, newValue, ID2, ID);
        return Connect.executeUpdate(sql);
    }
}
