package report;
import java.util.Scanner;

public class reportEpisodesGivenPodcast {
    public static void showDetails(String tableName){
        String sql = String.format("SELECT * FROM " + tableName + ";");
		WolfPubDB.executeQuery(sql);
    }

    public static ExecResult execute(int PID) {

		String sql = 
			"SELECT * FROM PodcastEpisodes WHERE PID=%d;"
		;
        
		sql = String.format(sql, PID);
        
		return WolfPubDB.executeUpdate(sql);
	}

    public static ExecResult run(Scanner reader) {
        System.out.println("+------------------------------------+");
		System.out.println("|           Podcasts Details         |");
		System.out.println("+------------------------------------+");
		System.out.println("");

		showDetails("Podcasts");

		System.out.println("+------------------------------------+");
		System.out.println("| Please Submit the Following Inputs |");
		System.out.println("+------------------------------------+");
		System.out.println("");

		System.out.println("PID: ");
		int PID = reader.nextInt();
		reader.nextLine();

		return execute(PID);
}
