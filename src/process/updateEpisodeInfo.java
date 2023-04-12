package process;

import java.util.Scanner;
import java.sql.*;

import util.queryExecuter;
import config.Result;

public class updateEpisodeInfo {

        public static Result execute(String sql) {
            return queryExecuter.execute(sql);
        }

        public static void showDetails(String tableName){
            String sql = String.format("SELECT * FROM " + tableName + ";");
            queryExecuter.execute(sql);
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

            System.out.println("Select attribute to update:" + "\n" +
                    "1. Podcast Episode title" + "\n" +
                    "2. Podcast Episode duration (HH:MM:SS)" + "\n" +
                    "3. Podcast Episode release date (YYYY:MM:DD)" + "\n" +
                    "4. Podcast Episode listener count" + "\n" +
                    "5. Podcast Episode advertisement count"
            );
            int choice = reader.nextInt();
            reader.nextLine();

            String attribute;
            if (choice == 1){
                attribute = "PETitle";
            }else if(choice == 2){
                attribute = "PEDuration";
            }else if(choice == 3){
                attribute = "PEReleaseDate";
            }else if(choice == 4){
                attribute = "ListenerCount";
            }else if(choice == 5){
                attribute = "AdCount";
            }else {
                return new Result(false, "Invalid input");
            }

            System.out.println("New Value: ");
            String newValue = reader.nextLine();

            return execute(peID, pID, attribute, newValue);


    }
    public static Result execute(int ID1, int ID2, String attribute, String newValue) {

        String sql =
                "UPDATE PodcastEpisodes " +
                        "SET %s = '%s' "  +
                        "WHERE PEID = %d " +
                        "AND PID = %d" +
                        ";"
                ;

        sql = String.format(sql, attribute, newValue, ID1, ID2);
        return queryExecuter.execute(sql);
    }
}