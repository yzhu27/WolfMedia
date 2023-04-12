package process;

import java.util.Scanner;
import java.sql.*;

import util.queryExecuter;
import config.Result;

public class assignHostToPodcast {

    public static Result execute(String sql) {
        return queryExecuter.execute(sql);
    }

    public static void showDetails(String tableName){
        String sql = String.format("SELECT * FROM " + tableName + ";");
        queryExecuter.execute(sql);
    }

    public static Result run(Scanner reader) {
        System.out.println("+------------------------------------+");
        System.out.println("|           Podcast Details   |");
        System.out.println("+------------------------------------+");
        System.out.println("");

        showDetails("Podcasts");

        System.out.println("+------------------------------------+");
        System.out.println("| Please Submit the Following Inputs |");
        System.out.println("+------------------------------------+");
        System.out.println("");

        System.out.println("Podcast ID: ");
        int pID = reader.nextInt();
        reader.nextLine();

         System.out.println("Host ID: ");
        int phID = reader.nextInt();
        reader.nextLine();

        String sql = "";
        sql = "UPDATE Podcasts SET PHID = %d WHERE PID=(%d);" + "\n" + "\n";
        sql = String.format(sql, phID, pID);

        return execute(sql);
    }
}
