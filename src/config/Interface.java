package config;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.NoSuchElementException;
import java.util.Scanner;

import maintain.*;
import payments.*;
import process.*;
import report.*;

/**
 * The Interface class represents a command-line interface for the WolfMedia
 * database management system.
 * It provides functionality to select a user and execute operations based on
 * the user's role.
 */
public class Interface {
    public static void main(String[] args) throws ParseException, SQLException {
        Interface Interface = new Interface();
        Interface.select_user();
    }

    private Scanner sc = new Scanner(System.in);
    // Maintaining metadata and records
    private static final String[] DataAdminOps = {
            "0. Logout",
            "1. Reset the number of listen count for a Podcast Episode to 0",
            "2. Reset the number of monthly listener for an Artist to 0",
            "3. Reset the number of play count for a song to 0",
            "4. Reset the number of rating for a Podcast to 0",
            "5. Reset the number of total subscribers for a Podcast to 0",
            "6. Get the records of all Episodes of a Podcast",
            "7. Get the records of all Songs of an Artist and Album",
            "8. Record a Play Count for a Song",
            "9. Record a Play Count for a Podcast",
            "10. Update the number of listen count for a Podcast Episode",
            "11. Update the number of monthly listener for an Artist",
            "12. Update the number of play count for a Song",
            "13. Update the number of rating for a Podcast",
            "14. Update the number of total subscribers for a Podcast",
            "15. Enter the details of the song to be added",
            "16. Enter the details of the artist to be added",
            "17. Enter the details of the album to be added",
            "18. Enter the details of the podcast to be added",
            "19. Enter the details of the host to be added",
            "20. Enter the details of the episode to be added",
            "21. Update the details of the song",
            "22. Update the details of the artist",
            "23. Update the details of the album",
            "24. Update the details of the podcast",
            "25. Update the details of the podcast host",
            "26. Update the details of the podcast episode",
            "27. delete a song",
            "28. delete an artist",
            "29. delete an album",
            "30. delete a podcast",
            "31. delete a host",
            "32. delete an episode",
            "33. assign a song to an artist",
            "34. assign a song to an album",
            "35. assign an artist to an album",
            "36. assign an artist to label",
            "37. assign an episode to podcast",
            "38. assign a host to podcast",
            "39. Display tables"

    };
    // Maintaining payments
    private static final String[] AccountantOps = {
            "0. Logout",
            "1. Payment For Song",
            "2. Payment For Host",
            "3. Receive Payment From Subscribers",
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
            "5. Report Past Monthly Play Count By Album",
            "6. Report Past Monthly Play Count By Artist",
            "7. Report Past Monthly Play Count By Song",
            "8. Report Episodes By Given Podcast",
            "9. Report Songs By Given Album",
            "10. Report Songs By Given Artist",
            "11. Report Subscribers And Ratings",
            "12. Calculate Total Revenue During Given Year",
            "13. Calculate Total Revenue During Given Month",
            "14. Report Current Monthly Play Count By Album",
            "15. Report Current Monthly Play Count By Artist",
            "16. Report Current Monthly Play Count By Song",

    };
    private static final String[][] userOps = {
            DataAdminOps,
            AccountantOps,
            ManagerOps,
    };

    private int user;
    private int operation;
    private String[] validOperations;

    /**
     * Creates an instance of the Interface class with default values for user,
     * operation, and validOperations.
     */
    public Interface() {
        this.user = -1;
        this.operation = -1;
        this.validOperations = null;
    }

