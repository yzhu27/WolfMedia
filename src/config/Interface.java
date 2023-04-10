package config;

import java.text.ParseException;
import java.util.NoSuchElementException;
import java.util.Scanner;

import maintain.enterListeningCount;
import maintain.enterMonthlyListeners;
import maintain.enterPlayCount;
import maintain.enterRating;
import maintain.enterTotalSubscribers;
import maintain.findEpisodesGivenPodcast;
import maintain.findSongsGivenAlbum;
import maintain.findSongsGivenArtist;
import maintain.recordPlayCount;
import maintain.recordPodcast;
import maintain.updateListeningCount;
import maintain.updateMonthlyListeners;
import maintain.updatePlayCount;
import maintain.updateRating;
import maintain.updateTotalSubscribers;
import  payments.*;

public class Interface {

    private static Scanner sc = new Scanner(System.in);
    // Information Processing + Maintaining metadata and records
    private static final String[] DataAdminOps = {
            "0. Logout",
            "1. Reset the number of listen count for a Podcast Episode to 0",
            "2. Reset the number of monthly listener for an Artist to 0",
            "3. Reset the number of play count for a song to 0",
            "4. Reset the number of rating for a Podcast to 0",
            "5. Reset the number of total subscribers for a Podcast to 0",
            "6. Get the records of all Episodes of a Podcast",
            "7. Get the records of all Songs of a Album",
            "8. Get the records of all Songs of an Artist, as the primary publisher",
            "9. Record a Play Count for a Song",
            "10. Record a Play Count for a Podcast",
            "11. Update the number of listen count for a Podcast Episode",
            "12. Update the number of monthly listener for an Artist",
            "13. Update the number of play count for a Song",
            "14. Update the number of rating for a Podcast",
            "15. Update the number of total subscribers for a Podcast",
    };
    // Maintaining payments
    private static final String[] AccountantOps = {
            "0. Logout",
            "1. Payment For Song",
            "2. Payment For Host",
            "3. Receive Revenue",
            "4. Get Artist Payment Records",
            "5. Get Host Payment Records",
            "6. Get Record Label Payment Records",
            "7. Get Revenue Records",
            "8. Add Artist Payment Records",
            "9. Add Host Payment Records",
            "10. Add Record Label Payment Records",
            "11. Add Revenue Records",
            "12. Update Artist Payment Records",
            "13. Update Host Payment Records",
            "14. Update Record Label Payment Records",
            "15. Update Revenue Records",
    };
    // Reports
    private static final String[] ManagerOps = {
            "0. Logout",
            "1. View Publication",
            "2. Add Book Chapter",
            "3. Delete Book Chapter",
            "4. Add Article",
            "5. Delete Article",
    };
    private static final String[][] WolfCityOpsMapping = {
            DataAdminOps,
            AccountantOps,
            ManagerOps,
    };

    private int position;
    private int operation;
    private String[] validOperations;

    public Interface(){
        this.position = -1;
        this.operation = -1;
        this.validOperations = null;
    }

    public static void main(String[] args) throws ParseException {
        Interface Interface = new Interface();
        Interface.select_position();
    }

    public void select_position() throws ParseException {
        while (true){
            System.out.print("\033\143");

            System.out.println("+--------------------------------------+");
            System.out.println("|       Welcome to WolfMedia DBMS      |");
            System.out.println("+--------------------------------------+");
            System.out.println("");
            System.out.println("User Options");
            System.out.println("============");
            System.out.println("0. Exit");
            System.out.println("1. Data Admin");
            System.out.println("2. Accountant");
            System.out.println("3. Manager");
            System.out.println("");
            System.out.println("");
            System.out.print("Selected User: ");

            boolean positionInvalid = true;
            while (positionInvalid) {
                try {
                    this.position = Integer.parseInt(this.sc.nextLine());
                    if (this.position == 0) {
                        this.sc.close();
                        System.out.print("\033[H\033[2J");
                        System.out.flush();
                        System.exit(1);
                    }
                    else if (1 <= this.position && this.position <= WolfCityOpsMapping.length) {
                        this.validOperations = WolfCityOpsMapping[this.position-1];
                        positionInvalid = false;
                    } else {
                        System.out.print("Invalid Option; Please try again: ");
                    }
                } catch(Exception e){
                    System.out.print("Invalid Option; Please try again: ");
                }
            }

            this.select_operation();
        }
    }

