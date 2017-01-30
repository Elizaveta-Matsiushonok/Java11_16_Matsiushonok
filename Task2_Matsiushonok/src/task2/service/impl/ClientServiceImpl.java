package task2.service.impl;

import java.util.List;

import task2.bean.Client;
import task2.dao.dao.ClientDAO;
import task2.dao.exception.DAOException;
import task2.dao.factory.DAOFactory;
import task2.service.exception.ServiceException;
import task2.service.service.ClientService;

public class ClientServiceImpl implements ClientService {

	@Override
	public void addClient(Client client) throws ServiceException {
		
		if(client == null){
			throw new ServiceException("ERROR: There is no client!");
		}// а что, валидировать мы должны только сам объект
		// а данные внутри объекта?
		
		try {
			DAOFactory daoFactory = DAOFactory.getInstance();
			ClientDAO clientDAO = (ClientDAO) daoFactory.getClientDAO();
			clientDAO.addClient(client);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public Client findById(int id) throws ServiceException {
		
		Client client = null;
		
		if(id == 0 ){
			throw new ServiceException("ERROR: Client id can not be equals to 0.");
		}
		
		try {
		DAOFactory daoFactory = DAOFactory.getInstance();
		ClientDAO clientDAO = (ClientDAO) daoFactory.getClientDAO();
		client = clientDAO.findById(id);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
		
		return client;
	}

	@Override
	public List<Client> allClients() throws ServiceException {
		try {
		DAOFactory daoFactory = DAOFactory.getInstance();
		ClientDAO clientDAO = (ClientDAO) daoFactory.getClientDAO();
			return clientDAO.allClients();
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}


	@Override
	public Client singIn(String login, String password) throws ServiceException {
		Client client = null;
		if(login == null || login.isEmpty()){
			throw new ServiceException("ERROR: Incorrect login");
		}
		else if(password == null || password.isEmpty()){
			throw new ServiceException("ERROR: Incorrect password");
		}
		try {
			DAOFactory daoFactory = DAOFactory.getInstance();
			ClientDAO clientDAO = (ClientDAO) daoFactory.getClientDAO();
			client = clientDAO.singIn(login, password);
			
			} catch (DAOException e) {
				throw new ServiceException(e);
			}
		return client;
	}


}
