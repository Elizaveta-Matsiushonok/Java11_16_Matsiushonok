package task2.service.impl;

import java.util.List;

import task2.bean.Equipment;
import task2.dao.dao.EquipmentDAO;
import task2.dao.exception.DAOException;
import task2.dao.factory.DAOFactory;
import task2.service.exception.ServiceException;
import task2.service.service.EquipmentService;

public class EquipmentServiceImpl implements EquipmentService {
	
	@Override
	public void addEquipment(Equipment equipment)  throws ServiceException{
		
		if(equipment == null){
			throw new ServiceException("ERROR: There is no equipment!");
		}
		
		try {
		DAOFactory daoFactory = DAOFactory.getInstance();
		EquipmentDAO equipmentDAO =  daoFactory.getEquipmentDAO();  
		equipmentDAO.addEquipment(equipment);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public Equipment findById(int id)  throws ServiceException{
		
		if(id == 0){
			throw new ServiceException("ERROR: Equipment id con not be equals 0!");
		}
		
		Equipment equipment = null;
		try {
		DAOFactory daoFactory = DAOFactory.getInstance();
		EquipmentDAO equipmentDAO =  daoFactory.getEquipmentDAO();  
	    equipment = equipmentDAO.findById(id);
		}  catch (DAOException e) {
			throw new ServiceException(e);
		}
		return equipment;
	}

	@Override
	public void updateEquipment(Equipment equipment)  throws ServiceException{
		
		if(equipment != null){
			throw new ServiceException("ERROR: There is no equipment!");
		}
		
		try {
		DAOFactory daoFactory = DAOFactory.getInstance();
		EquipmentDAO equipmentDAO =  daoFactory.getEquipmentDAO();  
		equipmentDAO.updateEquipment(equipment);
		}  catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public List<Equipment> allEquipments()  throws ServiceException{
		try {
		DAOFactory daoFactory = DAOFactory.getInstance();
		EquipmentDAO equipmentDAO =  daoFactory.getEquipmentDAO();  
		return equipmentDAO.allEquipments();
		}  catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public List<Equipment> findByCategory(String category)  throws ServiceException{
		
		if(category == null || category.isEmpty()){
			throw new ServiceException("ERROR: category can not be empty!");
		}
		
		try {
		DAOFactory daoFactory = DAOFactory.getInstance();
		EquipmentDAO equipmentDAO =  daoFactory.getEquipmentDAO();  
		return equipmentDAO.findByCategory(category);
		}  catch (DAOException e) {
			throw new ServiceException(e);
		}
		
	}

	@Override
	public Equipment getEquipmentByTitle(String title)  throws ServiceException{
		
		if(title == null || title.isEmpty()){
			throw new ServiceException("ERROR: title can not be empty!");
		}
		
		try {
		DAOFactory daoFactory = DAOFactory.getInstance();
		EquipmentDAO equipmentDAO =  daoFactory.getEquipmentDAO();  
		return equipmentDAO.getEquipmentByTitle(title);
		}  catch (DAOException e) {
			throw new ServiceException(e);
		}
	}


	

}
