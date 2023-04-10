import java.util.Scanner;

/**
 * Class used for executing the enterPlayCountForSong API operation.
 */

public class enterPlayCountForSong {
    
    public static ExecResult run(Scanner reader) {
		System.out.println("+------------------------------------+");
		System.out.println("| Please Submit the Following Inputs |");
		System.out.println("+------------------------------------+");
		System.out.println("");

		System.out.println("Song ID: ");
		int songID = reader.nextInt();
		reader.nextLine();

		System.out.println("Select attribute to update:" + "\n" +
			"1. Play count (integer)" + "\n"
		);
		int choice = reader.nextInt();
		reader.nextLine();

		String attribute;
		if (choice == 1){
			attribute = "Playcount";
		} else {
			return new ExecResult(false, "Invalid input");
		}

		System.out.println("New Value: ");
		int newValue = reader.nextInt();
		
		return execute(songID, attribute, newValue);
	}

	public static ExecResult execute(int songID, String attribute, int newValue) {

		String sql = 
			"UPDATE Songs"  + "\n" +
			"SET %s = '%s'"  + "\n" +
            "WHERE SongID = %s" + "\n" +
			";" + "\n" + "\n"
		;
        
		sql = String.format(sql, attribute, newValue, songID);
        
        return WolfPubDB.executeUpdate(sql);
	}

	public static void main(String[] args) {
		System.out.println("\n");
		System.out.println("Unit Test for enterPlayCountsforSong");
		System.out.println("===============================");
		execute(1, "Playcount", 15);
	}


}
