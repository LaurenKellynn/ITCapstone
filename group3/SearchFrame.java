/**
 * Programmer name: Emma Rawstron
 * Date: 2/6/2022
 * IT Capstone Project: Query Application
 * 
 * The Frame will be displayed when the application first starts.
 * The Frame will display buttons with two different query options:
 * 		1. Display information for all customers
 * 		2. Display order history for one customer (with text input field for customer name)
 * 
 * The Frame will use a BoxLayout with the search panel above, and the result panel below
 * The searchPanel will have components placed in a GridBagLayout
 * The resultPanel will be created from the ResultPanel class using GridBagLayout
 */

package group3;

import java.awt.*;
import javax.swing.*;

public class SearchFrame extends JFrame {
    JLabel searchAllLabel;
    JButton allUsersButton;

    JLabel userNameLabel;
    JTextField userName;
    JButton orderHistoryButton;

    JPanel searchPanel;
    JPanel resultPanel;

    public SearchFrame() {
        super();
        init();
    }

    private void init() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.setLayout(new BorderLayout());// Set the layout for the frame

        this.setTitle("Five Star Clothing Query"); // Set the title

        placeComponents(searchPanel = new JPanel());
        resultPanel = new ResultPanel(); 

        this.add(searchPanel, BorderLayout.PAGE_START);// Add panels to the frame
        this.add(resultPanel, BorderLayout.CENTER);

        // Add button listeners
        orderHistoryButton.addActionListener(new ButtonListener(searchPanel, resultPanel, this));
        allUsersButton.addActionListener(new ButtonListener(searchPanel, resultPanel, this));

        // Set the frame size and location
        this.setMinimumSize(new Dimension(640, 300));
        //this.setPreferredSize(new Dimension(640, 650));
        setFrameBounds(this.getMinimumSize());
    }
    
    //Sets the layout for components on the search panel
    private void placeComponents(Container panel) {          
        panel.setLayout(new GridBagLayout()); // Set search panel layout
        GridBagConstraints gbc = new GridBagConstraints();

        searchAllLabel = new JLabel("Show all customers: ");
        searchAllLabel.setLabelFor(allUsersButton);
        gbc.anchor = GridBagConstraints.EAST;
        // gbc.fill = GridBagConstraints.BOTH;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.weightx = 0.5;
        gbc.weighty = 0;
        gbc.insets = new Insets(20, 0, 15, 0); // top, left, bottom, right
        panel.add(searchAllLabel, gbc);

        allUsersButton = new JButton("Search all");
        allUsersButton.setToolTipText("Display information for all customers in the database");
        gbc.anchor = GridBagConstraints.WEST;
        // gbc.fill = GridBagConstraints.BOTH;
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.weightx = 0.5;
        gbc.weighty = 0;
        gbc.insets = new Insets(20, 0, 15, 0); // top, left, bottom, right
        panel.add(allUsersButton, gbc);

        userNameLabel = new JLabel("Enter customer name: ");
        gbc.anchor = GridBagConstraints.EAST;
        // gbc.fill = GridBagConstraints.BOTH;
        gbc.gridx = 3;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.weightx = 0.5;
        gbc.weighty = 0;
        gbc.insets = new Insets(20, 0, 15, 0); // top, left, bottom, right
        panel.add(userNameLabel, gbc);

        userName = new JTextField(12);
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.gridx = 4;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.weightx = 0.5;
        gbc.weighty = 0;
        gbc.insets = new Insets(20, 0, 15, 0); // top, left, bottom, right
        panel.add(userName, gbc);

        orderHistoryButton = new JButton("Search");
        orderHistoryButton.setToolTipText("Display all order history for customer");
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.NONE;
        gbc.gridx = 5;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.weightx = 0.5;
        gbc.weighty = 0;
        gbc.insets = new Insets(20, 0, 15, 0); // top, left, bottom, right
        panel.add(orderHistoryButton, gbc);
    }
    
    //Set initial frame size and location
    public void setFrameBounds(Dimension dimension) {              
        int frameWidth = dimension.width;
        int frameHeight = dimension.height;

        final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        this.setBounds((int) (screenSize.getWidth() / 2) - (frameWidth / 2),
                (int) (screenSize.getHeight() / 2) - frameHeight, frameWidth, frameHeight);
    }
    
    //Resize the frame only if smaller than preferred size
    public void resetFrameBounds(Dimension dimension, Point point) {          
        int frameWidth = this.getWidth();   
        int frameHeight = this.getHeight();
        
        if (frameWidth < dimension.width) {
            frameWidth = dimension.width;
            
        }      
        if (frameHeight < dimension.height) {
            frameHeight = dimension.height;
        }
        int y = point.y;
        int x = point.x;
        this.setBounds(x, y, frameWidth, frameHeight);
    }

}
