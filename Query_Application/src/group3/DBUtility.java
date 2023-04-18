/**
 * Programmer name: Emma Rawstron
 * Date: 3/7/2023
 * IT Capstone Project: Query Application
 */

package group3;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Properties;
import javax.swing.JOptionPane;
import javax.swing.table.TableColumnModel;

public class DBUtility {
    
    // Returns a connection object to the database
    public static Connection getConnection() throws SQLException {
        Connection connection = null;
        
        try (FileInputStream f = new FileInputStream("db.properties")) {
            
            //Load the properties file
            Properties properties = new Properties();
            properties.load(f);
            
            //Set database parameters
            String url = properties.getProperty("url");
            String user = properties.getProperty("user");
            String password = properties.getProperty("password");
            
            connection = DriverManager.getConnection(url, user, password);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return connection;
    }
    
    // Reset the table data and show the table with new result set data
    public static void showTable(ResultPanel resultPanel, ResultSet results) {
        // Reset the table model
        resultPanel.tableModel.setColumnCount(0);
        resultPanel.tableModel.setRowCount(0);

        populateTableData(resultPanel, results); // Populates the table with result data
        resultPanel.setVisible(true); // Show the table       
    }
    
    // Populates the result panel table with data from the input ResultSet object
    public static void populateTableData(ResultPanel resultPanel, ResultSet inputResults) {
        TableColumnModel colModel = resultPanel.resultTable.getColumnModel();
        resultPanel.resultTable.setColumnModel(colModel);

        try  {
            ResultSetMetaData metadata = inputResults.getMetaData();
            int numCols = metadata.getColumnCount(); // Get number of columns
            String columnName = "";
            String cellString = "";
            
            // Array to store the maximum width for each column
            int[] maxWidths = new int[numCols];
            
            // Loop through columns and add them to the table model
            for (int col = 0; col < numCols; col++) {
                columnName = metadata.getColumnName(col + 1); // Index starts at 1
                resultPanel.tableModel.addColumn(columnName); // Add the column to the table
                maxWidths[col] = columnName.length() * 8; // Set initial width based on column name
            }
            
            // Loop through rows and add them to the table model
            while (inputResults.next()) {
                Object[] resultRow = new Object[numCols];
                for (int col = 0; col < numCols; col++) {
                    resultRow[col] = inputResults.getObject(col + 1); // Index starts at 1
                    if (resultRow[col] != null) {
                        cellString = resultRow[col].toString();
                    } else {
                        cellString = "";
                    }
                    int cellWidth = cellString.length() * 8; // Calculate cell width based on content
                    maxWidths[col] = Math.max(maxWidths[col], cellWidth); // Update the maximum width
                }//End inner loop
                resultPanel.tableModel.addRow(resultRow); // Add the row to the table
            }//End outer loop

            // Set the preferred column widths based on the maximum widths
            for (int col = 0; col < numCols; col++) {
                colModel.getColumn(col).setPreferredWidth(maxWidths[col]);
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(resultPanel, "An error occurred while populating the table data.", "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }



}

