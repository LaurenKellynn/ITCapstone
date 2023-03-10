/**
 * Programmer name: Emma Rawstron
 * Date: 2/6/2022
 * IT Capstone Project: Query Application
 * 
 * The ButtonListener class will connect to the DB and execute the selected query.
 * The ResultSet data will be displayed on the table and the resultPanel set to visible.
 */

package group3;

import java.awt.event.*;
import java.sql.*;
import javax.swing.*;

public class ButtonListener implements ActionListener {
    JPanel searchPanel;
    ResultPanel resultPanel;
    SearchFrame frame;

    public ButtonListener(JPanel inputSPanel, JPanel inputRPanel, SearchFrame inputFrame) {
        searchPanel = inputSPanel;
        resultPanel = (ResultPanel) inputRPanel;
        frame = inputFrame;
    }

    public ButtonListener(ResultPanel inputResultPanel) {
        resultPanel = inputResultPanel;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        JButton source = (JButton) e.getSource();
        String buttonText = source.getText();
        String userNameInput = frame.userName.getText().trim();
        String query = "";

        // Query to print all user information
        if (buttonText == "Search all") {
            resultPanel.resultLabel.setText("All Customer Information");
            frame.userName.setText("");// Reset userName search text
            query = "SELECT * FROM Customers";
        }
        // Query to print order history for one user
        if (buttonText == "Search") {
            resultPanel.resultLabel.setText("Order History for " + userNameInput);
            query = "SELECT * FROM orders" + " WHERE username =" + "'" + userNameInput + "'";
        }

        // Prevent the search history button from continuing if customer field is blank
        if (userNameInput.length() == 0 && buttonText == "Search") {
            resultPanel.setVisible(false);
            resultPanel.resultLabel.setText("");
            JOptionPane.showMessageDialog(frame, "Enter a customer name to search", 
                    "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            showTable(query);
            //frame.pack();
            //Resize the frame if it is too small for content
            frame.resetFrameBounds(frame.getPreferredSize(), frame.getLocation()); 
        }
    }

    /*
     * Function will connect to the DB and execute the selected query. The ResultSet
     * data will be passed to the populateTableData function and displayed on the table.
     */
    private void showTable(String query) {
        // Reset the table model
        resultPanel.tableModel.setColumnCount(0);
        resultPanel.tableModel.setRowCount(0);

        Connection connection;
        try {
            ResultSet results = null;
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
            } catch (ClassNotFoundException e1) {
                e1.printStackTrace();
            }
            String url = "jdbc:mysql://localhost:3306/test";

            String password = ""; // Change the password
            connection = DriverManager.getConnection(url, "root", password);
            Statement st = connection.createStatement();
            results = st.executeQuery(query);

            populateTableData(results); // Populates the table with result data
            resultPanel.setVisible(true); // Show the table

            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Populates table rows and columns with ResultSet data
    public void populateTableData(ResultSet inputResults) {

        try {
            ResultSetMetaData metadata = inputResults.getMetaData();
            int numCols = metadata.getColumnCount(); // Get number of columns
            Object[] resultRow = new Object[numCols];
            String columnName = "";

            for (int col = 0; col < numCols; col++) {
                columnName = metadata.getColumnName(col + 1); // Index starts at 1
                resultPanel.tableModel.addColumn(columnName);// Add the column to the table
            } // End for loop

            while (inputResults.next()) {                 
                for (int col = 0; col < numCols; col++) {
                    resultRow[col] = inputResults.getObject(col + 1); // Index starts at 1
                }// End inner loop                
                resultPanel.tableModel.addRow(resultRow); // Add the row to the table
            } // End outer loop

            inputResults.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
