/**
 * Programmer name: Emma Rawstron
 * Date: 3/7/2023
 * IT Capstone Project: Query Application
 */

package group3;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * The DatabaseHandler class is responsible for handling all database operations
 * including executing queries and returning result sets.
 */
public class DatabaseHandler {
    
    /**
     * This method executes a query to retrieve all customer information from the user_login table.
     *
     * @return A ResultSet containing all customer information
     * @throws SQLException If an error occurs while executing the query
     */
    public ResultSet getAllCustomerInfo() throws SQLException {
        String query = "SELECT user_id, username, email FROM user_login";
        Connection connection = DBUtility.getConnection();
        Statement stmt = connection.createStatement();
        return stmt.executeQuery(query);
    }
   
    /**
     * This method executes a query to retrieve the order history for a specific user by username.
     *
     * @param username The username of the user whose order history to retrieve
     * @return A ResultSet containing the order history for the specified user
     * @throws SQLException If an error occurs while executing the query
     */
    public ResultSet getOrderHistory(String username) throws SQLException {
        String query = "SELECT oi.product_id, p.name, oi.quantity,"
                + " p.price, DATE_FORMAT(o.order_date, '%M %d, %Y') as order_date"
                + " FROM user_login u"
                + " JOIN orders o ON u.user_id = o.user_id"
                + " JOIN order_items oi ON o.order_id = oi.order_id"
                + " JOIN products p ON oi.product_id = p.product_id"
                + " WHERE u.username =?;";

        Connection connection = DBUtility.getConnection();
        PreparedStatement pstmt = connection.prepareStatement(query);
        pstmt.setString(1, username);
        return pstmt.executeQuery();
    }
}
