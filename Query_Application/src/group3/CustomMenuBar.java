/**
 * Programmer name: Emma Rawstron
 * Date: 4/1/2023
 * IT Capstone Project: Query Application
 */

package group3;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.MenuSelectionManager;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;

public class CustomMenuBar extends JMenuBar {
    private NonClosingCheckBoxMenuItem printOption1 = new NonClosingCheckBoxMenuItem("Fit to width");
    private NonClosingCheckBoxMenuItem printOption2 = new NonClosingCheckBoxMenuItem("Include date");
    private NonClosingCheckBoxMenuItem toggleHorizontalAlignment = new NonClosingCheckBoxMenuItem("Horizontal Alignment");
    private NonClosingCheckBoxMenuItem toggleHorizontalLines = new NonClosingCheckBoxMenuItem("Horizontal Lines");
    private NonClosingCheckBoxMenuItem toggleVerticalLines = new NonClosingCheckBoxMenuItem("Vertical Lines");
    private SearchFrame frame;
    private Font menuFont = new Font("Verdana", Font.PLAIN, 12);

    // Constructor for CustomMenuBar
    public CustomMenuBar(SearchFrame searchFrame) {    
        frame = searchFrame;
        createMenuBar(searchFrame);
    }
    
    // Getter methods for the print settings    
    public Boolean getFitWidthStatus() {
        return this.printOption1.isSelected();
    }
    
    public Boolean getIncludeDateStatus() {
        return this.printOption2.isSelected();
    }
    
    // Method to create the entire menu bar
    private void createMenuBar(SearchFrame searchFrame) {
        this.setFont(menuFont);
        
        // Create each menu
        JMenu settingsMenu = createSettingsMenu();
        JMenu printMenu = createPrintMenu();
        JMenu tableOptionsMenu = createTableOptionsMenu();
        
        // Add the menus to the menu bar
        //settingsMenu.setFont(menuFont);              
        //printMenu.setFont(menuFont);              
        //tableOptionsMenu.setFont(menuFont);

        this.add(printMenu);
        this.add(settingsMenu);
        this.add(tableOptionsMenu);
    }
    
    // Method to create the settings menu with dark mode toggle
    private JMenu createSettingsMenu() {
        JMenu settingsMenu = new JMenu("Settings");
        settingsMenu.setFont(menuFont);

        // Create a menu item for dark mode
        JCheckBoxMenuItem darkModeMenuItem = new JCheckBoxMenuItem("Dark Mode");
        darkModeMenuItem.setFont(menuFont);

        // Add the dark mode menu item to the settings menu
        settingsMenu.add(darkModeMenuItem);
        
        // Create a listener to change colors based on dark mode selection
        ColorListener changeColors = new ColorListener(frame, this);
        darkModeMenuItem.addActionListener(changeColors);

        return settingsMenu;
    }

    // Method to create the print menu
    private JMenu createPrintMenu() {
        JMenu printMenu = new JMenu("Print");
        printMenu.setFont(menuFont);
        
        // Create sub-menu for print settings
        JMenu subMenu = new JMenu("Print Settings");
        subMenu.setFont(menuFont);

        // Create a menu item for printing the table
        JMenuItem printMenuButton = new JMenuItem("Print Table");
        printMenuButton.setFont(menuFont);

        // Configure the print settings menu items
        printOption1.setFont(menuFont);
        printOption1.setSelected(true);
        printOption2.setFont(menuFont);

        // Add menu items to the print menu
        printMenu.add(printMenuButton);
        subMenu.add(printOption1);
        subMenu.add(printOption2);
        printMenu.add(subMenu);

        // Add a listener to handle the print action
        printMenuButton.addActionListener(new PrintButtonListener(this, frame));

        return printMenu;
    }

    // Method to create the table options menu
    private JMenu createTableOptionsMenu() {
        JMenu tableOptionsMenu = new JMenu("Table Options");
        tableOptionsMenu.setFont(menuFont);

        toggleHorizontalAlignment.setFont(menuFont);
        tableOptionsMenu.add(toggleHorizontalAlignment);
        toggleHorizontalAlignment.addActionListener(new ActionListener() {
            // Toggle the horizontal alignment of the table
            @Override
            public void actionPerformed(ActionEvent e) {
                ResultPanel resultPanel = (ResultPanel) frame.resultPanel;
                TableCellRenderer renderer = resultPanel.resultTable.getCellRenderer(0, 0);
                DefaultTableCellRenderer dtcr = (DefaultTableCellRenderer) renderer;
                if (dtcr.getHorizontalAlignment() == SwingConstants.CENTER) {
                    dtcr.setHorizontalAlignment(SwingConstants.LEFT);
                } else {
                    dtcr.setHorizontalAlignment(SwingConstants.CENTER);
                }
                resultPanel.resultTable.repaint();
            }
        });

        toggleHorizontalLines.setFont(menuFont);
        tableOptionsMenu.add(toggleHorizontalLines);
        toggleHorizontalLines.addActionListener(new ActionListener() {
            // Toggle the visibility of horizontal lines in the table
            @Override
            public void actionPerformed(ActionEvent e) {
                ResultPanel resultPanel = (ResultPanel) frame.resultPanel;
                boolean showLines = !resultPanel.resultTable.getShowHorizontalLines();
                resultPanel.resultTable.setShowHorizontalLines(showLines);
            }
        });
        
        toggleVerticalLines.setFont(menuFont);
        tableOptionsMenu.add(toggleVerticalLines);
        toggleVerticalLines.addActionListener(new ActionListener() {
            // Toggle the visibility of vertical lines in the table
            @Override
            public void actionPerformed(ActionEvent e) {
                ResultPanel resultPanel = (ResultPanel) frame.resultPanel;
                boolean showLines = !resultPanel.resultTable.getShowVerticalLines();
                resultPanel.resultTable.setShowVerticalLines(showLines);
            }
        });
        return tableOptionsMenu;
    }
    

}

//NonClosingCheckBoxMenuItem class to create menu items that do not close the menu when clicked
class NonClosingCheckBoxMenuItem extends JCheckBoxMenuItem {
    public NonClosingCheckBoxMenuItem(String inputString) {
        super(inputString);
    }

    @Override
    protected void processMouseEvent(MouseEvent e) {
        if (e.getID() == MouseEvent.MOUSE_RELEASED && contains(e.getPoint())) {
            doClick();
            setArmed(true);
        } else {
            MenuSelectionManager.defaultManager().processMouseEvent(e);
        }
    }
}
