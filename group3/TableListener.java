/**
 * Programmer name: Emma Rawstron
 * Date: 2/6/2022
 * IT Capstone Project: Query Application
 * 
 * The TableListener class currently only prints a string for the clicked cell to the console.
 */

package group3;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;

public class TableListener extends MouseAdapter {
    ResultPanel resultPanel;

    public TableListener(JPanel inputResultPanel) {
        resultPanel = (ResultPanel)inputResultPanel;
    }
    
    @Override
    public void mouseClicked(MouseEvent event) {
        JTable table = (JTable) event.getSource();
        int row = table.rowAtPoint(event.getPoint());
        int col = table.columnAtPoint(event.getPoint());
        //System.out.println("Row: " + row + " Column: " + col);
        System.out.println(resultPanel.resultTable.getValueAt(row, col));
        

    }
}

