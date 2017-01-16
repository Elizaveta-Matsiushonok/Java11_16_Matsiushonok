package task2.service.service;

import java.util.List;
import java.util.Map;

import task2.bean.Equipment;
import task2.bean.RentedEquipment;
import task2.service.exception.ServiceException;

public interface RentedEquipmentService {
	
	void addRentedEquipment(RentedEquipment rentedEquipment) throws ServiceException;
	void deleteRentedEquipment(int equipmentId) throws ServiceException;
	List<RentedEquipment> allEquipments() throws ServiceException;
	Equipment rentEquipment(int clientId, int equipmentId, String date) throws ServiceException;
	Map<RentedEquipment, Equipment> rentedEquipments(int clientId)  throws ServiceException;
}
