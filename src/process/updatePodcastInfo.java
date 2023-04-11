package process;

import java.util.Scanner;

import config.Connect;
import config.Result;

/**
 * This program is used to perform updatePodcastInfo API operation by updating the Podcasts table.
 */

public class updatePodcastInfo {

      public static Result execute(String sql) {
          return Connect.executeUpdate(sql);
      }

      public static void showDetails(String tableName){
          String sql = String.format("SELECT * FROM " + tableName + ";");
          Connect.executeQuery(sql);
      }

      public static Result run(Scanner reader) {
          System.out.println("+------------------------------------+");
          System.out.println("|         Podcast Details before     |");
          System.out.println("+------------------------------------+");
          System.out.println("");

          showDetails("Podcasts");

          System.out.println("| Please Submit the following details: |");


          System.out.println("Podcast ID: ");
          int podcastID = reader.nextInt();
          reader.nextLine();

          System.out.println("Attribute to be updated: ");
          String attributeName = reader.nextLine();

          System.out.println("New attribute value: ");

          String sql = "";

          if(attributeName.equals("PName") || attributeName.equals("PLanguage") || attributeName.equals("PCountry")){
              String updatedAttributeValue = reader.nextLine();

              sql = "UPDATE Podcasts SET %s='%s' WHERE PID = (%d);" + "\n" + "\n";
              sql = String.format(sql, attributeName, updatedAttributeValue, podcastID);

          } else if(attributeName.equals("PRating")){
              float updatedAttributeValue = reader.nextFloat();
              reader.nextLine();

              sql = "UPDATE Podcasts SET %s=%f WHERE PID = (%d);" + "\n" + "\n";
              sql = String.format(sql, attributeName, updatedAttributeValue, podcastID);

          } else if(attributeName.equals("PSubscribers") || attributeName.equals("EpisodeCount") || attributeName.equals("PHID")){
              int updatedAttributeValue = reader.nextInt();
              reader.nextLine();

              sql = "UPDATE Podcasts SET %s=%d WHERE PID = (%d);" + "\n" + "\n";
              sql = String.format(sql, attributeName, updatedAttributeValue, podcastID);
          }

          return execute(sql);
            
          System.out.println("+------------------------------------+");
          System.out.println("|         Podcast Details after      |");
          System.out.println("+------------------------------------+");
          System.out.println("");

          showDetails("Podcasts");

            
      }
}
