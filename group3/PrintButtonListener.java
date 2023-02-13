/**
 * Programmer name: Emma Rawstron
 * Date: 2/6/2022
 * IT Capstone Project: Query Application
 */

package group3;

import java.awt.event.*;
import java.awt.print.PrinterException;
import java.text.*;
import java.util.*;
import javax.swing.*;
import javax.swing.JTable.PrintMode;

public class PrintButtonListener implements ActionListener {
	ResultPanel resultPanel;
	
	public PrintButtonListener(ResultPanel inputResultPanel) {
		resultPanel = inputResultPanel;
	}
	
    public void actionPerformed(ActionEvent e) {
    	Locale locale = new Locale("en", "US");
    	DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.DEFAULT, locale);
    	String date = dateFormat.format(new Date());
    	//System.out.println(date);
        MessageFormat header = new MessageFormat("Query Results:  ");
        MessageFormat footer = new MessageFormat("Page {0}");
        PrintMode printMode = JTable.PrintMode.NORMAL;
        
        //Change print mode to fit width of page if check box is selected
        if (resultPanel.printModeCheckBox.isSelected()) {
        	System.out.println(printMode.toString());
        	printMode = JTable.PrintMode.FIT_WIDTH;
        	System.out.println(printMode.toString());
        }
        
        //Change header to include the date if check box is selected
        if (resultPanel.dateCheckBox.isSelected()) {
        	header = new MessageFormat("Query Results:  " + date);
        	System.out.println("Header: Query Results:  " + date);
        }
        
        try {
           resultPanel.resultTable.print(printMode, header, footer); //Print the table
        }
        catch (PrinterException ex) {
            ex.printStackTrace();
        }
    }
    
}

