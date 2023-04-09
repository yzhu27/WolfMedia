import java.util.Scanner;

public class enterAlbumInfo {

    public static ExecResult run(Scanner reader) {
        System.out.println("+------------------------------------+");
        System.out.println("| Please Submit the Following Inputs |");
        System.out.println("+------------------------------------+");
        System.out.println("");

        System.out.println("Album ID: ");
        int albumID = reader.nextInt();
        reader.nextLine();

        System.out.println("Album Name: ");
        String albumName = reader.nextLine();

        System.out.println("Release Year: ");
        int releaseYear = reader.nextInt();
        reader.nextLine();

        System.out.println("Edition: ");
        String edition = reader.nextLine();

        return execute(albumID, albumName, releaseYear, edition);	
    }

    public static ExecResult execute(int albumID, String albumName, int releaseYear, String edition) {

        String sql = 
            "INSERT INTO Albums VALUES "  + "\n" + "\t" +
                "(%d, '%s', %d, '%s')"  + "\n" +
            ";"
        ;
        
        sql = String.format(sql, albumID, albumName, releaseYear, edition);
        
        return WolfPubDB.executeUpdate(sql);
    }

    public static void main(String[] args) {
        System.out.println("\n");
        System.out.println("Unit Test for AddAlbum");
        System.out.println("===============================");
        execute(1, "Thriller", 1982, "Special Edition");
    }

}
