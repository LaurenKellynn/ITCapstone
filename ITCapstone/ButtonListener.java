/*
 * Programmer name: Emma Rawstron
 * Date: 2/4/2022
 * IT Capstone Project
 */

package ITCapstone;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.table.TableColumnModel;

public class ButtonListener implements ActionListener {
	MyFrame frame;
	
	public ButtonListener(MyFrame inputFrame) {
		frame = inputFrame;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		JButton source = (JButton) e.getSource();
		String buttonText = source.getText();
		String userNameInput = frame.userName.getText().trim();
		int queryNumber = 0;

		if (buttonText == "Search all") {
			queryNumber = 1;
		}
		if (buttonText == "Search user") {
			queryNumber = 2;
		}

		if (userNameInput.length() == 0 && queryNumber == 2) {
			frame.resultLabel.setText("");
			frame.resultPanel.setVisible(false);
			JOptionPane.showMessageDialog(null, "Enter a username to search", "Error", JOptionPane.ERROR_MESSAGE);		
		} else {
			showTable(queryNumber);
		}
		
	}	
	
	/*
	 * Function to add query results to the GUI table. 
	 * Will connect to DB, execute the query selected by the user,
	 * and then display the results on the results panel table.
	 */
	public void showTable(int queryNumber) {
		
		//Reset the table model
		frame.tableModel.setColumnCount(0);
		frame.tableModel.setRowCount(0);
		
		Connection connection;
		try {
			ResultSet results = null;
			
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
			} catch (ClassNotFoundException e1) {
				e1.printStackTrace();
			}
			String url = "jdbc:mysql://localhost:3306/test";
			String password = ""; //Change the password
			connection = DriverManager.getConnection(url,"root",password);
			String query = "";
			
			//Query to print all user information 
			if (queryNumber == 1) {
				frame.resultLabel.setText("All user information:");
				frame.userName.setText("");//Reset userName search text
				query = "SELECT * FROM Customers";
			}
			
			//Query to print order history for one user
			if (queryNumber == 2) {
				String userNameInput = frame.userName.getText().trim(); //Get userName input	
				frame.resultLabel.setText("Order history for " + userNameInput);
				query = "SELECT * FROM orders" + " WHERE username =" + "'" + userNameInput + "'";
			}
			Statement st = connection.createStatement();
			results = st.executeQuery(query);			
			ResultSetMetaData metadata = results.getMetaData();
			
			//Add column names to the table
			int numCols = metadata.getColumnCount(); //Get number of columns
			String columnName = "";
			for (int col = 0; col < numCols; col++) {
				columnName = metadata.getColumnName(col+1).toString();
				//System.out.println(columnName);
				frame.tableModel.addColumn(columnName);
			}
			setTableRows(results, numCols); //Populates the table rows with result data
			frame.resultPanel.setVisible(true); //Show the table
			//frame.pack();

			connection.close();
		} catch (SQLException e2) {
			e2.printStackTrace();
		}

	}
		
	//Populates table rows with ResultSet data
	public void setTableRows(ResultSet inputResults, int numCols) {
		if (inputResults != null) {
			List<Object> resultList = new ArrayList<>();
			TableColumnModel colModel = frame.resultTable.getColumnModel();
			Object[] resultRow = new Object[numCols];
			int rowNum = 0;
			try {
				while (inputResults.next()) {
					resultList.clear();
					for (int col = 0; col < numCols; col++) {
						resultList.add(inputResults.getObject(col+1));
						String rowString = resultList.get(col).toString();
						colModel.getColumn(col).setPreferredWidth(60 + (4 * rowString.length())); //Change column width
						resultRow[col] = resultList.get(col);
					}
					frame.tableModel.addRow(resultRow); //Add the row to the table
					rowNum++;
					//System.out.println(resultList.size());	
					//System.out.println(resultRow.length);	
				}//end loop	
				System.out.println(rowNum);	
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	}
	
		
}


