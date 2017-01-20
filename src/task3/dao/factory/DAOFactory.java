package task3.dao.factory;

import task3.dao.dao.DAOLexer;
import task3.dao.impl.DAOLexerImpl;

public class DAOFactory {
	
	private static DAOFactory daoFactory = new DAOFactory();
	
	private DAOFactory(){
		
	}
	
	private static DAOLexer daoLexer = new DAOLexerImpl();
	
	public static DAOFactory getInstance(){
		return daoFactory;
	}
	
	public DAOLexer getDAOLexer(){
		return daoLexer;
	}
	

}