    /**
     * Displays a menu to select a user and executes the corresponding operation.
     *
     * @throws ParseException if an error occurs while parsing the user input
     * @throws SQLException   if a database access error occurs
     */
    public void select_user() throws ParseException, SQLException {
        while (true) {

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
                    } else if (1 <= this.user && this.user <= userOps.length) {
                        this.validOperations = userOps[this.user - 1];
                        positionInvalid = false;
                    } else {
                        System.out.print("Invalid Option; Please try again: ");
                    }
                } catch (Exception e) {
                    System.out.print("Invalid Option; Please try again: ");
                }
            }

            this.select_operation();
        }
    }

    /**
     * Allows the user to select an operation from a list of valid operations and
     * execute it.
     *
     * @throws ParseException if there is an error parsing the input.
     * @throws SQLException   if there is an error executing the selected operation.
     */
    public void select_operation() throws ParseException, SQLException {
        while (true) {
            System.out.print("\033\143");

            System.out.println("+--------------------------------+");
            System.out.println("| Select an Operation to Execute |");
            System.out.println("+--------------------------------+");
            System.out.println("");
            System.out.println("Available Operations");
            System.out.println("====================");
            for (String s : this.validOperations) {
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
                    } else if (1 <= this.operation && this.operation <= this.validOperations.length-1) {
                        operationInvalid = false;
                    } else {
                        System.out.print("Invalid Option; Please try again: ");
                    }
                } catch (Exception e) {
                    System.out.print("Invalid Option; Please try again: ");
                }
            }

            this.execute_operation();
        }
    }

    /**
     *
     * This method executes the selected operation based on the user type and the
     * operation selected.
     *
     * @throws ParseException if there is an error parsing the input
     * @throws SQLException   if there is an error with the SQL database
     */
    public void execute_operation() throws ParseException, SQLException {
        System.out.print("\033\143");

        String result = "null";

        if (this.user == 1) {
            switch (this.operation) {
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
                    result = findSongsGivenArtistAndAlbum.run(this.sc);
                    break;
                case 8:
                    result = recordPlayCount.run(this.sc);
                    break;
                case 9:
                    result = recordPodcast.run(this.sc);
                    break;
                case 10:
                    result = updateListeningCount.run(this.sc);
                    break;
                case 11:
                    result = updateMonthlyListeners.run(this.sc);
                    break;
                case 12:
                    result = updatePlayCount.run(this.sc);
                    break;
                case 13:
                    result = updateRating.run(this.sc);
                    break;
                case 14:
                    result = updateTotalSubscribers.run(this.sc);
                    break;
                case 15:
                    result = enterSongInfo.run(this.sc);
                    break;
                case 16:
                    result = enterArtistInfo.run(this.sc);
                    break;
                case 17:
                    result = enterAlbumInfo.run(this.sc);
                    break;
                case 18:
                    result = enterPodcastInfo.run(this.sc);
                    break;
                case 19:
                    result = enterHostInfo.run(this.sc);
                    break;
                case 20:
                    result = enterEpisodeInfo.run(this.sc);
                    break;
                case 21:
                    result = updateSongInfo.run(this.sc);
                    break;
                case 22:
                    result = updateArtistInfo.run(this.sc);
                    break;
                case 23:
                    result = updateAlbumInfo.run(this.sc);
                    break;
                case 24:
                    result = updatePodcastInfo.run(this.sc);
                    break;
                case 25:
                    result = updateHostInfo.run(this.sc);
                    break;
                case 26:
                    result = updateEpisodeInfo.run(this.sc);
                    break;
                case 27:
                    result = deleteSongInfo.run(this.sc);
                    break;
                case 28:
                    result = deleteArtistInfo.run(this.sc);
                    break;
                case 29:
                    result = deleteAlbumInfo.run(this.sc);
                    break;
                case 30:
                    result = deletePodcastInfo.run(this.sc);
                    break;
                case 31:
                    result = deleteHostInfo.run(this.sc);
                    break;
                case 32:
                    result = deleteEpisodeInfo.run(this.sc);
                    break;
                case 33:
                    result = assignSongToArtist.run(this.sc);
                    break;
                case 34:
                    result = assignSongToAlbum.run(this.sc);
                    break;
                case 35:
                    result = assignArtistToAlbum.run(this.sc);
                    break;
                case 36:
                    result = assignArtistToLabel.run(this.sc);
                    break;
                case 37:
                    result = assignEpisodeToPodcast.run(this.sc);
                    break;
                case 38:
                    result = assignHostToPodcast.run(this.sc);
                    break;
                case 39:
                    result = getTables.run(this.sc);
                    break;
                default:
                    return;
            }
        } else if (this.user == 2) {
            switch (this.operation) {
                case 1:
                    result = paymentForSong.run(this.sc);
                    break;
                case 2:
                    result = paymentForHost.run(this.sc);
                    break;
                case 3:
                    result = paymentFromSubscribers.run(this.sc);
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
                case 16:
                    result = delArtistPaymentRecord.run(this.sc);
                    break;
                case 17:
                    result = delHostPaymentRecord.run(this.sc);
                    break;
                case 18:
                    result = delLabelPaymentRecord.run(this.sc);
                    break;
                case 19:
                    result = delRevenueRecord.run(this.sc);
                    break;
                default:
                    return;
            }
        } else if (this.user == 3) {
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
                    result = prevMonthlyPlayCountPerAlbum.run(this.sc);
                    break;
                case 6:
                    result = prevMonthlyPlayCountPerArtist.run(this.sc);
                    break;
                case 7:
                    result = prevMonthlyPlayCountPerSong.run(this.sc);
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
                case 12:
                    result = calTotalRevenuePerYear.run(this.sc);
                    break;
                case 13:
                    result = calTotalRevenuePerMonth.run(this.sc);
                    break;
                case 14:
                    result = currMonthlyPlayCountPerAlbum.run(this.sc);
                    break;
                case 15:
                    result = currMonthlyPlayCountPerArtist.run(this.sc);
                    break;
                case 16:
                    result = currMonthlyPlayCountPerSong.run(this.sc);
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