    public void select_operation() throws ParseException {
        while (true) {
            System.out.print("\033\143");

            System.out.println("+--------------------------------+");
            System.out.println("| Select an Operation to Execute |");
            System.out.println("+--------------------------------+");
            System.out.println("");
            System.out.println("Available Operations");
            System.out.println("====================");
            for (String s: this.validOperations){
                System.out.println(s);
            }
            System.out.println("");
            System.out.println("");
            System.out.print("Selected Operation: ");

            boolean operationInvalid = true;
            while (operationInvalid) {
                try {
                    this.operation = Integer.parseInt(this.sc.nextLine());
                    if (this.operation == 0) {
                        return;
                    }
                    else if (1 <= this.operation && this.operation <= this.validOperations.length-1) {
                        operationInvalid = false;
                    } else {
                        System.out.print("Invalid Option; Please try again: ");
                    }
                } catch(Exception e){
                    System.out.print("Invalid Option; Please try again: ");
                }
            }

            this.execute_operation();
        }
    }

    public void execute_operation() throws ParseException {
        System.out.print("\033\143");

        Result result = null;

        if(this.position == 1){
            switch(this.operation){
                case 1:
                    result = enterListeningCount.run(this.sc);
                    break;
                case 2:
                    result = enterMonthlyListeners.run(this.sc);
                    break;
                case 3:
                    result = enterPlayCount.run(this.sc);
                    break;
                case 4:
                    result = enterRating.run(this.sc);
                    break;
                case 5:
                    result = enterTotalSubscribers.run(this.sc);
                    break;
                case 6:
                    result = findEpisodesGivenPodcast.run(this.sc);
                    break;
                case 7:
                    result = findSongsGivenAlbum.run(this.sc);
                    break;
                case 8:
                    result = findSongsGivenArtist.run(this.sc);
                    break;
                case 9:
                    result = recordPlayCount.run(this.sc);
                    break;
                case 10:
                    result = recordPodcast.run(this.sc);
                    break;
                case 11:
                    result = updateListeningCount.run(this.sc);
                    break;
                case 12:
                    result = updateMonthlyListeners.run(this.sc);
                    break;
                case 13:
                    result = updatePlayCount.run(this.sc);
                    break;
                case 14:
                    result = updateRating.run(this.sc);
                    break;
                case 15:
                    result = updateTotalSubscribers.run(this.sc);
                    break;
                default:
                    return;
            }
        }
        else if (this.position == 2) {
            switch (this.operation) {
                case 1:
                    result = paymentForSong.run(this.sc);
                    break;
                case 2:
                    result = paymentForHost.run(this.sc);
                    break;
                case 3:
                    result = revenue.run(this.sc);
                    break;
                case 4:
                    result = getArtistPaymentRecords.run(this.sc);
                    break;
                case 5:
                    result = getHostPaymentRecords.run(this.sc);
                    break;
                case 6:
                    result = getLabelPaymentRecords.run(this.sc);
                    break;
                case 7:
                    result = getRevenueRecords.run(this.sc);
                    break;
                case 8:
                    result = addArtistPaymentRecord.run(this.sc);
                    break;
                case 9:
                    result = addHostPaymentRecord.run(this.sc);
                    break;
                case 10:
                    result = addLabelPaymentRecord.run(this.sc);
                    break;
                case 11:
                    result = addRevenueRecords.run(this.sc);
                    break;
                case 12:
                    result = updateArtistPaymentRecords.run(this.sc);
                    break;
                case 13:
                    result = updateHostPaymentRecords.run(this.sc);
                    break;
                case 14:
                    result = updateLabelPaymentRecords.run(this.sc);
                    break;
                case 15:
                    result = updateRevenueRecords.run(this.sc);
                    break;
                default:
                    return;
            }
        }
        else if (this.position == 3) {
            switch (this.operation) {
                default:
                    return;
            }
        }


        if (result.success) {
            System.out.println("API Status: Success");
        } else {
            System.out.println("API Status: Failure");
            System.out.println("\tError: " + result.errorMessage);
        }
        System.out.println("");

        System.out.print("Press Enter to Continue...");
        try {
            this.sc.nextLine();
        } catch (NoSuchElementException error) {
        }
    }
}
