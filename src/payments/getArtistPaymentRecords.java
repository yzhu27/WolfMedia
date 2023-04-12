package payments;

import util.queryExecuter;
import config.Result;

import java.util.Scanner;

public class getArtistPaymentRecords {

    public static Result execute() {

        String sql =
                "SELECT * FROM ArtistPaymentRecords;";

        return queryExecuter.execute(sql);
    }

    public static Result run(Scanner reader) {
        System.out.println("+------------------------------------+");
        System.out.println("|     ArtistPaymentRecords Details   |");
        System.out.println("+------------------------------------+");
        System.out.println("");

        return execute();
    }
}
