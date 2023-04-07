package report;
import java.util.Scanner;

public class MonthlyPlayCountPerAlbum {
	public static ExecResult run(Scanner reader) {
		return execute();
	}

	public static ExecResult execute() {
		String sql = 
            "SELECT s.Playcount, al.*" + "\n" +
            "FROM Albums AS al" + "\n" +
            "JOIN Songs AS s ON al.AlbumID=s.AlbumID;";
		return WolfPubDB.executeQuery(sql);
	}

	public static void main(String[] args) {
		System.out.println("\n");
		System.out.println("Unit Test for public class MonthlyPlayCountPerAlbum");
		System.out.println("===============================");
		ExecResult execResults = execute();
		if (execResults.success) {
			System.out.println("MonthlyPlayCountPerAlbum: Success");
		} else {
			System.out.println("MonthlyPlayCountPerAlbum: Failure");
			System.out.println("\tError: " + execResults.errorMessage);
		}
		System.out.println("\n");
	}
}
