/*
 * Programmer name: Emma Rawstron
 * Date: 2/1/2022
 * IT Capstone Project
 */

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.TableColumnModel;


public class ButtonListener implements ActionListener {	
	MyFrame frame;
	List<String> resultList = new ArrayList<>();
	String[][] dataValues;

	
	public ButtonListener(MyFrame inputFrame) {
		frame = inputFrame;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		JButton source = (JButton) e.getSource();
		String buttonText = source.getText();
		String userNameInput = frame.userName.getText().trim();
		//System.out.print(userNameInput.length());
		if (userNameInput.length() == 0) {
			//JFrame.setDefaultLookAndFeelDecorated(true);
			JOptionPane.showMessageDialog(null, "Enter a username", "Error", JOptionPane.ERROR_MESSAGE);
			JDialog.setDefaultLookAndFeelDecorated(true);
			JFrame.setDefaultLookAndFeelDecorated(true);
		} else {
			Query query = new Query(2);
			ResultSet results = query.connect();
			String[][] resultData = query.getResults();
			String[] columnNames = query.getColumnNames();
			
			//frame.tableModel.set(query.columnNames[0]);

			//frame.resultTable.setModel(resultColumns);
			//resultList = query.getResults();
			
			//frame.resultTable.setValueAt("Test", 0, 0);
			frame.resultLabel.setText("Order history for " + userNameInput);
			frame.resultPanel.setVisible(true);
			System.out.println(userNameInput);
		}
		
	}

}
