package task2.dao.factory;

import task2.dao.dao.ClientDAO;
import task2.dao.dao.EquipmentDAO;
import task2.dao.dao.RentedEquipmentDAO;
import task2.dao.implementation.ClientDAOImpl;
import task2.dao.implementation.EquipmentDAOImpl;
import task2.dao.implementation.RentedEquipmentDAOImpl;

public class DAOFactory {
	
	private static final DAOFactory daoFactory = new DAOFactory();
	 
	private DAOFactory(){
		
	}
	
	private ClientDAO clientDAO = new ClientDAOImpl();
	private EquipmentDAO equipmentDAO = new EquipmentDAOImpl();
	private RentedEquipmentDAO rentedEquipmentDAO = new RentedEquipmentDAOImpl();  
	
	public ClientDAO getClientDAO(){
		return clientDAO;
	}
	
	public EquipmentDAO getEquipmentDAO(){
		return equipmentDAO;
	}
	
	public RentedEquipmentDAO getRentedEquipmentDAO(){
		return rentedEquipmentDAO;
	}
	
	public static DAOFactory getInstance(){
		return daoFactory;
	}

}
