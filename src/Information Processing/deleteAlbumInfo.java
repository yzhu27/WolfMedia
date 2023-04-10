import java.util.Scanner;

public class deleteAlbumInfo {

    public static void showDetails(String tableName){
        String sql = String.format("SELECT * FROM " + tableName + ";");
        System.out.println("sql::" + sql);
		WolfPubDB.executeQuery(sql);
    }

    public static ExecResult execute(int albumID) {

        ExecResult result = null;

        String sql = 
			"DELETE FROM Albums WHERE AlbumID = %d"  + "\n" + "\t" +
				 "\n" +
			";" + "\n" + "\n"
		;
        
		sql = String.format(sql, albumID);

		return WolfPubDB.executeUpdate(sql);
	}

	public static void main(String[] args) {
		System.out.println("\n");
		System.out.println("Unit Test for deleteAlbumInfo");
		System.out.println("===============================");
		execute(1);
	}



    public static ExecResult run(Scanner reader) {

		System.out.println("+------------------------------------+");
		System.out.println("|         Album Details               |");
		System.out.println("+------------------------------------+");
		System.out.println("");

        showDetails("Albums");

		System.out.println("+------------------------------------+");
		System.out.println("| Please Submit the Following Inputs |");
		System.out.println("+------------------------------------+");
		System.out.println("");

		System.out.println("Album ID: ");
		int albumID = reader.nextInt();
		reader.nextLine();

		return execute(albumID);	
	}

}
