/**
 * Programmer name: Emma Rawstron
 * Date: 2/6/2023
 * IT Capstone Project: Query Application
 */

package group3;

import java.awt.event.*;
import java.sql.*;
import javax.swing.*;

/**
 * ButtonListener class is responsible for handling button click events in the SearchFrame.
 * It triggers actions such as executing database queries and populating the results table.
 */
public class ButtonListener implements ActionListener {
    JPanel searchPanel;
    ResultPanel resultPanel;
    SearchFrame frame;
    Boolean usePreparedStatement = false;
    String userNameInput = "";
    

    /**
     * Constructs a ButtonListener with a reference to the SearchFrame.
     *
     * @param inputFrame the SearchFrame containing the buttons this listener will handle.
     */
    public ButtonListener(SearchFrame inputFrame) {
        frame = inputFrame;
        searchPanel = frame.searchPanel;
        resultPanel = ((ResultPanel) frame.resultPanel);
    }
 
    /**
     * Handles button click events and performs the appropriate DBUtility actions.
     *
     * @param e the ActionEvent containing information about the button click event.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        DatabaseHandler dbHandler = new DatabaseHandler();
        JButton source = (JButton) e.getSource();
        String actionCommand = source.getActionCommand();
        userNameInput = frame.userName.getText().trim();
        
        // Display an error message if the user has not entered a customer name and is trying to search order history
        if (userNameInput.length() == 0 && "SEARCH_ORDER_HISTORY".equals(actionCommand)) {
            resultPanel.setVisible(false);
            resultPanel.resultLabel.setText("");
            JOptionPane.showMessageDialog(frame, "Enter a customer name to search", 
                    "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            // Show all customer information when the "SEARCH_ALL" button is clicked
            if ("SEARCH_ALL".equals(actionCommand)) {
                try {
                    resultPanel.resultLabel.setText("All Customer Information");
                    resultPanel.resultLabel.setToolTipText("Double click on a username to view order history for selected user");
                    DBUtility.showTable(resultPanel, dbHandler.getAllCustomerInfo());
                } catch (SQLException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
            // Show order history for a specific user when the "SEARCH_ORDER_HISTORY" button is clicked
            } else if ("SEARCH_ORDER_HISTORY".equals(actionCommand)) {
                try {
                    resultPanel.resultLabel.setText("Order History for " + userNameInput);
                    resultPanel.resultLabel.setToolTipText(null);
                    DBUtility.showTable(resultPanel, dbHandler.getOrderHistory(userNameInput));
                } catch (SQLException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
            }
            //Resize the frame if it is too small for content
            frame.adjustFrameBounds(frame.getPreferredSize(), frame.getLocation()); 
        }
    }

}

