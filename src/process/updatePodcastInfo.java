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

//          System.out.println("Attribute you want to update: ");
//          String attributeName = reader.nextLine();

          System.out.println("Select attribute to update:" + "\n" +
                  "1. Podcast name" + "\n" +
                  "2. Podcast language" + "\n" +
                  "3. Podcast country"
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
          }else {
              return "Error: Invalid Input";
          }

          System.out.println("New Value: ");
          String newValue = reader.nextLine();

          return execute(podcastID, attribute, newValue);

//          System.out.println("Attribute you want to update: ");
//          String attributeName = reader.nextLine();
//
//          System.out.println("New attribute value: ");
//
//          String sql = "";
//
//          if(attributeName.equals("PName") || attributeName.equals("PLanguage") || attributeName.equals("PCountry")){
//              String updatedAttributeValue = reader.nextLine();
//
//              sql = "UPDATE Podcasts SET %s='%s' WHERE PID = (%d);" + "\n" + "\n";
//              sql = String.format(sql, attributeName, updatedAttributeValue, podcastID);
//
//          } else if(attributeName.equals("PRating")){
//              float updatedAttributeValue = reader.nextFloat();
//              reader.nextLine();
//
//              sql = "UPDATE Podcasts SET %s=%f WHERE PID = (%d);" + "\n" + "\n";
//              sql = String.format(sql, attributeName, updatedAttributeValue, podcastID);
//
//          } else if(attributeName.equals("PSubscribers") || attributeName.equals("EpisodeCount") || attributeName.equals("PHID")){
//              int updatedAttributeValue = reader.nextInt();
//              reader.nextLine();
//
//              sql = "UPDATE Podcasts SET %s=%d WHERE PID = (%d);" + "\n" + "\n";
//              sql = String.format(sql, attributeName, updatedAttributeValue, podcastID);
//          }
//
//          return execute(sql);
      }
    public static String execute(int ID, String attribute, String newValue) {

        String sql =
                "UPDATE Podcasts " +
                        "SET %s = '%s' "  +
                        "WHERE PID = %d " +
                        ";"
                ;

        sql = String.format(sql, attribute, newValue, ID);
        return queryExecuter.execute(sql);
    }
}
