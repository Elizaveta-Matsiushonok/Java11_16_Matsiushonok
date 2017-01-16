package task2.service.service;

import java.util.List;

import task2.bean.Client;
import task2.service.exception.ServiceException;

public interface ClientService {
	
	void addClient(Client client) throws ServiceException;
	Client singIn(String login, String password) throws ServiceException;
	Client findById(int id) throws ServiceException;
	List<Client> allClients() throws ServiceException;

}
