import java.util.Scanner;

public class deleteEpisodeInfo {

    public static void showDetails(String tableName){
        String sql = String.format("SELECT * FROM " + tableName + ";");
        System.out.println("sql::" + sql);
		WolfPubDB.executeQuery(sql);
    }

    public static ExecResult execute(int episodeID,int podcastID) {

        ExecResult result = null;

        String sql = 
			"DELETE FROM PodcastEpisodes WHERE PEID = %d AND PID = %d"  + "\n" + "\t" +
				 "\n" +
			";" + "\n" + "\n"
		;
        
		sql = String.format(sql, episodeID, podcastID);

		return WolfPubDB.executeUpdate(sql);
	}

	public static void main(String[] args) {
		System.out.println("\n");
		System.out.println("Unit Test for deleteEpisodeInfo");
		System.out.println("===============================");
		//execute(101,401);
	}



    public static ExecResult run(Scanner reader) {

		System.out.println("+------------------------------------+");
		System.out.println("|         Episode Details            |");
		System.out.println("+------------------------------------+");
		System.out.println("");

        showDetails("PodcastEpisodes");

		System.out.println("+------------------------------------+");
		System.out.println("| Please Submit the Following Inputs |");
		System.out.println("+------------------------------------+");
		System.out.println("");
      
    System.out.println("Podcast ID: ");
    int podcastID = reader.nextInt();
		reader.nextLine();
		System.out.println("Episode ID: ");
		int episodeID = reader.nextInt();
		reader.nextLine();

		return execute(episodeID, podcastID);	
	}

}
