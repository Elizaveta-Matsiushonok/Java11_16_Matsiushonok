package task2.dao.dao;

import java.util.List;

import task2.bean.Client;
import task2.dao.exception.DAOException;

public interface ClientDAO {
	
	void addClient(Client client) throws DAOException;
	Client singIn(String login, String password) throws DAOException;
	Client findById(int id) throws DAOException;
	List<Client> allClients() throws DAOException;

}
