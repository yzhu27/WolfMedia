package payments;

import util.queryExecuter;


import java.util.Scanner;

public class getArtistPaymentRecords {

    public static String execute() {

        String sql =
                "SELECT * FROM ArtistPaymentRecords;";

        return queryExecuter.execute(sql);
    }

    public static String run(Scanner reader) {
        System.out.println("+------------------------------------+");
        System.out.println("|     ArtistPaymentRecords Details   |");
        System.out.println("+------------------------------------+");
        System.out.println("");

        return execute();
    }
}
