package maintain;

import config.Connect;
import config.Result;
import java.util.Scanner;

public class updateMonthlyListeners {
    public static Result run(Scanner reader) {
        System.out.println("+------------------------------------+");
        System.out.println("| Please Submit the Following Inputs |");
        System.out.println("+------------------------------------+");
        System.out.println("");

        System.out.println("ArtistID: ");
        int AID = reader.nextInt();
        reader.nextLine();

        System.out.println("Select attribute to update:" + "\n" +
                "1. MonthlyListeners"
        );
        int choice = reader.nextInt();
        reader.nextLine();

        String attribute;
        if (choice == 1){
            attribute = "MonthlyListeners";
        } else {
            return new Result(false, "Invalid input");
        }

        System.out.println("New Value: ");
        int newValue = reader.nextInt();

        return execute(AID, attribute, newValue);
    }

    public static Result execute(int ID, String attribute, int newValue) {

        String sql =
                "UPDATE Artists" +
                        "SET %s = %d"  +
                        "WHERE ArtistID = %d" +
                        ";"
                ;

        sql = String.format(sql, attribute, newValue, ID);
        return Connect.executeUpdate(sql);
    }
}
