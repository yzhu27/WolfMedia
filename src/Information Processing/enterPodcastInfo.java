import java.util.Scanner;

public class enterPodcastInfo {

    public static ExecResult run(Scanner reader) {
        System.out.println("+------------------------------------+");
        System.out.println("| Please Submit the Following Inputs |");
        System.out.println("+------------------------------------+");
        System.out.println("");

        System.out.println("Podcast ID: ");
        int podcastID = reader.nextInt();
        reader.nextLine();

        System.out.println("Name: ");
        String name = reader.nextLine();

        System.out.println("Language: ");
        String language = reader.nextLine();

        System.out.println("Country: ");
        String country = reader.nextLine();

        System.out.println("Rating: ");
        double rating = reader.nextDouble();
        reader.nextLine();

        System.out.println("Subscribers: ");
        int subscribers = reader.nextInt();
        reader.nextLine();

        System.out.println("Episode Count: ");
        int episodeCount = reader.nextInt();
        reader.nextLine();

        System.out.println("Podcast Host ID: ");
        int phID = reader.nextInt();
        reader.nextLine();

        return execute(podcastID, name, language, country, rating, subscribers, episodeCount, phID);   
    }

    public static ExecResult execute(int podcastID, String name, String language, String country, double rating, int subscribers, int episodeCount, int phID) {
        
        String sql = 
            "INSERT INTO Podcasts VALUES "  + "\n" + "\t" +
                "(%d, '%s', '%s', '%s', %.2f, %d, %d, %d)"  + "\n" +
            ";"+ "\n" + "\n"
        ;
        
        sql = String.format(sql, podcastID, name, language, country, rating, subscribers, episodeCount, phID);
        
        return WolfPubDB.executeUpdate(sql);
    }

    public static void main(String[] args) {
        System.out.println("\n");
        System.out.println("Unit Test for AddPodcast");
        System.out.println("===============================");
        execute(1, "The Joe Rogan Experience", "English", "USA", 4.5, 10000000, 200, 1);
    }

}
