import java.util.Scanner;
import java.sql.*;

import config.Connect;
import config.Result;

public class updateEpisodeInfo {

        public static Result execute(String sql) {
            return Connect.executeUpdate(sql);
        }

        public static void showDetails(String tableName){
            String sql = String.format("SELECT * FROM " + tableName + ";");
            Connect.executeQuery(sql);
        }

        public static Result run(Scanner reader) {
            System.out.println("+------------------------------------+");
            System.out.println("|      Podcast Episode Details       |");
            System.out.println("+------------------------------------+");
            System.out.println("");

            showDetails("PodcastEpisodes");

            System.out.println("+------------------------------------+");
            System.out.println("| Please Submit the Following Inputs |");
            System.out.println("+------------------------------------+");
            System.out.println("");

            System.out.println("Podcast Episode ID: ");
            int peID = reader.nextInt();
            reader.nextLine();

            System.out.println("Podcast ID: ");
            int pID = reader.nextInt();
            reader.nextLine();

            System.out.println("Attribute you want to update: ");
            String attributeName = reader.nextLine();

            System.out.println("New attribute value: ");

            String sql = "";

            if(attributeName.equals("PETitle")){
                String updatedAttributeValue = reader.nextLine();

                sql = "UPDATE PodcastEpisodes SET %s='%s' WHERE PEID = (%d) AND PID = (%d);" + "\n" + "\n";
                sql = String.format(sql, attributeName, updatedAttributeValue, peID, pID);

            } else if(attributeName.equals("PEDuration") || attributeName.equals("PEReleaseDate")){
                String updatedAttributeValue = reader.nextLine();

                sql = "UPDATE PodcastEpisodes SET %s='%s' WHERE PEID = (%d) AND PID = (%d);" + "\n" + "\n";
                sql = String.format(sql, attributeName, updatedAttributeValue, peID, pID);
            } else if(attributeName.equals("ListenerCount") || attributeName.equals("AdCount")){
                int updatedAttributeValue = reader.nextInt();
                reader.nextLine();

                sql = "UPDATE PodcastEpisodes SET %s=%d WHERE PEID = (%d) AND PID = (%d);" + "\n" + "\n";
                sql = String.format(sql, attributeName, updatedAttributeValue, peID, pID);
            }

            return execute(sql);
    }
}