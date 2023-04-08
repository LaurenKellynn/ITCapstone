

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.sql.SQLException;
import com.sun.net.httpserver.HttpServer;
import com.mysql.cj.xdevapi.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;


public class TestConn {
	
    public static void main(String[] args) throws IOException {
    	
    	HttpServer server = HttpServer.create(new InetSocketAddress(8082), 0);
    	
    	
    	server.createContext("/api/getOrderID", (exchange -> {
        	exchange.getResponseHeaders().add("Access-Control-Allow-Origin", "*");
            if ("GET".equals(exchange.getRequestMethod())) {
            	int order_Id = querydb();
                String responseText = "" + order_Id;           	
                exchange.sendResponseHeaders(200, responseText.getBytes().length);
                OutputStream output = exchange.getResponseBody();
                output.write(responseText.getBytes());
                output.flush();
            } else {
                exchange.sendResponseHeaders(405, -1);// 405 Method Not Allowed
                server.stop(0);
            }
            exchange.close();
        }));

        server.setExecutor(null); // creates a default executor
        server.start();

    }

    static int querydb() {
    	
    	int order_id = 0;
    	
    	try {
    		
    		// db parameters for mysql database    		
    		
    		String url="jdbc:mysql://itcapstonegroup3.mysql.database.azure.com:3306/db_group3?useSSL=true";
    		Connection myDbConn = DriverManager.getConnection(url, "ITCapstone3", "Group3isthebest!");

    		System.out.println("Got jdbc Connection: " + myDbConn);
    		
    		java.sql.Statement statement = myDbConn.createStatement();
    		
    	    String sql = "SELECT *" +
                     "FROM order_items ";
    	    
    	    ResultSet rs = statement.executeQuery(sql);
    	    
    	    while (rs.next()) {
    	    	   order_id = rs.getInt("order_item_id");
    	    	   //System.out.println(rs.getInt("order_id") + "\t");
    	    	                                   
    	    }
    	    
		} catch(SQLException e) {
			System.out.println(e.getMessage());
		}
		return order_id;
    }
}
