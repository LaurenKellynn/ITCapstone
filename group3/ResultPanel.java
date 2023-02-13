/**
 * Programmer name: Emma Rawstron
 * Date: 2/6/2022
 * IT Capstone Project: Query Application
 * 
 * The ResultPanel will create the components and layout for the result panel
 * Components will be added to the ResultPanel and arranged with GridBagConstraints
 */

package group3;

import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

public class ResultPanel extends JPanel {
	
	JTable resultTable;
	DefaultTableModel tableModel;
	JTableHeader tableHeader;
	JLabel resultLabel;
	JButton printButton;
	JScrollPane scroll;
	JCheckBox printModeCheckBox;
	JCheckBox dateCheckBox;
	
	public ResultPanel() {
		super();
		init();
	}	
	
	private void init() {
		tableModel = new DefaultTableModel(); //Table model
		resultTable = new JTable(tableModel);
		resultTable.setCellSelectionEnabled(true); //Changes from row select to cell select
		tableHeader = new JTableHeader();		
		tableHeader = resultTable.getTableHeader(); //create table header
		
		tableHeader.setFont(new Font("Verdana", Font.PLAIN, 14));
		resultTable.setFont(new Font("Verdana", Font.PLAIN, 12));

		//resultTable.setShowHorizontalLines(false); //Hides horizontal lines	
		//resultTable.setShowVerticalLines(false); //Hides vertical lines	
		
		placeComponents(this); //Add all components to panel		

		this.setBorder(BorderFactory.createEmptyBorder(10,30,5,30));
		this.setBackground(new Color(220,230,240)); //Set result panel background color
		tableHeader.setBackground(new Color(230, 245, 255)); //Set table header color

		resultTable.addMouseListener(new TableListener(this)); //Add listeners
		printButton.addActionListener(new PrintButtonListener(this));
		
		this.setVisible(false); //Hide the result panel
	}
	
	//Sets the layout for components on the result panel
	private void placeComponents(Container pane) {			
		pane.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();

		resultLabel = new JLabel(""); //Text changes based on the chosen query
		//gbc.anchor = GridBagConstraints.CENTER;
		//gbc.fill = GridBagConstraints.BOTH;	
		gbc.gridx = 1;
		gbc.gridy = 2;
		gbc.gridwidth = 4;
		gbc.weightx = 0.5;
		gbc.weighty = 0;
		gbc.insets = new Insets(0, 0, 10, 0); //top, left, bottom, right
		pane.add(resultLabel, gbc);
		
		scroll = new JScrollPane(resultTable); //Add resultTable to scrollPane
		//gbc.anchor = GridBagConstraints.WEST;
	        gbc.fill = GridBagConstraints.BOTH;
		gbc.gridx = 2;
	        gbc.gridy = 3;
	        gbc.gridwidth = 4;
		gbc.weightx = 1;
		gbc.weighty = 1;
		gbc.insets = new Insets(8, 15, 0, 15);
		pane.add(scroll, gbc);
		
		gbc.anchor = GridBagConstraints.EAST;
		gbc.fill = GridBagConstraints.NONE;
		gbc.gridx = 2;
		gbc.gridy = 4;
		gbc.gridwidth = 1;
		gbc.weightx = 0.5;
		gbc.weighty = 0;
		gbc.insets = new Insets(0, 0, 0, 5);
		pane.add(new JLabel("Print Settings:"), gbc);
	    
		printModeCheckBox = new JCheckBox("Fit to width");
		gbc.anchor = GridBagConstraints.WEST;
		//gbc.fill = GridBagConstraints.NONE;
		gbc.gridx = 3;
		gbc.gridy = 4;
		gbc.gridwidth = 1;
		gbc.weightx = 0.5;
		gbc.weighty = 0;
		gbc.insets = new Insets(5, 0, 0, 0);  //top, left, bottom, right
		pane.add(printModeCheckBox, gbc);
	    
		dateCheckBox = new JCheckBox("Include date");
	        //gbc.anchor = GridBagConstraints.WEST;
	        //gbc.fill = GridBagConstraints.NONE;
		gbc.gridx = 3;
	        gbc.gridy = 5;
	        gbc.gridwidth = 1;
	        gbc.weightx = 0.5;
	        gbc.weighty = 0;
	        gbc.insets = new Insets(0, 0, 0, 0);
	        pane.add(dateCheckBox, gbc);
		
	        printButton = new JButton("Print Table"); //Create the print button	
	        gbc.anchor = GridBagConstraints.WEST;
	        //gbc.fill = GridBagConstraints.BOTH;
	        gbc.gridx = 4;
	        gbc.gridy = 4;
	        gbc.gridwidth = 1;
	        gbc.weightx = 0.5;
	        gbc.weighty = 0;
	        gbc.insets = new Insets(10, 20, 0, 0);
	        pane.add(printButton, gbc);
	}
	
}
