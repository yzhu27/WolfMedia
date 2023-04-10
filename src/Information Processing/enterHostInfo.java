import java.util.Scanner;

public class enterHostInfo {
    
    public static ExecResult run(Scanner reader) {
        System.out.println("+------------------------------------+");
        System.out.println("| Please Submit the Following Inputs |");
        System.out.println("+------------------------------------+");
        System.out.println("");

        System.out.println("Podcast Host ID: ");
        int phID = reader.nextInt();
        reader.nextLine();

        System.out.println("First Name: ");
        String phFName = reader.nextLine();

        System.out.println("Last Name: ");
        String phLName = reader.nextLine();

        System.out.println("Email: ");
        String phEmail = reader.nextLine();

        System.out.println("City: ");
        String phCity = reader.nextLine();

        return execute(phID, phFName, phLName, phEmail, phCity);    
    }

    public static ExecResult execute(int phID, String phFName, String phLName, String phEmail, String phCity) {
        
        String sql = 
            "INSERT INTO PodcastHosts VALUES "  + "\n" + "\t" +
                "(%d, '%s', '%s', '%s', '%s')"  + "\n" +
            ";"
        ;
        
        sql = String.format(sql, phID, phFName, phLName, phEmail, phCity);
        
        return WolfPubDB.executeUpdate(sql);
    }

    public static void main(String[] args) {

        System.out.println("\n");
        System.out.println("Unit Test for AddPodcastHost");
        System.out.println("===============================");
        
        execute(6001, "Hideo", "Kojima", "hkjima@gameludens.com", "Tokyo"))
    }
}
