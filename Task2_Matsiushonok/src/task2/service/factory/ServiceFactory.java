package task2.service.factory;

import task2.service.impl.ClientServiceImpl;
import task2.service.impl.EquipmentServiceImpl;
import task2.service.impl.RentedEquipmentServiceImpl;
import task2.service.service.ClientService;
import task2.service.service.EquipmentService;
import task2.service.service.RentedEquipmentService;

public class ServiceFactory {
	private static final ServiceFactory serviceFactory = new ServiceFactory();
	 
	private ServiceFactory(){
		
	}
	
	private ClientService clientService = new ClientServiceImpl();
	private EquipmentService equipmentDAO = new EquipmentServiceImpl();
	private RentedEquipmentService rentedEquipmentService = new RentedEquipmentServiceImpl();  
	
	public ClientService getClientService(){
		return clientService;
	}
	
	public EquipmentService getEquipmentService(){
		return equipmentDAO;
	}
	
	public RentedEquipmentService getRentedEquipmentService(){
		return rentedEquipmentService;
	}
	
	public static ServiceFactory getInstance(){
		return serviceFactory;
	}
}
