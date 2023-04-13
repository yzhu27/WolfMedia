package process;

import java.util.Scanner;
import java.sql.*;

import util.*;


public class assignEpisodeToPodcast {

    public static String execute(String sql) {
        return queryExecuter.execute(sql);
    }



    public static String run(Scanner reader) throws SQLException{
        System.out.println("+-------------------------------+");
        System.out.println("|      PodcastEpisode Details   |");
        System.out.println("+-------------------------------+");
        System.out.println("");

        DBTablePrinter.printTable("PodcastEpisodes");

        System.out.println("+------------------------------------+");
        System.out.println("| Please Submit the Following Inputs |");
        System.out.println("+------------------------------------+");
        System.out.println("");

        System.out.println("Podcast Episode ID: ");
        int peID = reader.nextInt();
        reader.nextLine();

         System.out.println("Podcast ID: ");
        int pID = reader.nextInt();
        reader.nextLine();

        String sql = "";
        sql = "UPDATE PodcastEpisodes SET PID=%d WHERE PEID=(%d);" + "\n" + "\n";
        sql = String.format(sql, pID, peID);

        return execute(sql);
    }
}
