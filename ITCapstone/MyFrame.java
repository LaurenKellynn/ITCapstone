/*
 * Programmer name: Emma Rawstron
 * Date: 2/4/2022
 * IT Capstone Project
 */

package ITCapstone;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PrinterException;
import java.text.MessageFormat;
import javax.swing.*;
import javax.swing.JTable.PrintMode;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;


public class MyFrame extends JFrame {
		
	JButton allUsers;
	JButton orderHistory;
	JLabel searchAllLabel;
	
	JLabel userNameLabel;
	JTextField userName;
	JPanel searchPanel;
	JPanel userSearchPanel;
	JPanel resultPanel;
	JTable resultTable;
	DefaultTableModel tableModel;
	JTableHeader tableHeader;
	JLabel resultLabel;
	JButton printButton;
	JScrollPane scroll;
	
	public MyFrame() {
		super();
		init();
	}
	
	private void init() {
		this.setTitle("Query Application");	//Change the title
		
		//Search all panel
		searchPanel = new JPanel();
		searchPanel.setBorder(BorderFactory.createEmptyBorder(10,0,0,10));
		searchAllLabel = new JLabel("Show all users: ");
		allUsers = new JButton("Search all");
		searchPanel.setLayout(new BorderLayout());

		
		//Search user panel
		userName = new JTextField(12);
		orderHistory = new JButton("Search user");
		userNameLabel = new JLabel("Enter a user name to search: ");
		userSearchPanel = new JPanel();
		userSearchPanel.setLayout(new BorderLayout()); //Set the layout for the user search panel
		userSearchPanel.setBorder(BorderFactory.createEmptyBorder(10,10,0,0));
		
		//Result panel
		resultPanel = new JPanel();
		tableModel = new DefaultTableModel(); //Table model
		resultTable = new JTable(tableModel);
		//resultTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		tableHeader = new JTableHeader();
		resultLabel = new JLabel("");
		resultLabel.setVerticalAlignment(SwingConstants.BOTTOM);
		resultLabel.setHorizontalAlignment(SwingConstants.LEFT);
		//resultLabel.setBackground(new Color(50, 50, 50));
		//resultLabel.setForeground(new Color(50, 50, 50));
		tableHeader = resultTable.getTableHeader(); //create table header
		scroll = new JScrollPane(resultTable);
		//scroll.setPreferredSize(scroll.getPreferredSize());
		//resultPanel.setLayout(new BorderLayout());
		printButton = new JButton("Print");
		
		resultPanel.setVisible(false); //Hide the result panel

		FlowLayout flow = new FlowLayout();

		getContentPane().setLayout(flow); //Set the layout
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//Search all panel
		searchPanel.add(searchAllLabel, BorderLayout.WEST);
		searchPanel.add(allUsers, BorderLayout.EAST);
		this.add(searchPanel);
		
		//User search panel
		getContentPane().add(userSearchPanel);
		userSearchPanel.add(userNameLabel, BorderLayout.WEST);
		userSearchPanel.add(userName, BorderLayout.CENTER);
		userSearchPanel.add(orderHistory, BorderLayout.EAST);		
		
		//Result panel
		scroll.setColumnHeaderView(tableHeader);
		tableHeader.setBackground(new Color(200, 235, 255));
		//resultPanel.add(resultTable); //add table to panel
		//resultPanel.add(tableHeader); //add table header
		getContentPane().add(resultPanel);
		GroupLayout gl_resultPanel = new GroupLayout(resultPanel);
		gl_resultPanel.setHorizontalGroup(
			gl_resultPanel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_resultPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_resultPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(Alignment.TRAILING, gl_resultPanel.createSequentialGroup()
							.addComponent(printButton)
							.addGap(18))
						.addGroup(gl_resultPanel.createSequentialGroup()
							.addComponent(resultLabel, GroupLayout.PREFERRED_SIZE, 218, GroupLayout.PREFERRED_SIZE)
							.addContainerGap(301, Short.MAX_VALUE))
						.addGroup(Alignment.TRAILING, gl_resultPanel.createSequentialGroup()
							.addComponent(scroll, GroupLayout.PREFERRED_SIZE, 557, GroupLayout.PREFERRED_SIZE)
							.addContainerGap())))
		);
		gl_resultPanel.setVerticalGroup(
			gl_resultPanel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_resultPanel.createSequentialGroup()
					.addComponent(resultLabel, GroupLayout.DEFAULT_SIZE, 20, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scroll, GroupLayout.PREFERRED_SIZE, 413, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(printButton)
					.addGap(18))
		);
		resultPanel.setLayout(gl_resultPanel);
		
		//Add button listeners
		printButton.addActionListener(new PrintListener(this));
		orderHistory.addActionListener(new ButtonListener(this));
		allUsers.addActionListener(new ButtonListener(this));
	
		//Setting up the frame size and location
		int frameWidth = 600;
		int frameHeight = 500;
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		this.setBounds((int) screenSize.getWidth()/2 - frameWidth, 
						(int) (screenSize.getHeight()/2) - frameHeight, 
						668, 571);
	}

	
}

//Brings up print menu
class PrintListener implements ActionListener {
	MyFrame frame;
	public PrintListener(MyFrame inputFrame) {
		frame = inputFrame;
	}
    public void actionPerformed(ActionEvent e) {
        MessageFormat header = new MessageFormat("Query Results");
        MessageFormat footer = new MessageFormat("Page {0}");

        try {
           frame.resultTable.print(PrintMode.FIT_WIDTH, header, footer);
        }
        catch (PrinterException ex) {
            ex.printStackTrace();
        }
    }
}

