/*
 * Programmer name: Emma Rawstron
 * Date: 2/1/2022
 * IT Capstone Project
 */

import java.awt.*;
import javax.swing.*;
import java.sql.*;

public class Client {

	public static void main(String[] args) {
		
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				constructGUI();
			}
		});
		

	}//End main
	
	private static void constructGUI() {
		MyFrame frame = new MyFrame();
		JDialog.setDefaultLookAndFeelDecorated(true);
		frame.setVisible(true);

	}

}
