package report;
import java.util.Scanner;

public class reportSongsGivenAlbum {
    public static void showDetails(String tableName){
        String sql = String.format("SELECT * FROM " + tableName + ";");
		WolfPubDB.executeQuery(sql);
    }

    public static ExecResult execute(int AlbumID) {

		String sql = 
			"SELECT * FROM Songs WHERE AlbumID=%d;"
		;
        
		sql = String.format(sql, AlbumID);
        
		return WolfPubDB.executeUpdate(sql);
	}

    public static ExecResult run(Scanner reader) {
        System.out.println("+------------------------------------+");
		System.out.println("|            Album Details           |");
		System.out.println("+------------------------------------+");
		System.out.println("");

		showDetails("Albums");

		System.out.println("+------------------------------------+");
		System.out.println("| Please Submit the Following Inputs |");
		System.out.println("+------------------------------------+");
		System.out.println("");

		System.out.println("Artist ID: ");
		int AlbumID = reader.nextInt();
		reader.nextLine();

		return execute(AlbumID);	
	}
}
