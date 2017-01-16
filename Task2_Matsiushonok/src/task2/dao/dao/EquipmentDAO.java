package task2.dao.dao;

import java.util.List;

import task2.bean.Equipment;
import task2.dao.exception.DAOException;


public interface EquipmentDAO {

	void addEquipment(Equipment equipment) throws DAOException;
	Equipment findById(int id)  throws DAOException;
	void updateEquipment(Equipment equipment)  throws DAOException;
	List<Equipment> allEquipments()  throws DAOException;
	List<Equipment> findByCategory(String category)  throws DAOException;
	Equipment getEquipmentByTitle(String title)  throws DAOException;
	
}
