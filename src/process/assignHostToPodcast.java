package process;

import java.util.Scanner;
import java.sql.*;

import util.*;
/**
 * Class used for executing the assignHostToPodcast API operation by updating the Podcasts Table.
 */

public class assignHostToPodcast {

    public static String execute(String sql) {
        return queryExecuter.execute(sql);
    }



    public static String run(Scanner reader) throws SQLException{
        System.out.println("+------------------------------------+");
        System.out.println("|           Podcast Details   |");
        System.out.println("+------------------------------------+");
        System.out.println("");

        DBTablePrinter.printTable("Podcasts");

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
