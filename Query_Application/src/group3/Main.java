/**
 * Programmer name: Emma Rawstron
 * Date: 2/6/2023
 * IT Capstone Project: Query Application
 * 
 * The program will display a GUI with two query options:
 *      1. Display information for all customers
 *      2. Display order history for one customer
 * 
 * A table with query results and a print button will be displayed
 */

package group3;

import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

public class Main {

    public static void main(String[] args) {
        
        try {
            // Schedule the GUI construction to occur on the EDT to ensure thread safety
            SwingUtilities.invokeLater(new Runnable() {
                public void run() {
                    constructGUI();
                }
            });           
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "An error occurred: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }


    }
    
    // Construct the GUI frame and set the icon image
    private static void constructGUI() {
        SearchFrame frame = new SearchFrame();
        
        Image icon = Toolkit.getDefaultToolkit().getImage(
                "starfish2.png");
        frame.setIconImage(icon);
        frame.setVisible(true);
    }

}
