package maintain;

import config.Connect;
import config.Result;
import java.util.Scanner;


public class getTables {
    public static Result execute(String name) {

        String sql =
                "SELECT * FROM " + name + ";";

        return Connect.executeQuery(sql);
    }

    public static Result run(Scanner reader) {
        System.out.println("Select tables to display:" + "\n" +
                "1. PodcastEpisodes" + "\n" +
                "2. Artists" + "\n" +
                "3. Songs" + "\n" +
                "4. Podcasts" + "\n" +
                "5. SongRecords" + "\n" +
                "6. PodcastRecords" + "\n" +
                "7. Make" + "\n" +
                "8. Collaborate" + "\n" +
                "9. Albums" + "\n" +
                "10. PodcastHosts" + "\n" +
                "11. Collaborate"
//                + "\n" +
//                "11. " + "\n" +
//                "12. " + "\n" +
//                "13. " + "\n" +
//                "14. "
        );
        int choice = reader.nextInt();
        reader.nextLine();

        String name;
        if (choice == 1){
            name = "PodcastEpisodes";
        }else if(choice == 2){
            name = "Artists";
        }else if(choice == 3){
            name = "Songs";
        }else if(choice == 4){
            name = "Podcasts";
        }else if(choice == 5){
            name = "SongRecords";
        }else if(choice == 6){
            name = "PodcastRecords";
        }else if(choice == 7){
            name = "Make";
        }else if(choice == 8){
            name = "Collaborate";
        }else if(choice == 9){
            name = "Albums";
        }else if(choice == 10){
            name = "PodcastHosts";
        }else if(choice == 11){
            name = "Collaborate";}
        else {
            return new Result(false, "Invalid input");
        }

        return execute(name);
    }
}
