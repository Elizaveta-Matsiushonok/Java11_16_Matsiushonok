package task2.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import task2.bean.Equipment;
import task2.bean.RentedEquipment;
import task2.dao.dao.EquipmentDAO;
import task2.dao.dao.RentedEquipmentDAO;
import task2.dao.exception.DAOException;
import task2.dao.factory.DAOFactory;
import task2.service.exception.ServiceException;
import task2.service.service.RentedEquipmentService;

public class RentedEquipmentServiceImpl implements RentedEquipmentService {

	@Override
	public void addRentedEquipment(RentedEquipment rentedEquipment) throws ServiceException{
		
		if(rentedEquipment == null){
			throw new ServiceException();
		}
		
		DAOFactory daoFactory = DAOFactory.getInstance();
		RentedEquipmentDAO rentedEquipmentDAO =  daoFactory.getRentedEquipmentDAO();  
		try {
			rentedEquipmentDAO.addRentedEquipment(rentedEquipment);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
		
	}

	@Override
	public void deleteRentedEquipment(int equipmentId) throws ServiceException{
		
		if(equipmentId == 0){
			throw new ServiceException("ERROR: Equipment id con not be equals 0!");
		}
		
		DAOFactory daoFactory = DAOFactory.getInstance();
		RentedEquipmentDAO rentedEquipmentDAO =  daoFactory.getRentedEquipmentDAO();  
		EquipmentDAO equipmentDAO = daoFactory.getEquipmentDAO();
		Equipment equipment = null;
		
		try {
			rentedEquipmentDAO.deleteRentedEquipment(equipmentId);
			equipment = equipmentDAO.findById(equipmentId);
			equipment.setQuantity(equipment.getQuantity() + 1);
			equipmentDAO.updateEquipment(equipment);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public List<RentedEquipment> allEquipments() throws ServiceException {
		DAOFactory daoFactory = DAOFactory.getInstance();
		RentedEquipmentDAO rentedEquipmentDAO =  daoFactory.getRentedEquipmentDAO();  
		
		try {
			return rentedEquipmentDAO.allEquipments();
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public Equipment rentEquipment(int clientId, int equipmentId, String date) throws ServiceException {
		if( clientId == 0 ||equipmentId == 0 || date == null || date.isEmpty()){
			throw new ServiceException("ERROR: Incorrect clientId, equipmentId or date");
		}
		
		 Date dateFrom = new Date();
		 Date dateTo =  java.sql.Date.valueOf(date);
		 
		 if(dateFrom.after(dateTo)){                //правильная ли дата 
			 throw new ServiceException("incorrect date");
		 }
		
		DAOFactory daoFactory = DAOFactory.getInstance();
		RentedEquipmentDAO rentedEquipmentDAO =  daoFactory.getRentedEquipmentDAO();
		EquipmentDAO equipmentDAO = daoFactory.getEquipmentDAO();
		Equipment equipment = null;
		try {
			equipment = rentedEquipmentDAO.rentEquipment(clientId, equipmentId, date);
			equipment.setQuantity(equipment.getQuantity() - 1);
			equipmentDAO.updateEquipment(equipment);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
		return equipment;
	}

	@Override
	public Map<RentedEquipment, Equipment> rentedEquipments(int clientId) throws ServiceException{
		DAOFactory daoFactory = DAOFactory.getInstance();
		RentedEquipmentDAO rentedEquipmentDAO =  daoFactory.getRentedEquipmentDAO();  
		
		try {
			return rentedEquipmentDAO.rentedEquipments(clientId);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

}
