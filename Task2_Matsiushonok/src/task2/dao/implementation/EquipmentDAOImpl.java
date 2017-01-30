package task2.dao.implementation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import task2.bean.Equipment;
import task2.dao.dao.EquipmentDAO;
import task2.dao.exception.DAOException;
import task2.source.ConnectorFactory;

public class EquipmentDAOImpl implements EquipmentDAO {

	@Override
	public void addEquipment(Equipment equipment) throws DAOException {
		Connection connection = ConnectorFactory.getInstance().getConnection();
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = connection
					.prepareStatement("INSERT INTO equipment (title, price, category, quantity)" + " VALUES (?,?,?,?)");
			preparedStatement.setString(1, equipment.getTitle());
			preparedStatement.setDouble(2, equipment.getPrice());
			preparedStatement.setString(3, equipment.getCategory());
			preparedStatement.setInt(4, equipment.getQuantity());
		} catch (SQLException e) {
			throw new DAOException("ERROR: Can not add equipment");
		} finally {
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
	public Equipment findById(int id) throws DAOException {
		Equipment equipment = null;
		
		Connection connection = ConnectorFactory.getInstance().getConnection();
		Statement statement = null;
		ResultSet resultSet = null;

		try {
			statement = (Statement) connection.createStatement();
			resultSet = statement.executeQuery("SELECT * FROM equipment");
			while (resultSet.next()) {
				equipment = createEquipment(resultSet);
			}
		} catch (SQLException e) {
			throw new DAOException("ERROR: Can not find equipmentby this id");
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
		return equipment;

	}

	@Override
	public void updateEquipment(Equipment equipment) throws DAOException {
		Connection connection = ConnectorFactory.getInstance().getConnection();
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = connection.prepareStatement(
					"UPDATE equipment SET title=?, price=?, category=?, quantity=? WHERE id_equipment='"
							+ equipment.getId() + "'");
			preparedStatement.setString(1, equipment.getTitle());
			preparedStatement.setDouble(2, equipment.getPrice());
			preparedStatement.setString(3, equipment.getCategory());
			preparedStatement.setInt(4, equipment.getQuantity());
			preparedStatement.execute();
		} catch (SQLException e) {
			throw new DAOException("ERROR: Can not update equipment");
		} finally {
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
	public List<Equipment> allEquipments() throws DAOException {
		ArrayList<Equipment> equipments = new ArrayList<>();

		Connection connection = ConnectorFactory.getInstance().getConnection();
		Statement statement = null;
		ResultSet resultSet = null;
		try {
			statement = (Statement) connection.createStatement();
			resultSet = statement.executeQuery("SELECT * FROM equipment");
			while (resultSet.next()) {
				equipments.add(createEquipment(resultSet));
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
		return equipments;
	}

	private Equipment createEquipment(ResultSet result) throws SQLException {

		Equipment equipment = new Equipment();
		equipment.setId(result.getInt("id_equipment"));// что ьы делаем с константными строками?
		equipment.setTitle(result.getString("title"));
		equipment.setPrice(result.getDouble("price"));
		equipment.setCategory(result.getString("category"));
		equipment.setQuantity(result.getInt("quantity"));
		return equipment;
	}

	@Override
	public List<Equipment> findByCategory(String category) throws DAOException {
		ArrayList<Equipment> equipments = new ArrayList<>();

		Connection connection = ConnectorFactory.getInstance().getConnection();
		Statement statement = null;
		ResultSet resultSet = null;
		try {
			statement = (Statement) connection.createStatement();
			resultSet = statement.executeQuery("SELECT * FROM equipment  WHERE category = '" + category + "'");
			while (resultSet.next()) {
				equipments.add(createEquipment(resultSet));
			}
		} catch (SQLException e) {
			throw new DAOException("ERROR: Can not get all equipments on this category");
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
		return equipments;
	}

	@Override
	public Equipment getEquipmentByTitle(String title) throws DAOException {
		Equipment equipment = null;

		Connection connection = ConnectorFactory.getInstance().getConnection();
		Statement statement = null;
		ResultSet resultSet = null;
		try {
			statement = (Statement) connection.createStatement();
			resultSet = statement.executeQuery("SELECT * FROM equipment  WHERE title = '" + title + "'");
			if (resultSet.next()) {
				equipment = createEquipment(resultSet);
			}
		} catch (SQLException e) {
			throw new DAOException("ERROR: Can not get all equipments on this category");
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
		return equipment;
	}

}
