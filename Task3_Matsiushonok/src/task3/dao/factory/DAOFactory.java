package task3.dao.factory;

import task3.dao.dao.DAOLexer;
import task3.dao.impl.DAOLexerImpl;

public class DAOFactory {
	
	private static final  DAOFactory daoFactory = new DAOFactory();
	
	private DAOFactory(){
		
	}
	
	public static DAOFactory getInstance(){
		return daoFactory;
	}
	
	private final DAOLexer daoLexer = new DAOLexerImpl(); 
	
	public DAOLexer getDAOLexer(){
		return daoLexer;
	}

}
