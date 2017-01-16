package task2.source;

import java.sql.Connection;

public class ConnectorFactory {
	
	private static final ConnectorFactory connectorFactory = new  ConnectorFactory();
	
	private ConnectorFactory(){
		
	}
	
	private DBConnector connection = new DBConnector();
	
	public Connection getConnection(){
		return connection.openConnection();
	}
	
	public  static ConnectorFactory getInstance(){
		return  connectorFactory;
	}

}
