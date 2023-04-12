package config;

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

    public static void main(String[] args) throws ParseException {
        Interface Interface = new Interface();
        Interface.select_user();
    }

    public void select_user() throws ParseException {
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

    public void select_operation() throws ParseException {
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

    public void execute_operation() throws ParseException {

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
                case 16:
                    result = enterSongInfo.run(this.sc);
                    break;
                case 17:
                    result = enterArtistInfo.run(this.sc);
                    break;
                case 18:
                    result = enterAlbumInfo.run(this.sc);
                    break;
                case 19:
                    result = enterPodcastInfo.run(this.sc);
                    break;
                case 20:
                    result = enterHostInfo.run(this.sc);
                    break;
                case 21:
                    result = enterEpisodeInfo.run(this.sc);
                    break;
                case 22:
                    result = updateSongInfo.run(this.sc);
                    break;
                case 23:
                    result = updateArtistInfo.run(this.sc);
                    break;
                case 24:
                    result = updateAlbumInfo.run(this.sc);
                    break;
                case 25:
                    result = updatePodcastInfo.run(this.sc);
                    break;
                case 26:
                    result = updateHostInfo.run(this.sc);
                    break;
                case 27:
                    result = updateEpisodeInfo.run(this.sc);
                    break;
                case 28:
                    result = deleteSongInfo.run(this.sc);
                    break;
                case 29:
                    result = deleteArtistInfo.run(this.sc);
                    break;
                case 30:
                    result = deleteAlbumInfo.run(this.sc);
                    break;
                case 31:
                    result = deletePodcastInfo.run(this.sc);
                    break;
                case 32:
                    result = deleteHostInfo.run(this.sc);
                    break;
                case 33:
                    result = deleteEpisodeInfo.run(this.sc);
                    break;
                case 34:
                    result = assignSongToArtist.run(this.sc);
                    break;
                case 35:
                    result = assignSongToAlbum.run(this.sc);
                    break;
                case 36:
                    result = assignArtistToAlbum.run(this.sc);
                    break;
                case 37:
                    result = assignArtistToLabel.run(this.sc);
                    break;
                case 38:
                    result = assignEpisodeToPodcast.run(this.sc);
                    break;
                case 39:
                    result = assignHostToPodcast.run(this.sc);
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
                case 1:
                    result = calTotalPaymentsToArtistPerGivenTimePeriod.run(this.sc);
                    break;
                case 2:
                    result = calTotalPaymentsToHostPerGivenTimePeriod.run(this.sc);
                    break;
                case 3:
                    result = calTotalPaymentsToLabelPerGivenTimePeriod.run(this.sc);
                    break;
                case 4:
                    result = calTotalRevenue.run(this.sc);
                    break;
                case 5:
                    result = MonthlyPlayCountPerAlbum.run(this.sc);
                    break;
                case 6:
                    result = MonthlyPlayCountPerArtist.run(this.sc);
                    break;
                case 7:
                    result = MonthlyPlayCountPerSong.run(this.sc);
                    break;
                case 8:
                    result = reportEpisodesGivenPodcast.run(this.sc);
                    break;
                case 9:
                    result = reportSongsGivenAlbum.run(this.sc);
                    break;
                case 10:
                    result = reportSongsGivenArtist.run(this.sc);
                    break;
                case 11:
                    result = reportSubscribersAndRatingPerPodcastPerGivenTimePeriod.run(this.sc);
                    break;
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
