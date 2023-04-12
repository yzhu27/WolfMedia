package util;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

public class DBTablePrinter {
    
    /**
     * Function used to neatly print the data in a ResultSet returned from the
     * database. This is used in the "executeQuery" method above.
     *
     * To accomplish this, the function scans through the columns and records
     * of the ResultSet to determine the maximum length required for each field.
     * It then uses these values to neatly print the field names and each record
     * neatly in a table like fashion that is output directly to the console.
     */
    public static void printResultSet(ResultSet rs) throws  SQLException {
        String output = "\n";
        ResultSetMetaData metadata = rs.getMetaData();
        int colNum = metadata.getColumnCount();

        int[] colSizes = new int[colNum];
        for (int i=0; i<colNum; i++) {
            colSizes[i] = metadata.getColumnName(i+1).length();
        }
        int totalWidth = 0;
        while (rs.next()) {
            for (int i=0; i<colNum; i++) {
                int attrLen = 0;
                if(rs.getString(i+1)!=null) {
                    attrLen = rs.getString(i+1).length();
                }
                if (attrLen > colSizes[i]) {
                    colSizes[i] = attrLen;
                }
                
            }
        }
        for (int i=0; i<colNum; i++) {
            totalWidth += colSizes[i];
        }

        rs.beforeFirst();

        output += ("+"+"-".repeat(totalWidth+3*colNum-1)+"+\n");

        for(int i=0; i<colNum; i++) {
            output += String.format(String.format("| %%%ds ", colSizes[i]), metadata.getColumnName(i+1));
        }
        output += "|\n";

        output += ("|"+"-".repeat(totalWidth+3*colNum-1)+"|\n");

        while (rs.next()) {
            for(int i=0; i<colNum; i++) {
                output += String.format(String.format("| %%%ds ", colSizes[i]), rs.getString(i+1));
            }
            output += "|\n";
        }

        output += ("+"+"-".repeat(totalWidth+3*colNum-1)+"+\n");

        System.out.print(output);
    }
}
