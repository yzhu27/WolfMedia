package config;

/**
 * Initialize our DB by dropping the existing tables and creating them again.
 *
 * The database is constructed using the following 3 steps in order:
 *	1) Drop all existing tables
 *	2) Create tables using schema definitions (with types, constrains, keys, etc.)
 *	3) Insert example data into tables (including DEMO data)
 */

import java.sql.*;

public class Init {

    /* All the table/schema names included in our WolfPubDB implementation */
    private static String[] tables = {
            "Users",
            "RecordLabels",
            "Artists",
            "Albums",
            "Songs",
            "PodcastHosts",
            "Podcasts",
            "PodcastEpisodes",
            "ArtistPaymentRecords",
            "LabelPaymentRecords",
            "HostPaymentRecords",
            "RevenueRecords",
            "SongRecords",
            "PodcastRecords",
            "Genres",
            "SongGenre",
            "PodcastGenre",
            "Make",
            "Collaborate",
            "Sponsors",
            "Fund",
            "Guests",
            "AppearIn",
    };


    /**
     * Main - entry point that drops the existing tables, defines the new ones,
     * and populates them with the demo data.
     */
    public static void main(String[] args) {
        dropTables();
        createTables();
        //populateTables();
    }



    /* ################################################################################################################################################################# */
    /* ********************************************************************** */
    /* *********************** Connect & Drop Tables ************************ */
    /* ********************************************************************** */
    /* ################################################################################################################################################################# */


    /**
     * Function used to drop all the tables associated with the databse listed above.
     */
    private static void dropTables() {
        for (String table : tables)
        {
            String sql = "DROP TABLE IF EXISTS " + table + ";";
            // System.out.println(sql);
            Connect.executeUpdate(sql);
        }
    }



    /* ################################################################################################################################################################# */
    /* ********************************************************************** */
    /* *************************** Table Creation *************************** */
    /* ********************************************************************** */
    /* ################################################################################################################################################################# */


