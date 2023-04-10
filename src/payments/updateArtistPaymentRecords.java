package payments;

import config.Connect;
import config.Result;

import java.util.Scanner;

public class updateArtistPaymentRecords {

    public static Result run(Scanner reader) {
        System.out.println("+------------------------------------+");
        System.out.println("| Please Submit the Following Inputs |");
        System.out.println("+------------------------------------+");
        System.out.println("");

        System.out.println("ArtistID: ");
        int ArtistID = reader.nextInt();
        reader.nextLine();

        System.out.println("Select attribute to update:" + "\n" +
                "1. PayDate (YYYY-MM-YY)" + "\n" +
                "2. PayAmount"
        );
        int choice = reader.nextInt();
        reader.nextLine();

        String attribute;
        if (choice == 1){
            attribute = "PayDate";
        } else if (choice == 2){
            attribute = "PayAmount";
        } else {
            return new Result(false, "Invalid input");
        }

        System.out.println("New Value: ");
        String newValue = reader.nextLine();

        return execute(ArtistID, attribute, newValue);
    }

    public static Result execute(int publicationID, String attribute, String newValue) {

        if (attribute == "PayDate"){
            String sql =
                    "UPDATE ArtistPaymentRecords" +
                            "SET %s = '%s'"  +
                            "WHERE ArtistID = %d" +
                            ";"
                    ;

            sql = String.format(sql, attribute, newValue, publicationID);
            return Connect.executeUpdate(sql);
        }
        else {
            float tmp = Float.parseFloat(newValue);
            String sql =
                    "UPDATE ArtistPaymentRecords" +
                            "SET %s = %.2f"  +
                            "WHERE ArtistID = %d" +
                            ";"
                    ;

            sql = String.format(sql, attribute, newValue, publicationID);
            return Connect.executeUpdate(sql);
        }
    }
}
