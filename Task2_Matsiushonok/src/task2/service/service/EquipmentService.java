package task2.service.service;

import java.util.List;

import task2.bean.Equipment;
import task2.service.exception.ServiceException;

public interface EquipmentService {
	
	void addEquipment(Equipment equipment) throws ServiceException;
	Equipment findById(int id)  throws ServiceException;
	void updateEquipment(Equipment equipment)  throws ServiceException;
	List<Equipment> allEquipments()  throws ServiceException;
	List<Equipment> findByCategory(String category) throws ServiceException;
	Equipment getEquipmentByTitle(String title) throws ServiceException;
	
}
