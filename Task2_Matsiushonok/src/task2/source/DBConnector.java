package task2.source;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnector {
	
	public DBConnector(){
		
	}
	
	private Connection connection = null;
	public Connection openConnection(){
		try {
			
			Class.forName("com.mysql.jdbc.Driver");
			    connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1/rent_shop", "root", "root");
				return connection;
		
		} catch (ClassNotFoundException e) {
			System.out.println("error in class loader");
		}
		catch (SQLException e) {
               System.out.println("error in connection to db");
		}
		return null;
	}

}