    /**
     * Function that runs the sequence of SQL statements that define the shemas
     * for all the tables in our WolfPubDB implementation. This includes all
     * the key assignments and constraint checks associated with these tables
     * and their fields.
     */
    private static void createTables() {

        String SQL;

        /* Users */
        SQL =
                "CREATE TABLE Users ("  + "\n" +
                        "UID INT PRIMARY KEY,"  + "\n" +
                        "UEmail VARCHAR(255) NOT NULL,"  + "\n" +
                        "UFName VARCHAR(255) NOT NULL,"  + "\n" +
                        "ULName VARCHAR(255) NOT NULL,"  + "\n" +
                        "UPhone VARCHAR(20),"  + "\n" +
                        "URegDate DATE 	NOT NULL,"  + "\n" +
                        "UStatus VARCHAR(20) NOT NULL,"  + "\n" +
                        "UMonthlyFee DECIMAL(10,2) NOT NULL"  + "\n" +
                        ");"
        ;
        Connect.executeUpdate(SQL);
        /* ------------------------------------------------------------------ */

        /* RecordLabels */
        SQL =
                "CREATE TABLE RecordLabels ("  + "\n" +
                        "RLID INT PRIMARY KEY,"  + "\n" +
                        "RLName VARCHAR(255) NOT NULL"  + "\n" +
                        ");"
        ;
        Connect.executeUpdate(SQL);
        /* ------------------------------------------------------------------ */

        /* Artists */
        SQL =
                "CREATE TABLE Artists ("  + "\n" +
                        "ArtistID INT PRIMARY KEY,"  + "\n" +
                        "Name VARCHAR(128) NOT NULL,"  + "\n" +
                        "Status VARCHAR(128) NOT NULL,"  + "\n" +
                        "Type VARCHAR(128) NOT NULL,"  + "\n" +
                        "ArtCountry VARCHAR(128),"  + "\n" +
                        "MonthlyListeners INT NOT NULL,"  + "\n" +
                        "PrimaryGenre VARCHAR(128) NOT NULL,"  + "\n" +
                        "RLID INT NOT NULL,"  + "\n" +
                        "FOREIGN KEY (RLID) REFERENCES RecordLabels(RLID) ON UPDATE CASCADE"  + "\n" +
                        ");"
        ;
        Connect.executeUpdate(SQL);
        /* ------------------------------------------------------------------ */

        /* Albums */
        SQL =
                "CREATE TABLE Albums ("  + "\n" +
                        "AlbumID INT PRIMARY KEY,"  + "\n" +
                        "AlName VARCHAR(128) NOT NULL,"  + "\n" +
                        "ReleaseYear INT NOT NULL,"  + "\n" +
                        "Edition VARCHAR(128)"  + "\n" +
                        ");"
        ;
        Connect.executeUpdate(SQL);
        /* ------------------------------------------------------------------ */

        /* Genres */
        SQL =
                "CREATE TABLE Genres ("  + "\n" +
                        "GID INT PRIMARY KEY,"  + "\n" +
                        "GenreName VARCHAR(128) NOT NULL"  + "\n" +
                        ");"
        ;
        Connect.executeUpdate(SQL);
        /* ------------------------------------------------------------------ */

        /* Songs */
        SQL =
                "CREATE TABLE Songs ("  + "\n" +
                        "SongID INT PRIMARY KEY,"  + "\n" +
                        "Title VARCHAR(128) NOT NULL,"  + "\n" +
                        "Duration FLOAT NOT NULL,"  + "\n" +
                        "Playcount INT NOT NULL,"  + "\n" +
                        "ReleaseDate 	DATE     	NOT NULL,"  + "\n" +
                        "ReleaseCountry  VARCHAR(128) NOT NULL,"  + "\n" +
                        "Slanguage   	VARCHAR(128),"  + "\n" +
                        "RoyaltyRate 	FLOAT    	NOT NULL,"  + "\n" +
                        "RoyaltyPaid 	FLOAT    	NOT NULL,"  + "\n" +
                        "ArtistID    	INT      	NOT NULL,"  + "\n" +
                        "AlbumID     	INT      	NOT NULL,"  + "\n" +
                        "TrackNumber 	INT      	NOT NULL,"  + "\n" +
                        "FOREIGN KEY (ArtistID) REFERENCES Artists (ArtistID) ON UPDATE CASCADE ON DELETE CASCADE,"  + "\n" +
                        "FOREIGN KEY (AlbumID) REFERENCES Albums (AlbumID) ON UPDATE CASCADE ON DELETE CASCADE"  + "\n" +
                        ");"
        ;
        Connect.executeUpdate(SQL);
        /* ------------------------------------------------------------------ */

        /* Make */
        SQL =
                "CREATE TABLE Make ("  + "\n" +
                        "ArtistID  INT NOT NULL,"  + "\n" +
                        "AlbumID 	INT NOT NULL,"  + "\n" +
                        "PRIMARY KEY (ArtistID, AlbumID),"  + "\n" +
                        "FOREIGN KEY (ArtistID) REFERENCES Artists (ArtistID) ON UPDATE CASCADE ON DELETE CASCADE,"  + "\n" +
                        "FOREIGN KEY (AlbumID) REFERENCES Albums (AlbumID) ON UPDATE CASCADE ON DELETE CASCADE"  + "\n" +
                        ");"
        ;
        Connect.executeUpdate(SQL);
        /* ------------------------------------------------------------------ */

        /* Collaborate */
        SQL =
                "CREATE TABLE Collaborate ("  + "\n" +
                        "SongID    INT 	NOT NULL,"  + "\n" +
                        "ArtistID  INT 	NOT NULL,"  + "\n" +
                        "PRIMARY KEY (SongID, ArtistID),"  + "\n" +
                        "FOREIGN KEY (SongID) REFERENCES Songs (SongID) ON UPDATE CASCADE ON DELETE CASCADE,"  + "\n" +
                        "FOREIGN KEY (ArtistID) REFERENCES Artists (ArtistID) ON UPDATE CASCADE ON DELETE CASCADE"  + "\n" +
                        ");"
        ;
        Connect.executeUpdate(SQL);
        /* ------------------------------------------------------------------ */

        /* SongGenre */
        SQL =
                "CREATE TABLE SongGenre ("  + "\n" +
                        "SongID 	INT NOT NULL,"  + "\n" +
                        "GenreID 	INT NOT NULL,"  + "\n" +
                        "PRIMARY KEY (SongID, GenreID),"  + "\n" +
                        "FOREIGN KEY (SongID) REFERENCES Songs(SongID) ON UPDATE CASCADE ON DELETE CASCADE,"  + "\n" +
                        "FOREIGN KEY (GenreID) REFERENCES Genres(GID) ON UPDATE CASCADE ON DELETE CASCADE"  + "\n" +
                        ");"
        ;
        Connect.executeUpdate(SQL);
        /* ------------------------------------------------------------------ */

        /* PodcastHosts */
        SQL =
                "CREATE TABLE PodcastHosts ("  + "\n" +
                        "PHID INT PRIMARY KEY,"  + "\n" +
                        "PHFName VARCHAR(128) NOT NULL,"  + "\n" +
                        "PHLName VARCHAR(128) NOT NULL,"  + "\n" +
                        "PHEmail VARCHAR(128) NOT NULL,"  + "\n" +
                        "PHCity  VARCHAR(128)"  + "\n" +
                        ");"
        ;
        Connect.executeUpdate(SQL);
        /* ------------------------------------------------------------------ */

        /* Podcasts */
        SQL =
                "CREATE TABLE Podcasts ("  + "\n" +
                        "PID 		INT 		   PRIMARY KEY,"  + "\n" +
                        "PName 	VARCHAR(128) NOT NULL,"  + "\n" +
                        "PLanguage VARCHAR(128) NOT NULL,"  + "\n" +
                        "PCountry 	VARCHAR(128) NOT NULL,"  + "\n" +
                        "PRating 	DECIMAL(3, 2) NOT NULL,"  + "\n" +
                        "PSubscribers INT 	    NOT NULL,"  + "\n" +
                        "EpisodeCount INT        NOT NULL,"  + "\n" +
                        "PHID 	   INT 	    NOT NULL,"  + "\n" +
                        "FOREIGN KEY (PHID) REFERENCES PodcastHosts(PHID) ON UPDATE CASCADE ON DELETE CASCADE"  + "\n" +
                        ");"
        ;
        Connect.executeUpdate(SQL);
        /* ------------------------------------------------------------------ */

        /* PodcastEpisodes */
        SQL =
                "CREATE TABLE PodcastEpisodes ("  + "\n" +
                        "PEID 		INT 			NOT NULL,"  + "\n" +
                        "PID 		INT 			NOT NULL,"  + "\n" +
                        "PETitle 	VARCHAR(128) 	NOT NULL,"  + "\n" +
                        "PEDuration 	TIME 		NOT NULL,"  + "\n" +
                        "PEReleaseDate DATE 		NOT NULL,"  + "\n" +
                        "ListenerCount INT 		NOT NULL,"  + "\n" +
                        "AdCount 	 INT 		NOT NULL,"  + "\n" +
                        "PRIMARY KEY (PEID, PID),"  + "\n" +
                        "FOREIGN KEY (PID) REFERENCES Podcasts(PID) ON UPDATE CASCADE ON DELETE CASCADE"  + "\n" +
                        ");"
        ;
        Connect.executeUpdate(SQL);
        /* ------------------------------------------------------------------ */

        /* Sponsors */
        SQL =
                "CREATE TABLE Sponsors ("  + "\n" +
                        "SID 		INT 		   PRIMARY KEY,"  + "\n" +
                        "SName 		VARCHAR(128) NOT NULL"  + "\n" +
                        ");"
        ;
        Connect.executeUpdate(SQL);
        /* ------------------------------------------------------------------ */

        /* Guests */
        SQL =
                "CREATE TABLE Guests ("  + "\n" +
                        "GID 	INT 		   PRIMARY KEY,"  + "\n" +
                        "GName 	VARCHAR(128) NOT NULL"  + "\n" +
                        ");"
        ;
        Connect.executeUpdate(SQL);
        /* ------------------------------------------------------------------ */

        /* Fund */
        SQL =
                "CREATE TABLE Fund ("  + "\n" +
                        "PID INT NOT NULL,"  + "\n" +
                        "SID INT NOT NULL,"  + "\n" +
                        "PRIMARY KEY (PID, SID),"  + "\n" +
                        "FOREIGN KEY (PID) REFERENCES Podcasts(PID) ON DELETE CASCADE ON UPDATE CASCADE,"  + "\n" +
                        "FOREIGN KEY (SID) REFERENCES Sponsors(SID) ON DELETE CASCADE ON UPDATE CASCADE"  + "\n" +
                        ");"
        ;
        Connect.executeUpdate(SQL);
        /* ------------------------------------------------------------------ */

        /* AppearIn */
        SQL =
                "CREATE TABLE AppearIn ("  + "\n" +
                        "PEID 		INT NOT NULL,"  + "\n" +
                        "GID 		INT NOT NULL,"  + "\n" +
                        "PRIMARY KEY (PEID, GID),"  + "\n" +
                        "FOREIGN KEY (PEID) REFERENCES PodcastEpisodes(PEID) ON DELETE CASCADE ON UPDATE CASCADE,"  + "\n" +
                        "FOREIGN KEY (GID) REFERENCES Guests(GID) ON DELETE CASCADE ON UPDATE CASCADE"  + "\n" +
                        ");"
        ;
        Connect.executeUpdate(SQL);
        /* ------------------------------------------------------------------ */

        /* PodcastGenre */
        SQL =
                "CREATE TABLE PodcastGenre ("  + "\n" +
                        "PID 		INT NOT NULL,"  + "\n" +
                        "GenreID 	INT NOT NULL,"  + "\n" +
                        "PRIMARY KEY (PID, GenreID),"  + "\n" +
                        "FOREIGN KEY (PID) REFERENCES Podcasts(PID) ON UPDATE CASCADE ON DELETE CASCADE,"  + "\n" +
                        "FOREIGN KEY (GenreID) REFERENCES Genres(GID) ON UPDATE CASCADE ON DELETE CASCADE"  + "\n" +
                        ");"
        ;
        Connect.executeUpdate(SQL);
        /* ------------------------------------------------------------------ */

        /* ArtistPaymentRecords */
        SQL =
                "CREATE TABLE ArtistPaymentRecords ("  + "\n" +
                        "PayDate 	DATE 		NOT NULL,"  + "\n" +
                        "ArtistID 	INT  		NOT NULL,"  + "\n" +
                        "PayAmount 	DECIMAL(10,2) 	NOT NULL,"  + "\n" +
                        "PRIMARY KEY (PayDate, ArtistID),"  + "\n" +
                        "FOREIGN KEY (ArtistID) REFERENCES Artists(ArtistID) ON UPDATE CASCADE ON DELETE CASCADE"  + "\n" +
                        ");"
        ;
        Connect.executeUpdate(SQL);
        /* ------------------------------------------------------------------ */

        /* LabelPaymentRecords */
        SQL =
                "CREATE TABLE LabelPaymentRecords ("  + "\n" +
                        "PayDate 	DATE 		NOT NULL,"  + "\n" +
                        "LabelID 	INT 			NOT NULL,"  + "\n" +
                        "PayAmount 	DECIMAL(10,2) 	NOT NULL,"  + "\n" +
                        "PRIMARY KEY (PayDate, LabelID),"  + "\n" +
                        "FOREIGN KEY (LabelID) REFERENCES RecordLabels(RLID) ON UPDATE CASCADE ON DELETE CASCADE"  + "\n" +
                        ");"
        ;
        Connect.executeUpdate(SQL);
        /* ------------------------------------------------------------------ */

        /* HostPaymentRecords */
        SQL =
                "CREATE TABLE HostPaymentRecords ("  + "\n" +
                        "PayDate 	DATE 		NOT NULL,"  + "\n" +
                        "HostID 		INT 			NOT NULL,"  + "\n" +
                        "PayAmount 	DECIMAL(10,2) 	NOT NULL,"  + "\n" +
                        "PRIMARY KEY (PayDate, HostID),"  + "\n" +
                        "FOREIGN KEY (HostID) REFERENCES PodcastHosts(PHID) ON UPDATE CASCADE ON DELETE CASCADE"  + "\n" +
                        ");"
        ;
        Connect.executeUpdate(SQL);
        /* ------------------------------------------------------------------ */

        /* RevenueRecords */
        SQL =
                "CREATE TABLE RevenueRecords ("  + "\n" +
                        "RevDate 	DATE 		PRIMARY KEY,"  + "\n" +
                        "RevAmount DECIMAL(10,2) 	NOT NULL"  + "\n" +
                        ");"
        ;
        Connect.executeUpdate(SQL);
        /* ------------------------------------------------------------------ */

        /* SongRecords */
        SQL =
                "CREATE TABLE SongRecords ("  + "\n" +
                        "SRDate 	DATE 	NOT NULL,"  + "\n" +
                        "SongID 	INT 		NOT NULL,"  + "\n" +
                        "SRPlaycount INT 	NOT NULL,"  + "\n" +
                        "PRIMARY KEY (SRDate, SongID),"  + "\n" +
                        "FOREIGN KEY (SongID) REFERENCES Songs(SongID) ON UPDATE CASCADE ON DELETE CASCADE"  + "\n" +
                        ");"
        ;
        Connect.executeUpdate(SQL);
        /* ------------------------------------------------------------------ */

        /* PodcastRecords */
        SQL =
                "CREATE TABLE PodcastRecords ("  + "\n" +
                        "PRDate 			DATE 	NOT NULL,"  + "\n" +
                        "PID 			INT 		NOT NULL,"  + "\n" +
                        "PRSubscribers 	INT 		NOT NULL,"  + "\n" +
                        "PRRating 		FLOAT 	NOT NULL,"  + "\n" +
                        "PRIMARY KEY (PRDate, PID),"  + "\n" +
                        "FOREIGN KEY (PID) REFERENCES Podcasts(PID) ON UPDATE CASCADE ON DELETE CASCADE"  + "\n" +
                        ");"
        ;
        Connect.executeUpdate(SQL);
        /* ------------------------------------------------------------------ */
    }


