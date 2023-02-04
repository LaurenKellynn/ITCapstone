/*
 * Programmer name: Emma Rawstron
 * Date: 2/1/2022
 * IT Capstone Project
 */

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;


public class MyFrame extends JFrame {
		
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public JButton allUsers;
	public JButton orderHistory;
	public JLabel searchAllLabel;
	
	public JLabel userNameLabel;
	public JTextField userName;
	public JPanel searchPanel;
	public JPanel userSearchPanel;
	public JPanel resultPanel;
	public JTable resultTable;
	public DefaultTableModel tableModel;
	public JTableHeader tableHeader;
	public JLabel resultLabel;
	
	
	//Example data
	public String[][] data = { {"101","first","last"},    
            {"0","0","0"},    
            {"0","0","0"}}; 

	public String[] column = {"ID","name","lastName"};
	
	public MyFrame() {
		super();
		init();
	}
	
	private void init() {
	
		//Search all panel
		searchPanel = new JPanel();
		allUsers = new JButton("Search all");
		searchAllLabel = new JLabel("Show all users: ");
		searchPanel.setLayout(new BorderLayout());
		
		//User search panel
		orderHistory = new JButton("Search user");
		userName = new JTextField(10);
		//userName.setPreferredSize(new Dimension (150, 30));
		userNameLabel = new JLabel("Enter a user name to search: ");
		userSearchPanel = new JPanel();
		userSearchPanel.setLayout(new BorderLayout());
		
		//Result panel
		resultPanel = new JPanel();
		resultTable = new JTable(data, column);
		tableModel = new DefaultTableModel();
		tableHeader = new JTableHeader();
		resultLabel = new JLabel("Query Results:");
		tableHeader = resultTable.getTableHeader(); //create table header
		resultPanel.setLayout(new BorderLayout());
		resultPanel.setVisible(false);

		FlowLayout flow = new FlowLayout();
		BorderLayout border = new BorderLayout();

		this.setLayout(flow); //Set the layout
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		this.setTitle("Query Application");	//Change the title
		this.add(new JLabel("Select an option from the menu below:"));
		
		//Search all panel
		this.add(searchPanel);
		this.add(searchAllLabel);	
		this.add(allUsers); 
		
		searchPanel.add(searchAllLabel, BorderLayout.WEST);
		searchPanel.add(allUsers, BorderLayout.EAST);
		
		//User search panel
		this.add(userSearchPanel);
		this.add(userNameLabel);
		this.add(userName);
		this.add(orderHistory);
		
		userSearchPanel.add(userNameLabel, BorderLayout.WEST);
		userSearchPanel.add(userName, BorderLayout.CENTER);
		userSearchPanel.add(orderHistory, BorderLayout.EAST);
		
		//Result panel
		this.add(resultLabel);
		this.add(resultPanel);
		this.add(resultTable);
		this.add(tableHeader);		

		resultPanel.add(resultLabel, BorderLayout.NORTH);
		resultPanel.add(tableHeader, BorderLayout.CENTER); //add table header
		resultPanel.add(resultTable, BorderLayout.SOUTH); //add table to panel
		
		//Add button listeners
		allUsers.addActionListener(new ButtonListener(this));
		orderHistory.addActionListener(new ButtonListener(this));
		
		//Setting up the frame size and location
		int frameWidth = 400;
		int frameHeight = 300;
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		this.setBounds((int) (screenSize.getWidth()/2) - (frameWidth/2),
						(int) (screenSize.getHeight()/2) - frameHeight, 
						frameWidth, frameHeight);
	}
}

class Listener implements ActionListener {
	MyFrame frame;
	
	public Listener(MyFrame inputFrame) {
		frame = inputFrame;
	}
	
	public void actionPerformed(ActionEvent e) {
		

	}
}

class Listener2 implements ActionListener {
	MyFrame frame;
	
	public Listener2(MyFrame inputFrame) {
		frame = inputFrame;
	}
	public void actionPerformed(ActionEvent e) {
		
	}
}
