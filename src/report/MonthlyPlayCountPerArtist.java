package report;
import java.util.Scanner;

public class MonthlyPlayCountPerArtist {
    public static ExecResult run(Scanner reader) {
		return execute();
	}

	public static ExecResult execute() {
		String sql = 
            "SELECT s.Playcount, ar.*" + "\n" +
            "FROM Artists AS ar" + "\n" +
            "JOIN Songs AS s ON ar.ArtistID=s.ArtistID;";
		return WolfPubDB.executeQuery(sql);
	}

	public static void main(String[] args) {
		System.out.println("\n");
		System.out.println("Unit Test for public class MonthlyPlayCountPerArtist");
		System.out.println("===============================");
		ExecResult execResults = execute();
		if (execResults.success) {
			System.out.println("MonthlyPlayCountPerArtist: Success");
		} else {
			System.out.println("MonthlyPlayCountPerArtist: Failure");
			System.out.println("\tError: " + execResults.errorMessage);
		}
		System.out.println("\n");
	}
}
