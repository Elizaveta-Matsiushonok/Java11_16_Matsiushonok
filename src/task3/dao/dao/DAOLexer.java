package task3.dao.dao;

import task3.bean.Lexeme;
import task3.dao.exception.DAOException;

public interface DAOLexer {
	  void setFilePath(String file);
      Lexeme getLexeme() throws DAOException;
      boolean hasNextLexeme() throws DAOException;
}
