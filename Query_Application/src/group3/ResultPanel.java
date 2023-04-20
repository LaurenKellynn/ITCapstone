/**
 * Programmer name: Emma Rawstron
 * Date: 2/6/2023
 * IT Capstone Project: Query Application
 * 
 * The ResultPanel will create the components and layout for the result panel
 * Components will be added to the ResultPanel and arranged with GridBagConstraints
 */

package group3;

import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;

public class ResultPanel extends JPanel {
    
    JTable resultTable;
    DefaultTableModel tableModel;
    JTableHeader tableHeader;
    JLabel resultLabel;
    JScrollPane scroll;
    
    public ResultPanel(SearchFrame inputFrame) {
        super();
        
        // Create a table model and a JTable with the model
        tableModel = new DefaultTableModel();
        resultTable = new JTable(tableModel) {
            DefaultTableCellRenderer render = new DefaultTableCellRenderer(); {
                render.setHorizontalAlignment(SwingConstants.CENTER); //Change cell alignment
                }

            @Override
            public TableCellRenderer getCellRenderer (int r, int c) {
                return render;
            }
        };
        
        // Create table header
        tableHeader = resultTable.getTableHeader();
        
        // Hides horizontal and vertical lines 
        resultTable.setShowHorizontalLines(false);
        resultTable.setShowVerticalLines(false);
        
        //Add components to panel and set layout
        placeComponents(this); 

        this.setBorder(BorderFactory.createEmptyBorder(5,20,20,20));
        
        //Hide the result panel
        this.setVisible(false);
        
        // Add mouse listener for table selection events
        resultTable.addMouseListener(new TableListener(this));
    }   
       
    // Place components on the result panel with GridBagLayout
    private void placeComponents(Container panel) {         
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        
        //Results label text changes based on the chosen query
        resultLabel = new JLabel("");
        //gbc.anchor = GridBagConstraints.CENTER;
        //gbc.fill = GridBagConstraints.BOTH;   
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.gridwidth = 4;
        gbc.weightx = 0.5;
        gbc.weighty = 0;
        gbc.insets = new Insets(0, 0, 5, 0); //top, left, bottom, right
        panel.add(resultLabel, gbc);
        
        //Add resultTable to scrollPane
        scroll = new JScrollPane(resultTable);
        scroll.setColumnHeader(scroll.getColumnHeader());
        scroll.add(tableHeader);
        //gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.gridx = 2;
        gbc.gridy = 3;
        gbc.gridwidth = 4;
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.insets = new Insets(0, 0, 0, 0);
        panel.add(tableHeader);
        panel.add(scroll, gbc);        
    }
    
}
