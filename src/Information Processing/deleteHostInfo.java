import java.util.Scanner;

public class deleteHostInfo {

    public static void showDetails(String tableName){
        String sql = String.format("SELECT * FROM " + tableName + ";");
        System.out.println("sql::" + sql);
		WolfPubDB.executeQuery(sql);
    }

    public static ExecResult execute(int hostID) {

        ExecResult result = null;

        String sql = 
			"DELETE FROM PodcastHosts WHERE PHID = %d"  + "\n" + "\t" +
				 "\n" +
			";" + "\n" + "\n"
		;
        
		sql = String.format(sql, hostID);

		return WolfPubDB.executeUpdate(sql);
	}

	public static void main(String[] args) {
		System.out.println("\n");
		System.out.println("Unit Test for deleteHostInfo");
		System.out.println("===============================");
		execute(100);
	}



    public static ExecResult run(Scanner reader) {

		System.out.println("+------------------------------------+");
		System.out.println("|         Podcast Host Details       |");
		System.out.println("+------------------------------------+");
		System.out.println("");

        showDetails("PodcastHosts");

		System.out.println("+------------------------------------+");
		System.out.println("| Please Submit the Following Inputs |");
		System.out.println("+------------------------------------+");
		System.out.println("");

		System.out.println("Host ID: ");
		int hostID = reader.nextInt();
		reader.nextLine();

		return execute(hostID);	
	}

}
