package maintain;

import util.*;
import java.sql.*;
import java.util.Scanner;

public class findSongsGivenArtist {


    public static String run(Scanner reader) throws SQLException{
        System.out.println("+------------------------------------+");
		System.out.println("|           Artist Details           |");
		System.out.println("+------------------------------------+");
		System.out.println("");

		DBTablePrinter.printTable("Artists");

        System.out.println("+------------------------------------+");
        System.out.println("| Please Submit the Following Inputs |");
        System.out.println("+------------------------------------+");
        System.out.println("");

        System.out.println("Artist ID: ");
        int AID = reader.nextInt();
        reader.nextLine();

        String attribute = "ArtistID";

        return execute(AID, attribute);
    }

    public static String execute(int ID, String attribute) {

        String sql =
            "SELECT * " +
            "FROM Songs "  +
            "WHERE %s = %d " +
            ";"
        ;

        sql = String.format(sql, attribute, ID);
        return queryExecuter.execute(sql);
    }
}
