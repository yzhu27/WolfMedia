import java.util.Scanner;
import java.sql.*;

public class updateArtistInfo {

    public static ExecResult execute(String sql) {
        return WolfPubDB.executeUpdate(sql);
    }

    public static void showDetails(String tableName){
        String sql = String.format("SELECT * FROM " + tableName + ";");
        WolfPubDB.executeQuery(sql);
    }

    public static ExecResult run(Scanner reader) {
        System.out.println("+------------------------------------+");
        System.out.println("|         Artist Details             |");
        System.out.println("+------------------------------------+");
        System.out.println("");

        showDetails("Artists");

        System.out.println("+------------------------------------+");
        System.out.println("| Please Submit the Following Inputs |");
        System.out.println("+------------------------------------+");
        System.out.println("");

        System.out.println("Artist ID: ");
        int artistID = reader.nextInt();
        reader.nextLine();

        System.out.println("Attribute you want to update: ");
        String attributeName = reader.nextLine();

        System.out.println("New attribute value: ");

        String sql = "";

        if(attributeName.equals("Name") || attributeName.equals("Status") || attributeName.equals("Type") || attributeName.equals("ArtCountry") || attributeName.equals("PrimaryGenre")){
            String updatedAttributeValue = reader.nextLine();

            sql = "UPDATE Artists SET %s='%s' WHERE ArtistID = (%d);" + "\n" + "\n";
            sql = String.format(sql, attributeName, updatedAttributeValue, artistID);

        } else if(attributeName.equals("MonthlyListeners") || attributeName.equals("RLID")){
            int updatedAttributeValue = reader.nextInt();
            reader.nextLine();

            sql = "UPDATE Artists SET %s=%d WHERE ArtistID = (%d);" + "\n" + "\n";
            sql = String.format(sql, attributeName, updatedAttributeValue, artistID);
        }

        return execute(sql);
    }
}
