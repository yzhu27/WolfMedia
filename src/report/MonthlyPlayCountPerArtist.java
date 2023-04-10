package report;
import java.util.Scanner;

import config.Connect;
import config.Result;
import config.Transaction;

public class MonthlyPlayCountPerArtist {
    public static Result run(Scanner reader) {
		return execute();
	}

	public static Result execute() {
		String sql = 
            "SELECT s.Playcount, ar.*" + "\n" +
            "FROM Artists AS ar" + "\n" +
            "JOIN Songs AS s ON ar.ArtistID=s.ArtistID;";
		return Connect.executeQuery(sql);
	}

	public static void main(String[] args) {
		System.out.println("\n");
		System.out.println("Unit Test for public class MonthlyPlayCountPerArtist");
		System.out.println("===============================");
		Result Results = execute();
		if (Results.success) {
			System.out.println("MonthlyPlayCountPerArtist: Success");
		} else {
			System.out.println("MonthlyPlayCountPerArtist: Failure");
			System.out.println("\tError: " + Results.errorMessage);
		}
		System.out.println("\n");
	}
}
