/*
 * Programmer name: Emma Rawstron
 * Date: 2/1/2022
 * IT Capstone Project
 */

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class Query {
	public List<String> queryResults;
	public String[][] queryResultTable;
	public String[] columnNames;
	private int queryNumber;
	
	public Query(int selectedQuery) {
		this.setQueryNumber(selectedQuery);
		connect();
	}
	public String[][] getResults() {
		return queryResultTable;
	}
	public void setResults(List<String> inputResults) {
		queryResults = inputResults;
	}
	public String[] getColumnNames() {
		return this.columnNames;
	}
	public int getQueryNumber() {
		return this.queryNumber;
	}
	public void setQueryNumber(int queryNumberInput) {
		this.queryNumber = queryNumberInput;
	}
	
	public ResultSet connect() {
		Connection connection;
		ResultSet results = null;
		try {
			//ResultSet results = null;
			//results = null;
			
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			String url = "jdbc:mysql://localhost:3306/test";
			connection = DriverManager.getConnection(url,"root","cop2805");
			
			String query = "";
			
			if (queryNumber == 1)
				query = "SELECT * FROM Employees";
			if (queryNumber == 2)
				query = "SELECT firstName FROM Employees";
			
			Statement st = connection.createStatement();
			results = st.executeQuery(query);
			ResultSetMetaData metadata = results.getMetaData();
			int numCols = metadata.getColumnCount();

			//String resultText = "";
			List<String> resultList = new ArrayList<>();
			for (int i = 1; i < numCols; i++) {
				columnNames[i] = metadata.getColumnName(i);
			}
			int row = 0;
			while (results.next()) {
				//System.out.println(results.getInt(1) + ": " + results.getString(3) + ", " + results.getString(2));
				for (int col = 1; col < numCols; col++) {
					resultList.add(results.getString(col));
					queryResultTable[row][col] = results.getString(col);
					row++;
				}


				//resultText += results.getInt(1) + ": " + results.getString(3) + ", " + results.getString(2) + "\n";
				//JOptionPane.showMessageDialog(null, resultText, "the title", JOptionPane.PLAIN_MESSAGE);
			}
			//Query newQuery = new Query(selectedQuery);
			queryResults = new ArrayList<>();
			queryResults = resultList;
			//resultList;
			//newQuery.setResults(resultList);
			//JOptionPane.showMessageDialog(null, resultText, "Query Results", JOptionPane.PLAIN_MESSAGE);
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return results;
	}
	
}
