package config;

/**
 * Initialize our DB by dropping the existing tables and creating them again.
 *
 * The database is constructed using the following 3 steps in order:
 *	1) Drop all existing tables
 *	2) Create tables using schema definitions (with types, constrains, keys, etc.)
 *	3) Insert example data into tables (including DEMO data)
 */


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
        populateTables();
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
                        "UMonthlyFee INT NOT NULL"  + "\n" +
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
                        "RoyaltyPaid 	VARCHAR(3)    	NOT NULL,"  + "\n" +
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
                        "PRating 	DECIMAL(2, 1) NOT NULL,"  + "\n" +
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

        /* Users */
        /* ------------------------------------------------------------------ */
        SQL =
                "INSERT INTO Users VALUES " +
                        "(8001, 'Alex@example.com', 'Alex', 'A', '111-111-1111', '2000-03-16', 'active', 10)," +
                        "(8002, 'John@example.com', 'John', 'J', '222-222-2222', '2001-05-19', 'active', 10)" +
                        ";"
        ;
        Connect.executeUpdate(SQL);
        /* ------------------------------------------------------------------ */

        /* RecordLabels */
        /* ------------------------------------------------------------------ */
        SQL =
                "INSERT INTO RecordLabels VALUES " +
                        "(3001, 'Elevate Records')," +
                        "(3002, 'Melodic Avenue Music')" +
                        ";"
        ;
        Connect.executeUpdate(SQL);
        /* ------------------------------------------------------------------ */

        /* Artists */
        /* ------------------------------------------------------------------ */
        SQL =
                "INSERT INTO Artists VALUES " +
                        "(2001, 'Forest', 'active', 'musician', 'America', 25, 'POP', 3001)," +
                        "(2002, 'Rain', 'active', 'musician', 'America', 55, 'POP', 3002)" +
                        ";"
        ;
        Connect.executeUpdate(SQL);
        /* ------------------------------------------------------------------ */

        /* Albums */
        /* ------------------------------------------------------------------ */
        SQL =
                "INSERT INTO Albums VALUES " +
                        "(4001, 'Electric Oasis', 2005, '1st')," +
                        "(4002, 'Lost in the Echoes', 2006, '1st')" +
                        ";"
        ;
        Connect.executeUpdate(SQL);
        /* ------------------------------------------------------------------ */

        /* Genres */
        /* ------------------------------------------------------------------ */
        SQL =
                "INSERT INTO Genres VALUES " +
                        "(300, 'Rock')," +
                        "(301, 'POP')" +
                        ";"
        ;
        Connect.executeUpdate(SQL);
        /* ------------------------------------------------------------------ */

        /* Songs */
        /* ------------------------------------------------------------------ */
        SQL =
                "INSERT INTO Songs VALUES " +
                        "(1001, 'Electric Dreamscape', 2.53, 500, '2007-01-01', 'America', 'English', 0.1, 'no', 2001, 4001, 2)," +
                        "(1002, 'Midnight Mirage', 3.02, 1000, '2008-01-01', 'America', 'English', 0.1, 'no', 2001, 4001, 2)," +
                        "(1003, 'Echoes of You', 2.32, 100, '2009-01-01', 'America', 'English', 0.1, 'no', 2002, 4002, 2)," +
                        "(1004, 'Lost in the Echoes', 3.32, 200, '2010-01-01', 'America', 'English', 0.1, 'no', 2002, 4002, 2)" +
                        ";"
        ;
        Connect.executeUpdate(SQL);
        /* ------------------------------------------------------------------ */

        /* Make */
        /* ------------------------------------------------------------------ */
        SQL =
                "INSERT INTO Make VALUES " +
                        "(2001, 4001)," +
                        "(2002, 4002)" +
                        ";"
        ;
        Connect.executeUpdate(SQL);
        /* ------------------------------------------------------------------ */

        /* Collaborate */
        /* ------------------------------------------------------------------ */
        SQL =
                "INSERT INTO Collaborate VALUES " +
                        "(1002, 2002)" +
                        ";"
        ;
        Connect.executeUpdate(SQL);
        /* ------------------------------------------------------------------ */

        /* SongGenre */
        /* ------------------------------------------------------------------ */
        SQL =
                "INSERT INTO SongGenre VALUES " +
                        "(1001, 300)," +
                        "(1002, 301)," +
                        "(1003, 300)," +
                        "(1004, 301)" +
                        ";"
        ;
        Connect.executeUpdate(SQL);
        /* ------------------------------------------------------------------ */

        /* PodcastHosts */
        /* ------------------------------------------------------------------ */
        SQL =
                "INSERT INTO PodcastHosts VALUES " +
                        "(6001, 'Matthew', 'Wilson', 'Matthew@example.com', 'Raleigh')" +
                        ";"
        ;
        Connect.executeUpdate(SQL);
        /* ------------------------------------------------------------------ */

        /* Podcasts */
        /* ------------------------------------------------------------------ */
        SQL =
                "INSERT INTO Podcasts VALUES " +
                        "(5001, 'Mind Over Matter: Exploring the Power of the Human Mind', 'English', 'America', 4.5, 10, 5, 6001)" +
                        ";"
        ;
        Connect.executeUpdate(SQL);
        /* ------------------------------------------------------------------ */

        /* PodcastEpisodes */
        /* ------------------------------------------------------------------ */
        SQL =
                "INSERT INTO PodcastEpisodes VALUES " +
                        "(7001, 5001, 'The Science of Mindfulness', '00:30:12', '2023-03-01', 100, 0)," +
                        "(7002, 5001, 'Unlocking Your Potential', '00:15:32', '2023-03-25', 200, 0)" +
                        ";"
        ;
        Connect.executeUpdate(SQL);
        /* ------------------------------------------------------------------ */

        /* ArtistPaymentRecords */
        /* ------------------------------------------------------------------ */
        SQL =
                "INSERT INTO ArtistPaymentRecords VALUES " +
                        "('2023-01-01', 2001, 4.2)," +
                        "('2023-02-01', 2001, 8.4)," +
                        "('2023-03-01', 2001, 12.6)," +
                        "('2023-01-01', 2002, 703.5)," +
                        "('2023-02-01', 2002, 1547)," +
                        "('2023-03-01', 2002, 2320.5)" +
                        ";"
        ;
        Connect.executeUpdate(SQL);
        /* ------------------------------------------------------------------ */

        /* LabelPaymentRecords */
        /* ------------------------------------------------------------------ */
        SQL =
                "INSERT INTO LabelPaymentRecords VALUES " +
                        "('2023-01-01', 3001, 3.3)," +
                        "('2023-02-01', 3001, 6.6)," +
                        "('2023-03-01', 3001, 9.9)," +
                        "('2023-01-01', 3002, 330)," +
                        "('2023-02-01', 3002, 660)," +
                        "('2023-03-01', 3002, 990)" +
                        ";"
        ;
        Connect.executeUpdate(SQL);
        /* ------------------------------------------------------------------ */

        /* HostPaymentRecords */
        /* ------------------------------------------------------------------ */
        SQL =
                "INSERT INTO HostPaymentRecords VALUES " +
                        "('2023-01-01', 6001, 20)," +
                        "('2023-02-01', 6001, 30)," +
                        "('2023-03-01', 6001, 40)" +
                        ";"
        ;
        Connect.executeUpdate(SQL);
        /* ------------------------------------------------------------------ */

        /* RevenueRecords */
        /* ------------------------------------------------------------------ */
        SQL =
                "INSERT INTO RevenueRecords VALUES " +
                        "('2023-01-01', 1111)," +
                        "('2023-02-01', 2222)," +
                        "('2023-03-01', 3333)," +
                        "('2023-04-01', 123000)" +
                        ";"
        ;
        Connect.executeUpdate(SQL);
        /* ------------------------------------------------------------------ */

        /* SongRecords */
        /* ------------------------------------------------------------------ */
        SQL =
                "INSERT INTO SongRecords VALUES " +
                        "('2023-01-01', 1001, 10)," +
                        "('2023-02-01', 1001, 20)," +
                        "('2023-03-01', 1001, 30)," +
                        "('2023-01-01', 1002, 100)," +
                        "('2023-02-01', 1002, 200)," +
                        "('2023-03-01', 1002, 300)," +
                        "('2023-01-01', 1003, 1000)," +
                        "('2023-02-01', 1003, 2000)," +
                        "('2023-03-01', 1003, 3000)," +
                        "('2023-01-01', 1004, 10000)," +
                        "('2023-02-01', 1004, 20000)," +
                        "('2023-03-01', 1004, 30000)" +
                        ";"
        ;
        Connect.executeUpdate(SQL);
        /* ------------------------------------------------------------------ */
    }
}