    /* ################################################################################################################################################################# */
    /* ********************************************************************** */
    /* ************************** Table Population ************************** */
    /* ********************************************************************** */
    /* ################################################################################################################################################################# */


    /**
     * Function that runs a sequence of SQL statements which populate our newly
     * created databse schemas with demo data. Note that it is set up to leave
     * the database in a consistent state (else it would fail).
     */
    private static void populateTables() {

        String SQL;

        /* Publication */
        /* ------------------------------------------------------------------ */
        SQL =
                "INSERT INTO Publication VALUES "  + "\n" + "\t" +
                        "(1, 'Book', '2020-05-23', 'Sports'),"  + "\n" + "\t" +
                        "(2, 'Periodical', '2021-01-01', 'Science'),"  + "\n" + "\t" +
                        "(3, 'Periodical', '2018-11-01', 'Entertainment'),"  + "\n" + "\t" +
                        "(4, 'Book', '2020-08-28', 'Science Fiction'),"  + "\n" + "\t" +
                        "(5, 'Book', '2016-09-09', 'Biography'),"  + "\n" + "\t" +
                        "(6, 'Periodical', '2022-01-08', 'Sports'),"  + "\n" + "\t" +
                        "(7, 'Periodical', '2021-10-01', 'Music'),"  + "\n" + "\t" +
                        "(8, 'Periodical', '2021-11-01', 'Sports'),"  + "\n" + "\t" +
                        "(9, 'Periodical', '2021-12-15', 'History'),"  + "\n" + "\t" +
                        "(10, 'Book', '2021-04-02', 'Self Help'),"  + "\n" + "\t" +
                        "(1001, 'Book', '2018-10-10', 'Technology'),"  + "\n" + "\t" +
                        "(1002, 'Periodical', '2020-02-24', 'Health'),"  + "\n" + "\t" +
                        "(1003, 'Periodical', '2020-03-01', 'Science')"  + "\n" +
                        ";" + "\n" + "\n"
        ;
        // System.out.println(SQL);
        Connect.executeUpdate(SQL);
        /* ------------------------------------------------------------------ */

        /* Book */
        /* ------------------------------------------------------------------ */
        SQL =
                "INSERT INTO Book VALUES " + "\n" + "\t" +
                        "(4, '0000000001', 'Harry Potter', 1, '1998-03-02')," + "\n" + "\t" +
                        "(1, '0000000002', 'What Made Maddy Run', 1, '2000-12-10'),"  + "\n" + "\t" +
                        "(5, '0000000003', 'Churchill: A life', 2, '2012-08-05'),"  + "\n" + "\t" +
                        "(10, '0000000004', 'Atomic Habits', 1, '2006-06-09'),"  + "\n" + "\t" +
                        "(1001, '0000012345', 'Introduction to Database', 2, '2017-03-17')"  + "\n" +
                        ";" + "\n" + "\n"
        ;
        // System.out.println(SQL);
        Connect.executeUpdate(SQL);
        /* ------------------------------------------------------------------ */


        /* Periodicity */
        /* ------------------------------------------------------------------ */
        SQL =
                "INSERT INTO Periodicity VALUES "  + "\n" + "\t" +
                        "('Journal', 'Monthly')," + "\n" + "\t" +
                        "('Magazine', 'Weekly')"  + "\n" +
                        ";" + "\n" + "\n"
        ;
        // System.out.println(SQL);
        Connect.executeUpdate(SQL);
        /* ------------------------------------------------------------------ */

        /* Periodical */
        /* ------------------------------------------------------------------ */
        SQL =
                "INSERT INTO Periodical VALUES "  + "\n" + "\t" +
                        "(2,  'Magazine', 'Discover', 1, '2020-12-01')," + "\n" + "\t" +
                        "(3,  'Journal', 'Variety', 1, '2018-11-07')," + "\n" + "\t" +
                        "(6,  'Journal', 'ESPN', 1, '2022-01-14')," + "\n" + "\t" +
                        "(7,  'Magazine', 'Revolver', 2, '2021-09-01')," + "\n" + "\t" +
                        "(8,  'Magazine', 'Hoop', 1, '2021-10-01')," + "\n" + "\t" +
                        "(9,  'Journal', 'Ancient', 3, '2021-12-21'),"  + "\n" + "\t" +
                        "(1002,  'Magazine', 'Healthy Diet', 1, '2020-02-24'),"  + "\n" + "\t" +
                        "(1003,  'Journal', 'Animal Science', 5, '2020-03-01')"  + "\n" +
                        ";" + "\n" + "\n"
        ;
        // System.out.println(SQL);
        Connect.executeUpdate(SQL);
        /* ------------------------------------------------------------------ */


        /* Chapter */
        /* ------------------------------------------------------------------ */
        SQL =
                "INSERT INTO Chapter VALUES "  + "\n" + "\t" +
                        "(4,  1, 'The Boy Who Lived','Lorem Ipsum is simply dummy text...')," + "\n" + "\t" +
                        "(4,  2, 'The Vanishing Glass','It was popularised in the 1960s...')," + "\n" + "\t" +
                        "(4,  3, 'The Letters From No One','Lorem Ipsum is simply dummy text of the printing...')," + "\n" + "\t" +
                        "(4,  4, 'The Keeper Of Keys', 'It was popularised in the 1960s with the release of Letraset...')," + "\n" + "\t" +
                        "(1,  1, 'Vacuum', 'Lorem Ipsum is simply dummy text of the...')," + "\n" + "\t" +
                        "(1,  2, 'Run Maddy Run', 'It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum...')," + "\n" + "\t" +
                        "(5,  1, 'England', 'Lorem Ipsum is simply dummy text of...')," + "\n" + "\t" +
                        "(10,  1, 'Focus', 'It was popularised in the 1960s with the release of...'),"  + "\n" + "\t" +
                        "(1001,  1, 'Why Data?', 'Since the dawn of time, man has strived to store his data, whether it be on cave walls, papyrus sheets, or bits on a magnetic strip...'),"  + "\n" + "\t" +
                        "(1001,  2, 'Relational Schemas', 'Tables, tables, tables.... Did I mention tables? That is how we store data in a relational DB...'),"  + "\n" + "\t" +
                        "(1001,  3, 'SQL Basics', 'SQL is the language of databases. Though limited in its functionality, these limitations allow for efficiency during execution...'),"  + "\n" + "\t" +
                        "(1001,  4, 'Transactions', 'Transactions allows mutliple queries and updates to run simultaneously on a DB while ensuring that the data always stays in a consistent state...')"  + "\n" +
                        ";" + "\n" + "\n"
        ;
        // System.out.println(SQL);
        Connect.executeUpdate(SQL);
        /* ------------------------------------------------------------------ */


        /* Article */
        /* ------------------------------------------------------------------ */
        SQL =
                "INSERT INTO Article VALUES "  + "\n" + "\t" +
                        "(2,  1, 'Nuclear Bomb', '2020-11-11', 'Lorem Ipsum is simply dummy text...')," + "\n" + "\t" +
                        "(2,  2, 'Magnetism', '2020-11-15', 'It was popularised in the 1960s with the release of Letraset...')," + "\n" + "\t" +
                        "(3,  1, 'Gossip Column', '2018-10-17', 'Lorem Ipsum is simply dummy text of the printing...')," + "\n" + "\t" +
                        "(6,  1, 'Manchester Derby', '2021-12-28', 'It was popularised in the 1960s with the release of Letraset sheets...')," + "\n" + "\t" +
                        "(7,  1, 'Top 10 Country Songs', '2021-08-24', 'Lorem Ipsum is simply dummy tex...')," + "\n" + "\t" +
                        "(8,  1, 'The Last Dance', '2021-09-25', 'It was popularised in the 1960s...')," + "\n" + "\t" +
                        "(9,  1, 'Pyramids Of Giza', '2021-12-12', 'Lorem Ipsum is simply dummy text of the printing...')," + "\n" + "\t" +
                        "(9,  2, 'The 8th Wonder', '2021-12-19', 'It was popularised in the 1960s with the release of...'),"  + "\n" + "\t" +
                        "(1002,  1, 'Eat More Chicken', '2020-02-24', 'ABC'),"  + "\n" + "\t" +
                        "(1003,  1, 'Why Cats are Evil', '2020-03-01', 'AAA')"  + "\n" +
                        ";" + "\n" + "\n"
        ;
        // System.out.println(SQL);
        Connect.executeUpdate(SQL);
        /* ------------------------------------------------------------------ */


        /* Contributor */
        /* ------------------------------------------------------------------ */
        SQL =
                "INSERT INTO Contributor VALUES "  + "\n" + "\t" +
                        "(1,  'J.K. Rowling', 'Author', 'Staff', 56, 'F', '9477488475', 'imawizard@potter.com', '1200 Hogwarts Street, ENG')," + "\n" + "\t" +
                        "(2,  'Kate Fagan', 'Author', 'Staff', 32, 'F', '8563156942', 'katefagan@gmail.com', '101 Main Street, NC 27513')," + "\n" + "\t" +
                        "(3,  'Martin Gilbert', 'Author', 'Invited', 45, 'M', '3685149657', 'martingilbert@gmail.com', '345 Silver Church Road, NC 27845')," + "\n" + "\t" +
                        "(4,  'James Clear', 'Author', 'Invited', 74, 'M', '4865123975', 'jameclear@gmail.com', '2306 Raleigh Road, NC 27530')," + "\n" + "\t" +
                        "(5,  'Bob Woodward', 'Author', 'Staff', 21, 'M', '9863254178', 'bossboby@gmail.com', '310 Carytown Road, NC 27513')," + "\n" + "\t" +
                        "(6,  'Barbara Walter', 'Author', 'Staff', 26, 'F', '6668453248', 'barbarawalter@yahoo.com', '598 Old Apex Road, NC 27516')," + "\n" + "\t" +
                        "(7,  'Anna Wintour', 'Editor', 'Staff', 28, 'F', '3877749865', 'annawintour@yahoo.com', '234 Wake Avenue, NC 28645')," + "\n" + "\t" +
                        "(8,  'Dasha Gold', 'Editor', 'Invited', 30, 'F', '5488675309', '24kgold@hotmail.com', '1602 Broadway, NY 15036')," + "\n" + "\t" +
                        "(9,  'Emmanuelle', 'Editor', 'Staff', 54, 'M', '5555555555', 'emmanuelle@hotmail.com', '9849 Higher Living Circle, Apt. 235, NC 27348')," + "\n" + "\t" +
                        "(10,  'Carine Roitfeld', 'Editor', 'Invited', 38, 'F', '9865321148', 'carineroitfeld@ncsu.edu', '666 Big Cow Road, TX 69420'),"  + "\n" + "\t" +
                        "(3001,  'John', 'Editor', 'Staff', 36, 'M', '9391234567', '3001@gmail.com', '21 ABC St, NC 27'),"  + "\n" + "\t" +
                        "(3002,  'Ethen', 'Editor', 'Staff', 30, 'M', '9491234567', '3002@gmail.com', '21 ABC St, NC 27606'),"  + "\n" + "\t" +
                        "(3003,  'Cathy', 'Author', 'Invited', 28, 'F', '9591234567', '3003@gmail.com', '3300 AAA St, NC 27606')"  + "\n" +
                        ";" + "\n" + "\n"
        ;
        // System.out.println(SQL);
        Connect.executeUpdate(SQL);
        /* ------------------------------------------------------------------ */


        /* Edits */
        /* ------------------------------------------------------------------ */
        SQL =
                "INSERT INTO Edits VALUES "  + "\n" + "\t" +
                        "(1, 7)," + "\n" + "\t" +
                        "(2, 8)," + "\n" + "\t" +
                        "(3, 9)," + "\n" + "\t" +
                        "(4, 10),"  + "\n" + "\t" +
                        "(1001, 3001),"  + "\n" + "\t" +
                        "(1002, 3002)"  + "\n" +
                        ";" + "\n" + "\n"
        ;
        // System.out.println(SQL);
        Connect.executeUpdate(SQL);

        /* ------------------------------------------------------------------ */

        /* AuthorsArticle */
        /* ------------------------------------------------------------------ */
        SQL =
                "INSERT INTO AuthorsArticle VALUES "  + "\n" + "\t" +
                        "(2, 1, 4)," + "\n" + "\t" +
                        "(2, 2, 4)," + "\n" + "\t" +
                        "(3, 1, 2)," + "\n" + "\t" +
                        "(6, 1, 3)," + "\n" + "\t" +
                        "(7, 1, 1)," + "\n" + "\t" +
                        "(8, 1, 5)," + "\n" + "\t" +
                        "(9, 1, 6)," + "\n" + "\t" +
                        "(9, 2, 6),"  + "\n" + "\t" +
                        "(1002, 1, 3003),"  + "\n" + "\t" +
                        "(1003, 1, 3003)"  + "\n" +
                        ";" + "\n" + "\n"
        ;
        // System.out.println(SQL);
        Connect.executeUpdate(SQL);

        /* ------------------------------------------------------------------ */

        /* AuthorsBook */
        /* ------------------------------------------------------------------ */
        SQL =
                "INSERT INTO AuthorsBook VALUES "  + "\n" + "\t" +
                        "(1, 2)," + "\n" + "\t" +
                        "(4, 1)," + "\n" + "\t" +
                        "(5, 3)," + "\n" + "\t" +
                        "(10, 4),"  + "\n" + "\t" +
                        "(1001, 3003)"  + "\n" +
                        ";" + "\n" + "\n"
        ;
        // System.out.println(SQL);
        Connect.executeUpdate(SQL);

        /* ------------------------------------------------------------------ */

        /* Address */
        /* ------------------------------------------------------------------ */
        SQL =
                "INSERT INTO Address VALUES "  + "\n" + "\t" +
                        "('1615 S Wilmington St', 'Raleigh')," + "\n" + "\t" +
                        "('230 East Cameron Ave', 'Chapel Hill'),"  + "\n" + "\t" +
                        "( '3000 Cowboy Street', 'Greensboro')," + "\n" + "\t" +
                        "('250 West Main Street', 'Apex')," + "\n" + "\t" +
                        "('2200, A Street, NC', 'Charlotte')," + "\n" + "\t" +
                        "('2200, B Street, NC', 'Raleigh')" + "\n" + "\t" +
                        ";" + "\n" + "\n"
        ;
        // System.out.println(SQL);
        Connect.executeUpdate(SQL);
        /* ------------------------------------------------------------------ */

        /* Distributor */
        /* ------------------------------------------------------------------ */
        SQL =
                "INSERT INTO Distributor VALUES "  + "\n" + "\t" +
                        "(1, 'Whole Order', 243.56, '5647891452', '1615 S Wilmington St', 'Mark Austin', 'Wholesale Distributor')," + "\n" + "\t" +
                        "(2, 'Davis Library', 2542.43, '7645801443', '230 East Cameron Ave', 'Christina Higgins', 'Library'),"  + "\n" + "\t" +
                        "(3, 'Bobs Used Books', 126.98, '2537891452', '3000 Cowboy Street', 'Bob Bobertson', 'Book Store')," + "\n" + "\t" +
                        "(4, 'Barnes & Noble of Apex', 48987.05, '7645864443', '250 West Main Street', 'Jerry Seinfeld', 'Book Store')," + "\n" + "\t" +
                        "(2001, 'BookSell', 215.00, '9191234567', '2200, A Street, NC', 'Jason', 'Book Store')," + "\n" + "\t" +
                        "(2002, 'BookDist', 0.00, '9291234568', '2200, B Street, NC', 'Alex', 'Wholesale Distributor')" + "\n" +
                        ";" + "\n" + "\n"
        ;
        // System.out.println(SQL);
        Connect.executeUpdate(SQL);
        /* ------------------------------------------------------------------ */

        /* Orders */
        /* ------------------------------------------------------------------ */
        SQL =
                "INSERT INTO Orders VALUES "  + "\n" + "\t" +
                        "(1, 1, 1, 30, 15.95, '2022-03-21', 20.35, 'Order Received')," + "\n" + "\t" +
                        "(2, 2, 3, 20, 10.99, '2022-03-09', 15.99, 'Delivered')," + "\n" + "\t" +
                        "(3, 2, 5, 15, 20.98, '2022-03-15', 12.47, 'Shipped')," + "\n" + "\t" +
                        "(4, 1, 7, 25, 30.49, '2022-03-29', 18.89, 'Order Received'),"  + "\n" + "\t" +
                        "(5, 3, 1, 15, 15.95, '2022-03-15', 12.47, 'Delivered')," + "\n" + "\t" +
                        "(6, 4, 5, 10, 20.98, '2022-03-29', 11.99, 'Delivered')," + "\n" + "\t" +
                        "(7, 3, 3, 15, 21.99, '2022-03-15', 8.47, 'Order Received')," + "\n" + "\t" +
                        "(8, 4, 5, 5, 22.98, '2022-03-29', 5.95,'Shipped')," + "\n" + "\t" +
                        "(4001, 2001, 1001, 30, 20.00, '2020-01-02', 30.00, 'Delivered')," + "\n" + "\t" +
                        "(4002, 2001, 1001, 10, 20.00, '2020-02-05', 15.00, 'Delivered')," + "\n" + "\t" +
                        "(4003, 2002, 1003, 10, 10.00, '2020-02-10', 15.00, 'Delivered')" + "\n" +
                        ";" + "\n" + "\n"
        ;
        // System.out.println(SQL);
        Connect.executeUpdate(SQL);
        /* ------------------------------------------------------------------ */


        /* Transaction */
        /* ------------------------------------------------------------------ */
        SQL =
                "INSERT INTO Transaction VALUES "  + "\n" + "\t" +
                        "(1, 450.00, '2020-03-21')," + "\n" + "\t" +
                        "(2, 200.00, '2020-03-09')," + "\n" + "\t" +
                        "(3, 2500.00, '2022-01-01')," + "\n" + "\t" +
                        "(4, 3000.00, '2022-01-01'),"  + "\n" +"\t" +
                        "(5, 600.00, '2022-03-09'),"  + "\n" + "\t" +
                        "(6, 3200.00, '2022-03-03'),"  + "\n" +"\t" +
                        "(7, 300.00, '2022-03-05'),"  + "\n" +"\t" +
                        "(8, 1300.00, '2022-03-06'),"  + "\n" +"\t" +
                        "(9, 300.00, '2022-03-10'),"  + "\n" + "\t" +
                        "(5001, 630.00, '2020-01-02'),"  + "\n" + "\t" +
                        "(5002, 215.00, '2020-02-05'),"  + "\n" + "\t" +
                        "(5003, 115.00, '2020-02-10'),"  + "\n" + "\t" +
                        "(5004, 1000.00, '2020-04-01'),"  + "\n" + "\t" +
                        "(5005, 1000.00, '2020-04-01'),"  + "\n" + "\t" +
                        "(5006, 1200.00, '2020-04-01')"  + "\n" +
                        ";" + "\n" + "\n"
        ;
        // System.out.println(SQL);
        Connect.executeUpdate(SQL);

        /* ------------------------------------------------------------------ */

        /* Bills */
        /* ------------------------------------------------------------------ */
        SQL =
                "INSERT INTO Bills VALUES "  + "\n" + "\t" +
                        "(1, 1, True, '2020-03-28')," + "\n" + "\t" +
                        "(2, 1, True,  '2020-03-16'),"  + "\n" +"\t" +
                        "(6, 4, True,  '2022-03-05')," + "\n" + "\t" +
                        "(7, 3, False, NULL)," + "\n" + "\t" +
                        "(8, 4, True,  '2022-03-10')," + "\n" + "\t" +
                        "(5001, 2001, True,  '2020-01-15')," + "\n" + "\t" +
                        "(5002, 2001, False,  NULL)," + "\n" + "\t" +
                        "(5003, 2002, True,  '2020-02-25')" + "\n" +
                        ";" + "\n" + "\n"
        ;
        // System.out.println(SQL);
        Connect.executeUpdate(SQL);
        /* ------------------------------------------------------------------ */

        /* Wages */
        /* ------------------------------------------------------------------ */
        SQL =
                "INSERT INTO Wages VALUES "  + "\n" + "\t" +
                        "(3, 1,  'Book Authorship', '2022-01-03')," + "\n" + "\t" +
                        "(4, 7, 'Editorial Work', '2022-01-05'),"  + "\n" + "\t" +
                        "(5, 1, 'Article Authorship', '2022-01-05'),"  + "\n" + "\t" +
                        "(9, 1, 'Article Authorship', NULL),"  + "\n" + "\t" +
                        "(5004, 3001, 'Editorial Work', '2020-04-02'),"  + "\n" + "\t" +
                        "(5005, 3002, 'Editorial Work', '2020-04-02'),"  + "\n" + "\t" +
                        "(5006, 3003, 'Article Authorship', '2020-04-02')"  + "\n" +
                        ";" + "\n" + "\n"
        ;
        // System.out.println(SQL);
        Connect.executeUpdate(SQL);
        /* ------------------------------------------------------------------ */

    }
}
