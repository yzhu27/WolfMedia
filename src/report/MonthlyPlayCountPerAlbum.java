package report;
import java.util.Scanner;

import config.Connect;
import config.Result;
import config.Transaction;

public class MonthlyPlayCountPerAlbum {
	public static Result run(Scanner reader) {
		return execute();
	}

	public static Result execute() {
		String sql = 
            "SELECT s.Playcount, al.*" + "\n" +
            "FROM Albums AS al" + "\n" +
            "JOIN Songs AS s ON al.AlbumID=s.AlbumID;";
		return Connect.executeQuery(sql);
	}

	public static void main(String[] args) {
		System.out.println("\n");
		System.out.println("Unit Test for public class MonthlyPlayCountPerAlbum");
		System.out.println("===============================");
		Result Results = execute();
		if (Results.success) {
			System.out.println("MonthlyPlayCountPerAlbum: Success");
		} else {
			System.out.println("MonthlyPlayCountPerAlbum: Failure");
			System.out.println("\tError: " + Results.errorMessage);
		}
		System.out.println("\n");
	}
}
