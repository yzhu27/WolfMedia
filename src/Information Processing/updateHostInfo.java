import java.util.Scanner;
import java.sql.*;

public class updateHostInfo {

    public static ExecResult execute(String sql) {
        return WolfPubDB.executeUpdate(sql);
    }

    public static void showDetails(String tableName){
        String sql = String.format("SELECT * FROM " + tableName + ";");
        WolfPubDB.executeQuery(sql);
    }

    public static ExecResult run(Scanner reader) {
        System.out.println("+----------------------------------------+");
        System.out.println("|           Podcast Host Details         |");
        System.out.println("+----------------------------------------+");
        System.out.println("");

        showDetails("PodcastHosts");

        System.out.println("+----------------------------------------+");
        System.out.println("| Please Submit the Following Inputs     |");
        System.out.println("+----------------------------------------+");
        System.out.println("");

        System.out.println("Podcast Host ID: ");
        int phID = reader.nextInt();
        reader.nextLine();

        System.out.println("Attribute you want to update: ");
        String attributeName = reader.nextLine();

        System.out.println("New attribute value: ");

        String sql = "";

        if(attributeName.equals("PHFName") || attributeName.equals("PHLName") || attributeName.equals("PHEmail") || attributeName.equals("PHCity")){
            String updatedAttributeValue = reader.nextLine();

            sql = "UPDATE PodcastHosts SET %s='%s' WHERE PHID = (%d);" + "\n" + "\n";
            sql = String.format(sql, attributeName, updatedAttributeValue, phID);

        }

        return execute(sql);
    }
}
