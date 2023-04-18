/**
 * Programmer name: Emma Rawstron
 * Date: 3/6/2023
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
    SearchFrame frame;
    JMenuBar menuBar;
    
    public PrintButtonListener(CustomMenuBar inputMenuBar, SearchFrame inputFrame) {
        frame = inputFrame;
        resultPanel = (ResultPanel) frame.resultPanel;
        menuBar = (JMenuBar)inputMenuBar;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        Locale locale = new Locale("en", "US");
        DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.DEFAULT, locale);
        String date = dateFormat.format(new Date());

        // Create header message format with initial text
        MessageFormat header = new MessageFormat(resultPanel.resultLabel.getText());
        
        // Create footer message format with page number
        MessageFormat footer = new MessageFormat("Page {0}");
        
        // Set initial print mode
        PrintMode printMode = JTable.PrintMode.NORMAL;

        // Check if "Fit to width" checkbox is selected
        if (((CustomMenuBar) menuBar).getFitWidthStatus()) {
            // Set print mode to fit width
            printMode = JTable.PrintMode.FIT_WIDTH;
            System.out.println(printMode.toString());
        }
        else {
            System.out.println(printMode.toString());
        }

        // Check if "Include date" checkbox is selected
        if (((CustomMenuBar) menuBar).getIncludeDateStatus()) {
            // Add current date to header message format
            header = new MessageFormat(resultPanel.resultLabel.getText() + ", " +  date);
            System.out.println(resultPanel.resultLabel.getText() + ", " + date);
        }

        try {
            resultPanel.resultTable.print(printMode, header, footer); //Print the table
        }
        catch (PrinterException ex) {
            ex.printStackTrace();
        }
    }

}


