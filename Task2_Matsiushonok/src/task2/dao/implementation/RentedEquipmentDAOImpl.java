package task2.dao.implementation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import task2.bean.Equipment;
import task2.bean.RentedEquipment;
import task2.dao.dao.EquipmentDAO;
import task2.dao.dao.RentedEquipmentDAO;
import task2.dao.exception.DAOException;
import task2.dao.factory.DAOFactory;
import task2.source.ConnectorFactory;

public class RentedEquipmentDAOImpl implements RentedEquipmentDAO {

	@Override
	public void addRentedEquipment(RentedEquipment rentedEquipment)  throws DAOException{
		Connection connection = ConnectorFactory.getInstance().getConnection();
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = connection.prepareStatement(
					"INSERT INTO rented_equipment (id_client, id_equipment, date_from, date_to, total_price)" + " VALUES (?,?,?,?,?)");
			preparedStatement.setInt(1, rentedEquipment.getIdClient());
			preparedStatement.setInt(2, rentedEquipment.getIdEquipment());
			preparedStatement.setDate(3,  rentedEquipment.getDateFrom());
			preparedStatement.setDate(4, rentedEquipment.getDateTo());
			preparedStatement.setDouble(5, rentedEquipment.getTotalPrice());
			preparedStatement.execute();
		} catch (SQLException e) {
			throw new DAOException("ERROR: Can not add this rented equipment");
		}
		finally {
			try {
				if (preparedStatement != null) {
					preparedStatement.close();
				}
			} catch (SQLException e) {
				System.out.println("ERROR: Error in closing preparedStatment");
			}
			try {
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				System.out.println("ERROR: Error in closing connection");
			}
		}
	}

	@Override
	public void deleteRentedEquipment(int equipmentId) throws DAOException{
		Connection connection = ConnectorFactory.getInstance().getConnection();
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = connection.prepareStatement("DELETE FROM rented_equipment WHERE id_rented_equipment = ?");
			preparedStatement.setInt(1, equipmentId);
            preparedStatement.execute();
		} catch (SQLException e) {
			throw new DAOException("ERROR: Can not delete this rented equipment");
		}

		finally {
			try {
				if (preparedStatement != null) {
					preparedStatement.close();
				}
			} catch (SQLException e) {
				System.out.println("ERROR: Error in closing preparedStatment");
			}
			try {
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				System.out.println("ERROR: Error in closing connection");
			}
		}
		
	}

	@Override
	public List<RentedEquipment> allEquipments() throws DAOException{
		
		ArrayList<RentedEquipment> rentedEquipments = new ArrayList<>();

		Connection connection = ConnectorFactory.getInstance().getConnection();
		Statement statement = null;
		ResultSet resultSet = null;
		try {
			statement = (Statement) connection.createStatement();
			resultSet = statement.executeQuery("SELECT * FROM rented_equipment");
			while (resultSet.next()) {
				rentedEquipments.add(createEquipment(resultSet));
			}
		} catch (SQLException e) {
			throw new DAOException("ERROR: Can not get all equipments");
		} finally {
			try {
				if (statement != null) {
					statement.close();
				}
			} catch (SQLException e) {
				System.out.println("ERROR: Error in closing preparedStatment");
			}
			try {
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				System.out.println("ERROR: Error in closing connection");
			}
		}
		return rentedEquipments;
	}
	private RentedEquipment createEquipment(ResultSet result) throws SQLException{

		RentedEquipment equipment = new RentedEquipment();
		
		equipment.setId(result.getInt("id_rented_equipment"));
		equipment.setIdClient(result.getInt("id_client"));
		equipment.setIdEquipment(result.getInt("id_equipment"));
		equipment.setDateFrom(result.getDate("date_from"));
		equipment.setDateTo(result.getDate("date_to"));
		equipment.setTotalPrice(result.getDouble("total_price"));

		return equipment;
	}

	@Override
	public Equipment rentEquipment(int clientId, int equipmentId, String date) throws DAOException{
        DAOFactory daoFactory = DAOFactory.getInstance();
		EquipmentDAO equipmentDAO =  daoFactory.getEquipmentDAO();  
		
		Equipment equipment  = null;
		
		try {
			equipment = equipmentDAO.findById(equipmentId);
		} catch (DAOException e) {
			throw new DAOException(e);
		}
		
		RentedEquipment rentedEquipment = new RentedEquipment();
		
		rentedEquipment.setIdClient(clientId);
		rentedEquipment.setIdEquipment(equipmentId);
	   
	    Date dateFrom = new Date();
		rentedEquipment.setDateFrom(new java.sql.Date(dateFrom.getTime()));
		rentedEquipment.setDateTo(java.sql.Date.valueOf(date));
		rentedEquipment.setTotalPrice(equipment.getPrice());
		addRentedEquipment(rentedEquipment);
		
		return equipment;
	}

	@Override
	public Map<RentedEquipment, Equipment> rentedEquipments(int clientId) throws DAOException {
		Map<RentedEquipment, Equipment> rentedEquipments = new HashMap<>();
		
		DAOFactory daoFactory = DAOFactory.getInstance();	
		EquipmentDAO equipmentDAO =  daoFactory.getEquipmentDAO();  
		
		Connection connection = ConnectorFactory.getInstance().getConnection();
		Statement statement = null;
		ResultSet resultSet = null;
		
		RentedEquipment rentedEquipment = null;
		
		try {
			statement = (Statement) connection.createStatement();
			resultSet = statement.executeQuery("SELECT * FROM rented_equipment WHERE id_client='" + clientId + "'");
			while (resultSet.next()) {
				rentedEquipment = createEquipment(resultSet);
				rentedEquipments.put(rentedEquipment, equipmentDAO.findById(rentedEquipment.getIdEquipment()));
			}
		} catch (SQLException e) {
			throw new DAOException("ERROR: Can not get all equipments");
		} finally {
			try {
				if (statement != null) {
					statement.close();
				}
			} catch (SQLException e) {
				System.out.println("ERROR: Error in closing preparedStatment");
			}
			try {
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				System.out.println("ERROR: Error in closing connection");
			}
		}
		return rentedEquipments;
	}
	
}
