package config;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.NoSuchElementException;
import java.util.Scanner;

import maintain.*;
import payments.*;
import process.*;
import report.*;

public class Interface {

    private Scanner sc = new Scanner(System.in);
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
            "16. Enter the details of the song to be added",
            "17. Enter the details of the artist to be added",
            "18. Enter the details of the album to be added",
            "19. Enter the details of the podcast to be added",
            "20. Enter the details of the host to be added",
            "21. Enter the details of the episode to be added",
            "22. Update the details of the song",
            "23. Update the details of the artist",
            "24. Update the details of the album",
            "25. Update the details of the podcast",
            "26. Update the details of the podcast host",
            "27. Update the details of the podcast episode",
            "28. delete a song",
            "29. delete an artist",
            "30. delete an album",
            "31. delete a podcast",
            "32. delete a host",
            "33. delete an episode",
            "34. assign a song to an artist",
            "35. assign a song to an album",
            "36. assign an artist to an album",
            "37. assign an artist to label",
            "38. assign an episode to podcast",
            "39. assign a host to podcast",
            "40. Display tables",
        
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
            "16. Delete Artist Payment Records",
            "17. Delete Host Payment Records",
            "18. Delete Record Label Payment Records",
            "19. Delete Revenue Records",
    };
    // Reports
    private static final String[] ManagerOps = {
            "0. Logout",
            "1. Calculate Total Payment To Artist",
            "2. Calculate Total Payment To Host",
            "3. Calculate Total Payment To Label",
            "4. Calculate Total Revenue",
            "5. Report Monthly Play Count By Album",
            "6. Report Monthly Play Count By Artist",
            "7. Report Monthly Play Count By Song",
            "8. Report Episodes By Given Podcast",
            "9. Report Songs By Given Album",
            "10. Report Songs By Given Artist",
            "11. Report Subscribers And Ratings",
    };
    private static final String[][] userOps = {
            DataAdminOps,
            AccountantOps,
            ManagerOps,
    };

    private int user;
    private int operation;
    private String[] validOperations;

    public Interface(){
        this.user = -1;
        this.operation = -1;
        this.validOperations = null;
    }

    public static void main(String[] args) throws ParseException, SQLException {
        Interface Interface = new Interface();
        Interface.select_user();
    }

    public void select_user() throws ParseException, SQLException {
        while (true){

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
                    this.user = Integer.parseInt(this.sc.nextLine());
                    if (this.user == 0) {
                        this.sc.close();
                        System.out.flush();
                        System.exit(1);
                    }
                    else if (1 <= this.user && this.user <= userOps.length) {
                        this.validOperations = userOps[this.user-1];
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

    public void select_operation() throws ParseException, SQLException {
        while (true) {

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

    public void execute_operation() throws ParseException, SQLException {

        String result = "null";

        if(this.user == 1){
            switch(this.operation){
                case 1:
                    result = enterListeningCount(this.sc).run();
                    break;
                case 2:
                    result = enterMonthlyListeners(this.sc).run();
                    break;
                case 3:
                    result = enterPlayCount(this.sc).run();
                    break;
                case 4:
                    result = enterRating(this.sc).run();
                    break;
                case 5:
                    result = enterTotalSubscribers(this.sc).run();
                    break;
                case 6:
                    result = findEpisodesGivenPodcast(this.sc).run();
                    break;
                case 7:
                    result = findSongsGivenAlbum(this.sc).run();
                    break;
                case 8:
                    result = findSongsGivenArtist(this.sc).run();
                    break;
                case 9:
                    result = recordPlayCount(this.sc).run();
                    break;
                case 10:
                    result = recordPodcast(this.sc).run();
                    break;
                case 11:
                    result = updateListeningCount(this.sc).run();
                    break;
                case 12:
                    result = updateMonthlyListeners(this.sc).run();
                    break;
                case 13:
                    result = updatePlayCount(this.sc).run();
                    break;
                case 14:
                    result = updateRating(this.sc).run();
                    break;
                case 15:
                    result = updateTotalSubscribers(this.sc).run();
                    break;
                case 16:
                    result = enterSongInfo(this.sc).run();
                    break;
                case 17:
                    result = enterArtistInfo(this.sc).run();
                    break;
                case 18:
                    result = enterAlbumInfo(this.sc).run();
                    break;
                case 19:
                    result = enterPodcastInfo(this.sc).run();
                    break;
                case 20:
                    result = enterHostInfo(this.sc).run();
                    break;
                case 21:
                    result = enterEpisodeInfo(this.sc).run();
                    break;
                case 22:
                    result = updateSongInfo(this.sc).run();
                    break;
                case 23:
                    result = updateArtistInfo(this.sc).run();
                    break;
                case 24:
                    result = updateAlbumInfo(this.sc).run();
                    break;
                case 25:
                    result = updatePodcastInfo(this.sc).run();
                    break;
                case 26:
                    result = updateHostInfo(this.sc).run();
                    break;
                case 27:
                    result = updateEpisodeInfo(this.sc).run();
                    break;
                case 28:
                    result = deleteSongInfo(this.sc).run();
                    break;
                case 29:
                    result = deleteArtistInfo(this.sc).run();
                    break;
                case 30:
                    result = deleteAlbumInfo(this.sc).run();
                    break;
                case 31:
                    result = deletePodcastInfo(this.sc).run();
                    break;
                case 32:
                    result = deleteHostInfo(this.sc).run();
                    break;
                case 33:
                    result = deleteEpisodeInfo(this.sc).run();
                    break;
                case 34:
                    result = assignSongToArtist(this.sc).run();
                    break;
                case 35:
                    result = assignSongToAlbum(this.sc).run();
                    break;
                case 36:
                    result = assignArtistToAlbum(this.sc).run();
                    break;
                case 37:
                    result = assignArtistToLabel(this.sc).run();
                    break;
                case 38:
                    result = assignEpisodeToPodcast(this.sc).run();
                    break;
                case 39:
                    result = assignHostToPodcast(this.sc).run();
                    break;
                case 40:
                    result = getTables(this.sc).run();
                    break;
                default:
                    return;
            }
        }
        else if (this.user == 2) {
            switch (this.operation) {
                case 1:
                    result = paymentForSong(this.sc).run();
                    break;
                case 2:
                    result = paymentForHost(this.sc).run();
                    break;
                case 3:
                    result = revenue(this.sc).run();
                    break;
                case 4:
                    result = getArtistPaymentRecords(this.sc).run();
                    break;
                case 5:
                    result = getHostPaymentRecords(this.sc).run();
                    break;
                case 6:
                    result = getLabelPaymentRecords(this.sc).run();
                    break;
                case 7:
                    result = getRevenueRecords(this.sc).run();
                    break;
                case 8:
                    result = addArtistPaymentRecord(this.sc).run();
                    break;
                case 9:
                    result = addHostPaymentRecord(this.sc).run();
                    break;
                case 10:
                    result = addLabelPaymentRecord(this.sc).run();
                    break;
                case 11:
                    result = addRevenueRecords(this.sc).run();
                    break;
                case 12:
                    result = updateArtistPaymentRecords(this.sc).run();
                    break;
                case 13:
                    result = updateHostPaymentRecords(this.sc).run();
                    break;
                case 14:
                    result = updateLabelPaymentRecords(this.sc).run();
                    break;
                case 15:
                    result = updateRevenueRecords(this.sc).run();
                    break;
                case 16:
                    result = delArtistPaymentRecord(this.sc).run();
                    break;
                case 17:
                    result = delHostPaymentRecord(this.sc).run();
                    break;
                case 18:
                    result = delLabelPaymentRecord(this.sc).run();
                    break;
                case 19:
                    result = delRevenueRecord(this.sc).run();
                    break;
                default:
                    return;
            }
        }
        else if (this.user == 3) {
            switch (this.operation) {
                case 1:
                    result = calTotalPaymentsToArtistPerGivenTimePeriod(this.sc).run();
                    break;
                case 2:
                    result = calTotalPaymentsToHostPerGivenTimePeriod(this.sc).run();
                    break;
                case 3:
                    result = calTotalPaymentsToLabelPerGivenTimePeriod(this.sc).run();
                    break;
                case 4:
                    result = calTotalRevenue(this.sc).run();
                    break;
                case 5:
                    result = MonthlyPlayCountPerAlbum(this.sc).run();
                    break;
                case 6:
                    result = MonthlyPlayCountPerArtist(this.sc).run();
                    break;
                case 7:
                    result = MonthlyPlayCountPerSong(this.sc).run();
                    break;
                case 8:
                    result = reportEpisodesGivenPodcast(this.sc).run();
                    break;
                case 9:
                    result = reportSongsGivenAlbum(this.sc).run();
                    break;
                case 10:
                    result = reportSongsGivenArtist(this.sc).run();
                    break;
                case 11:
                    result = reportSubscribersAndRatingPerPodcastPerGivenTimePeriod(this.sc).run();
                    break;
                default:
                    return;
            }
        }


        if (result.substring(0, 7).equalsIgnoreCase("success")) {
            System.out.println("API Status: Success");
        } else {
            System.out.println("API Status: Failure");
            System.out.println(result);
        }
        System.out.println("");

        System.out.print("Press Enter to Continue...");
        try {
            this.sc.nextLine();
        } catch (NoSuchElementException error) {
        }
    }
}
