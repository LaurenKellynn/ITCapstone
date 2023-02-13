/**
 * Programmer name: Emma Rawstron
 * Date: 2/6/2022
 * IT Capstone Project: Query Application
 * 
 * The program will display a GUI with two query options:
 * 		1. Display information for all customers
 * 		2. Display order history for one customer
 * 
 * A table with query results and a print button will be displayed
 */

package group3;

import javax.swing.SwingUtilities;

public class Client {

	public static void main(String[] args) {
		
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				constructGUI();
			}
		});

	}
	
	private static void constructGUI() {
		Frame frame = new Frame();
		//JDialog.setDefaultLookAndFeelDecorated(true)
		//frame.pack();
		frame.setVisible(true);
	}

}
