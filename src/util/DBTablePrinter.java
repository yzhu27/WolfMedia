package util;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

/**
 * A utility class to print the contents of a SQL ResultSet or table.
 */
public class DBTablePrinter {

    /**
     * Prints the contents of a given SQL ResultSet.
     *
     * @param rs the SQL ResultSet to print.
     * @throws SQLException if an error occurs while accessing the ResultSet.
     */
    public static void printResultSet(ResultSet rs) throws SQLException {
        String output = "\n";
        ResultSetMetaData metadata = rs.getMetaData();
        int colNum = metadata.getColumnCount();

        int[] colSizes = new int[colNum];
        for (int i = 0; i < colNum; i++) {
            colSizes[i] = metadata.getColumnName(i + 1).length();
        }
        int totalWidth = 0;
        while (rs.next()) {
            for (int i = 0; i < colNum; i++) {
                int attrLen = 0;
                if (rs.getString(i + 1) != null) {
                    attrLen = rs.getString(i + 1).length();
                }
                if (attrLen > colSizes[i]) {
                    colSizes[i] = attrLen;
                }

            }
        }
        for (int i = 0; i < colNum; i++) {
            totalWidth += colSizes[i];
        }

        rs.beforeFirst();

        output += ("+" + "-".repeat(totalWidth + 3 * colNum - 1) + "+\n");

        for (int i = 0; i < colNum; i++) {
            output += String.format(String.format("| %%%ds ", colSizes[i]), metadata.getColumnName(i + 1));
        }
        output += "|\n";

        output += ("|" + "-".repeat(totalWidth + 3 * colNum - 1) + "|\n");

        while (rs.next()) {
            for (int i = 0; i < colNum; i++) {
                output += String.format(String.format("| %%%ds ", colSizes[i]), rs.getString(i + 1));
            }
            output += "|\n";
        }

        output += ("+" + "-".repeat(totalWidth + 3 * colNum - 1) + "+\n");

        System.out.print(output);
    }

    /**
     * Prints the contents of a given SQL table.
     *
     * @param tableName the name of the SQL table to print.
     * @throws SQLException if an error occurs while accessing the table.
     */
    public static void printTable(String tableName) throws SQLException {
        String sql = String.format("SELECT * FROM " + tableName + ";");
        queryExecuter.execute(sql);
    }

}
