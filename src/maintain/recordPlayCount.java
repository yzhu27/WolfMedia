package maintain;

import config.Connect;
import config.Result;
import java.util.Scanner;

public class recordPlayCount {
    public static Result run(Scanner reader) {
        System.out.println("+------------------------------------+");
        System.out.println("| Please Submit the Following Inputs |");
        System.out.println("+------------------------------------+");
        System.out.println("");

        System.out.println("SongID: ");
        int SID = reader.nextInt();
        reader.nextLine();

        System.out.println("SongRecordDate: YYYY-MM-DD");
        String SRDate = reader.nextLine();
        reader.nextLine();

        System.out.println("PlayCount: ");
        int SRPlaycount = reader.nextInt();
        reader.nextLine();

        return execute(SRDate, SID, SRPlaycount);
    }
    public static Result execute(String PayDate, int SID, int Playcount) {

        String sql =
                "INSERT INTO SongRecords VALUES " +
                        "(%s, '%d', '%d')" +
                        ";"
                ;
        sql = String.format(sql, PayDate, SID, Playcount);

        return Connect.executeQuery(sql);
    }
}
