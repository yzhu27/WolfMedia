package process;

import java.util.Scanner;
import java.sql.*;

import util.*;


public class updateArtistInfo {

//    public static String execute(String sql) {
//        return queryExecuter.execute(sql);
//    }



    public static String run(Scanner reader) throws SQLException{
        System.out.println("+------------------------------------+");
        System.out.println("|         Artist Details             |");
        System.out.println("+------------------------------------+");
        System.out.println("");

        DBTablePrinter.printTable("Artists");

        System.out.println("+------------------------------------+");
        System.out.println("| Please Submit the Following Inputs |");
        System.out.println("+------------------------------------+");
        System.out.println("");

        System.out.println("Artist ID: ");
        int artistID = reader.nextInt();
        reader.nextLine();

        System.out.println("Select attribute to update:" + "\n" +
                "1. Artist name" + "\n" +
                "2. Artist status" + "\n" +
                "3. Artist type (band/musician/composer)" + "\n" +
                "4. Artist country" + "\n" +
                "5. Artist primary genre" + "\n" +
                "6. MonthlyListeners"
        );
        int choice = reader.nextInt();
        reader.nextLine();

        String attribute;
        if (choice == 1){
            attribute = "Name";
        }else if(choice == 2){
            attribute = "Status";
        }else if(choice == 3){
            attribute = "Type";
        }else if(choice == 4){
            attribute = "ArtCountry";
        }else if(choice == 5){
            attribute = "PrimaryGenre";
        }else if(choice == 6) {
            attribute = "MonthlyListeners";
        }
        else {
            return "Error: Invalid Input";
        }

        String newValue;

        if(choice == 3){
            System.out.println("Select type of Artist:" + "\n" +
                    "1. Band" + "\n" +
                    "2. Musician" + "\n" +
                    "3. Composer"
            );
            int type = reader.nextInt();
            if (type == 1){
                newValue = "Band";
            }else if(type == 2){
                newValue = "Musician";
            }else if(type == 3){
                newValue = "Composer";
            }else {
                return "Error: Invalid Input";
            }
        } else {
            System.out.println("New Value: ");
            newValue = reader.nextLine();

        }

        return execute(artistID, attribute, newValue);

    }

    public static String execute(int ID, String attribute, String newValue) {

        if (attribute.equals("MonthlyListeners")){
            int tmp = Integer.parseInt(newValue);
            String sql =
                    "UPDATE Artists " +
                            "SET %s = %d "  +
                            "WHERE ArtistID = %d " +
                            ";"
                    ;

            sql = String.format(sql, attribute, tmp, ID);
            return queryExecuter.execute(sql);
        }
        String sql =
                "UPDATE Artists " +
                        "SET %s = '%s' "  +
                        "WHERE ArtistID = %d " +
                        ";"
                ;

        sql = String.format(sql, attribute, newValue, ID);
        return queryExecuter.execute(sql);
    }
}
