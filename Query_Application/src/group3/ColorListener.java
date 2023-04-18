/**
 * Programmer name: Emma Rawstron
 * Date: 3/6/2023
 * IT Capstone Project: Query Application
 * 
 * The ColorListener class is an ActionListener that handles color
 * changes for the application's components when the user toggles
 * between dark and light mode.
 */

package group3;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.table.JTableHeader;

public class ColorListener implements ActionListener {

    private SearchFrame frame;
    private JPanel searchPanel;
    private JPanel resultPanel;
    private JMenuBar menuBar;
    
    private Color backColor, foreColor;
    private Color buttonBackColor;
    private Color buttonForeColor;
    private Color textFieldBackColor, textFieldColor;
    private Color tableBackColor, tableForeColor;
    private boolean darkModeSelected = false;
    
    // Constructor
    public ColorListener(SearchFrame inputFrame, JMenuBar inputMenuBar) {
        frame = inputFrame;      
        searchPanel = frame.searchPanel;
        resultPanel = frame.resultPanel;
        menuBar = inputMenuBar;
        initializeColors();
    }
    
    // Returns the dark mode status
    public boolean isDarkModeSelected() {
        return darkModeSelected;
    }
    
    // Initializes colors based on the current mode
    private void initializeColors() {
        if (darkModeSelected) {
            applyDarkModeColors();
        } else {
            applyLightModeColors();
        }
        applyColorToComponents(searchPanel.getComponents());
        applyColorToComponents(resultPanel.getComponents());
    }
    
    // Sets the colors for dark mode
    private void applyDarkModeColors() {
        backColor = new Color(45, 45, 50);
        foreColor = new Color(224, 224, 224);
        buttonBackColor = new Color(70, 75, 80);
        buttonForeColor = new Color(224, 224, 224);
        textFieldBackColor = new Color(50, 50, 50);
        textFieldColor = new Color(224, 224, 224);
        tableBackColor = new Color(35, 35, 40);
    }
    
    // Sets the colors for light mode
    private void applyLightModeColors() {
        backColor = new Color(235, 240, 245);
        foreColor = new Color(30, 30, 30);
        buttonBackColor = new Color(220, 240, 250);
        buttonForeColor = new Color(20, 40, 50);
        textFieldBackColor = new Color(245, 245, 245);
        textFieldColor = new Color(0, 0, 0);
        tableBackColor = new Color(255, 255, 255);
    }
    
    // ActionListener: toggles dark mode based on user input
    @Override
    public void actionPerformed(ActionEvent e) {
        //darkModeSelected = frame.darkModeCheckBox.isSelected();
        darkModeSelected = ((JCheckBoxMenuItem)e.getSource()).isSelected();
        initializeColors();
    }

    // Applies the appropriate color to each component in the input array
    private void applyColorToComponents(Component[] inputArray) {
        frame.getContentPane().setBackground(backColor);
        searchPanel.setBackground(backColor);
        resultPanel.setBackground(backColor);
        updateMenuBarColors();

        for (Component c : inputArray) {
            c.setFont(new Font("Verdana", Font.PLAIN, 12));

            if (c instanceof JButton) {
                applyColorToButton((JButton) c);
            } else if (c instanceof JLabel) {
                applyColorToLabel((JLabel) c);
            } else if (c instanceof JTextField) {
                applyColorToTextField((JTextField) c);
            } else if (c instanceof JCheckBox) {
                applyColorToCheckBox((JCheckBox) c);
            } else if (c instanceof JScrollPane) {
                applyColorToScrollPane((JScrollPane) c);
            }
        }
    }
    
    private void applyColorToButton(JButton button) {
        button.setBackground(buttonBackColor);
        button.setForeground(buttonForeColor);
    }

    private void applyColorToLabel(JLabel label) {
        label.setForeground(foreColor);
    }

    private void applyColorToTextField(JTextField textField) {
        textField.setBackground(textFieldBackColor);
        textField.setForeground(textFieldColor);
    }

    private void applyColorToCheckBox(JCheckBox checkBox) {
        checkBox.setBackground(backColor);
        checkBox.setForeground(foreColor);
    }

    private void applyColorToScrollPane(JScrollPane scrollPane) {
        scrollPane.getViewport().setBackground(tableBackColor);

        if (scrollPane.getViewport().getView() instanceof JTable) {
            applyColorToTable((JTable) scrollPane.getViewport().getView());
        }
    }

    private void applyColorToTable(JTable table) {
        table.setBackground(tableBackColor);
        table.setForeground(foreColor);
        table.setFont(new Font("Verdana", Font.PLAIN, 12));

        JTableHeader header = table.getTableHeader();
        header.setBackground(buttonBackColor);
        header.setForeground(buttonForeColor);
        header.setFont(new Font("Verdana", Font.PLAIN, 12));
    }
    
    // Updates the colors for menu bar components
    private void updateMenuBarColors() {
        menuBar.setBackground(backColor);
        menuBar.setForeground(foreColor);
        menuBar.setFont(new Font("Verdana", Font.PLAIN, 12));      

        for (MenuElement menuElement : menuBar.getSubElements()) {
            if (menuElement instanceof JMenu) {
                JMenu menu = (JMenu) menuElement;
                menu.setBackground(backColor);
                menu.setForeground(foreColor);
                menu.setFont(new Font("Verdana", Font.PLAIN, 12));      

                for (MenuElement menuItemElement : menu.getSubElements()) {
                    if (menuItemElement instanceof JMenuItem) {
                        JMenuItem menuItem = (JMenuItem) menuItemElement;
                        menuItem.setFont(new Font("Verdana", Font.PLAIN, 12));   
                        menuItem.setBackground(backColor);
                        menuItem.setForeground(foreColor);
                    }
                }
            }
        }
    }

   
}
