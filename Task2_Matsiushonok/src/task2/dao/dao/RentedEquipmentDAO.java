package task2.dao.dao;

import java.util.List;
import java.util.Map;

import task2.bean.Equipment;
import task2.bean.RentedEquipment;
import task2.dao.exception.DAOException;

public interface RentedEquipmentDAO {
	
	void addRentedEquipment(RentedEquipment rentedEquipment)  throws DAOException;
	void deleteRentedEquipment(int equipmentId)  throws DAOException;
	List<RentedEquipment> allEquipments()  throws DAOException;
	Equipment rentEquipment(int clientId, int equipmentId, String date) throws DAOException;
	Map<RentedEquipment, Equipment> rentedEquipments(int clientId)  throws DAOException;
}
