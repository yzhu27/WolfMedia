package process;

import java.util.Scanner;
import java.sql.*;

import util.*;
import java.sql.*;

public class updatePodcastInfo {

      public static String execute(String sql) {
          return queryExecuter.execute(sql);
      }

      public static String run(Scanner reader) throws SQLException{
          System.out.println("+------------------------------------+");
          System.out.println("|         Podcast Details            |");
          System.out.println("+------------------------------------+");
          System.out.println("");

          DBTablePrinter.printTable("Podcasts");

          System.out.println("+------------------------------------+");
          System.out.println("| Please Submit the Following Inputs |");
          System.out.println("+------------------------------------+");
          System.out.println("");

          System.out.println("Podcast ID: ");
          int podcastID = reader.nextInt();
          reader.nextLine();


          System.out.println("Select attribute to update:" + "\n" +
                  "1. Podcast name" + "\n" +
                  "2. Podcast language" + "\n" +
                  "3. Podcast country" + "\n" +
                  "4. Podcast rating" + "\n" +
                  "5. Podcast subscribers" + "\n" +
                  "6. Podcast Episode count"
          );
          int choice = reader.nextInt();
          reader.nextLine();

          String attribute;
          if (choice == 1){
              attribute = "PName";
          }else if(choice == 2){
              attribute = "PLanguage";
          }else if(choice == 3){
              attribute = "PCountry";
          }else if(choice == 4){
              attribute = "PRating";
          }else if(choice == 5) {
              attribute = "PSubscribers";
          }else if(choice == 6) {
              attribute = "EpisodeCount";
          } else {
              return "Error: Invalid Input";
          }

          System.out.println("New Value: ");
          String newValue = reader.nextLine();

          return execute(podcastID, choice, attribute, newValue);

      }
    public static String execute(int ID, int choice, String attribute, String newValue) {
        if (choice < 4){
            String sql =
                    "UPDATE Podcasts " +
                            "SET %s = '%s' "  +
                            "WHERE PID = %d " +
                            ";"
                    ;

            sql = String.format(sql, attribute, newValue, ID);
            return queryExecuter.execute(sql);
        } else if (choice == 4){
            float tmp = Float.parseFloat(newValue);
            String sql =
                    "UPDATE Podcasts " +
                            "SET %s = %.1f "  +
                            "WHERE PID = %d " +
                            ";"
                    ;

            sql = String.format(sql, attribute, tmp, ID);
            return queryExecuter.execute(sql);
        } else {
            int tmp =Integer.parseInt(newValue);
            String sql =
                    "UPDATE Podcasts " +
                            "SET %s = %d "  +
                            "WHERE PID = %d " +
                            ";"
                    ;

            sql = String.format(sql, attribute, tmp, ID);
            return queryExecuter.execute(sql);
        }
    }
}
