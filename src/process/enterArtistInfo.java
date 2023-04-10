import java.util.Scanner;

import config.Connect;
import config.Result;

public class enterArtistInfo {

	public static Result run(Scanner reader) {
		System.out.println("+------------------------------------+");
		System.out.println("| Please Submit the Following Inputs |");
		System.out.println("+------------------------------------+");
		System.out.println("");

		System.out.println("Artist ID: ");
		int artistID = reader.nextInt();
		reader.nextLine();

		System.out.println("Name: ");
		String name = reader.nextLine();

		System.out.println("Status: ");
		String status = reader.nextLine();

		System.out.println("Type: ");
		String type = reader.nextLine();

		System.out.println("Art Country: ");
		String artCountry = reader.nextLine();

		System.out.println("Monthly Listeners: ");
		int monthlyListeners = reader.nextInt();
		reader.nextLine();

		System.out.println("Primary Genre: ");
		String primaryGenre = reader.nextLine();

		System.out.println("Record Label ID: ");
		int rlID = reader.nextInt();
		reader.nextLine();

		return execute(artistID, name, status, type, artCountry, monthlyListeners, primaryGenre, rlID);	
	}

	public static Result execute(int artistID, String name, String status, String type, String artCountry, int monthlyListeners, String primaryGenre, int rlID) {
		
		String sql = 
			"INSERT INTO Artists VALUES "  + "\n" + "\t" +
				"(%d, '%s', '%s', '%s', '%s', %d, '%s', %d)"  + "\n" +
			";" + "\n" + "\n"
		;
		
		sql = String.format(sql, artistID, name, status, type, artCountry, monthlyListeners, primaryGenre, rlID);
		
		return Connect.executeUpdate(sql);
	}

	public static void main(String[] args) {
		
	}

}
