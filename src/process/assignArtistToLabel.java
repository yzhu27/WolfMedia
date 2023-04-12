package process;

import java.util.Scanner;
import java.sql.*;

import util.queryExecuter;
import config.Result;

public class assignArtistToLabel {

    public static Result execute(String sql) {
        return queryExecuter.execute(sql);
    }

    public static void showDetails(String tableName){
        String sql = String.format("SELECT * FROM " + tableName + ";");
        queryExecuter.execute(sql);
    }

    public static Result run(Scanner reader) {
        System.out.println("+------------------------------------+");
        System.out.println("|           Artist Details            |");
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

         System.out.println("RecordLabel ID: ");
        int rlID = reader.nextInt();
        reader.nextLine();

        String sql = "";
        sql = "UPDATE Artists SET RLID=%d WHERE ArtistID=(%d);" + "\n" + "\n";
        sql = String.format(sql, rlID, artistID);

        return execute(sql);
    }
}
