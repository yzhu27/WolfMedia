import java.util.Scanner;

public class deletePodcastInfo {

    public static void showDetails(String tableName){
        String sql = String.format("SELECT * FROM " + tableName + ";");
        System.out.println("sql::" + sql);
		WolfPubDB.executeQuery(sql);
    }

    public static ExecResult execute(int podcastID) {

        ExecResult result = null;

        String sql = 
			"DELETE FROM Podcasts WHERE PID = %d"  + "\n" + "\t" +
				 "\n" +
			";" + "\n" + "\n"
		;
        
		sql = String.format(sql, podcastID);

		return WolfPubDB.executeUpdate(sql);
	}

	public static void main(String[] args) {
		System.out.println("\n");
		System.out.println("Unit Test for deletePodcastInfo");
		System.out.println("===============================");
		///execute(200);
	}



    public static ExecResult run(Scanner reader) {

		System.out.println("+------------------------------------+");
		System.out.println("|         Podcast Details               |");
		System.out.println("+------------------------------------+");
		System.out.println("");

        showDetails("Podcasts");

		System.out.println("+------------------------------------+");
		System.out.println("| Please Submit the Following Inputs |");
		System.out.println("+------------------------------------+");
		System.out.println("");

		System.out.println("Podcast ID: ");
		int podcastID = reader.nextInt();
		reader.nextLine();

		return execute(podcastID);	
	}

}
