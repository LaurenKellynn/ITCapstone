/**
 * Programmer name: Emma Rawstron
 * Date: 3/6/2023
 * IT Capstone Project: Query Application
 * 
 */

package group3;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import javax.swing.*;

public class TableListener extends MouseAdapter {
    ResultPanel resultPanel;
    String query;
    DatabaseHandler dbHandler = new DatabaseHandler();

    public TableListener(JPanel inputResultPanel) {
        resultPanel = (ResultPanel)inputResultPanel;
    }
    
    @Override
    public void mouseClicked(MouseEvent event) {
        resultPanel.resultTable.setDefaultEditor(resultPanel.resultTable.getColumnClass(0), null);
        
        // Check if the user double clicked on a row
        if (event.getClickCount() == 2) {
            JTable table = (JTable) event.getSource();
            int row = table.rowAtPoint(event.getPoint());

            if (resultPanel.resultLabel.getText().equals("All Customer Information")) {
                String cellString = resultPanel.resultTable.getValueAt(row, 1).toString();
                showNewResults(cellString);
            }
        }
    }
   
    // Method to show the order history for a specific user
    public void showNewResults(String cellString) {
        resultPanel.resultLabel.setText("Order History for " + cellString);
        resultPanel.resultLabel.setToolTipText(null);
        try {
            DBUtility.showTable(resultPanel, dbHandler.getOrderHistory(cellString));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
        
}


