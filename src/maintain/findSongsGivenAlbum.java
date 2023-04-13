package maintain;

import util.queryExecuter;

import java.util.Scanner;

public class findSongsGivenAlbum {
    public static String run(Scanner reader) {
        System.out.println("+------------------------------------+");
        System.out.println("| Please Submit the Following Inputs |");
        System.out.println("+------------------------------------+");
        System.out.println("");

        System.out.println("Album ID: ");
        int AlID = reader.nextInt();
        reader.nextLine();

        String attribute = "AlbumID";

        return execute(AlID, attribute);
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
