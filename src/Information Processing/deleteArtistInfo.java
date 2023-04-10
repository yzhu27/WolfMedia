import java.util.Scanner;

public class deleteArtistInfo {

    public static void showDetails(String tableName){
        String sql = String.format("SELECT * FROM " + tableName + ";");
        System.out.println("sql::" + sql);
		    WolfPubDB.executeQuery(sql);
    }

    public static ExecResult execute(int songID) {

        ExecResult result = null;

        String sql = 
			"DELETE FROM Artists WHERE artistID = %d"  + "\n" + "\t" +
				 "\n" +
			";" + "\n" + "\n"
		;
        
		sql = String.format(sql, artistID);

		return WolfPubDB.executeUpdate(sql);
	}

	public static void main(String[] args) {
		System.out.println("\n");
		System.out.println("Unit Test for deleteArtistInfo");
		System.out.println("===============================");
		execute(1);
	}



    public static ExecResult run(Scanner reader) {

		System.out.println("+------------------------------------+");
		System.out.println("|         Artist Details               |");
		System.out.println("+------------------------------------+");
		System.out.println("");

        showDetails("Artists");

		System.out.println("+------------------------------------+");
		System.out.println("| Please Submit the Following Inputs |");
		System.out.println("+------------------------------------+");
		System.out.println("");

		System.out.println("Artist ID: ");
		int artistID = reader.nextInt();
		reader.nextLine();

		return execute(artistID);	
	}

}
