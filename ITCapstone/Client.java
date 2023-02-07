/*
 * Programmer name: Emma Rawstron
 * Date: 2/4/2022
 * IT Capstone Project
 */

package ITCapstone;

import javax.swing.JDialog;
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
		MyFrame frame = new MyFrame();
		//JDialog.setDefaultLookAndFeelDecorated(true);
		//frame.pack();
		frame.setVisible(true);

	}

}
