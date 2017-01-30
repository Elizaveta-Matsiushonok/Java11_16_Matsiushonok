package task2.dao.implementation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import task2.bean.Client;
import task2.dao.dao.ClientDAO;
import task2.dao.exception.DAOException;
import task2.source.ConnectorFactory;

public class ClientDAOImpl implements ClientDAO {

	@Override
	public void addClient(Client client) throws DAOException {
		Connection connection = ConnectorFactory.getInstance().getConnection();
		PreparedStatement preparedStatement = null;

		try {
			preparedStatement = connection.prepareStatement(
					"INSERT INTO client (login,password, name, surname, patronymic) VALUES (?,?,?,?,?)");// константные строки именуются
			// и их лучше выносить в public static final поля
			preparedStatement.setString(1, client.getLogin());
			preparedStatement.setString(2, client.getPassword());
			preparedStatement.setString(3, client.getName());
			preparedStatement.setString(4, client.getSurname());
			preparedStatement.setString(5, client.getPatronymic());
			preparedStatement.execute();
		} catch (SQLException e) {
			throw new DAOException("ERROR: Error in adding new client");// потеряла исходное исключение, и в логе его не обнаружится
		} finally {
			try {
				if (preparedStatement != null) {
					preparedStatement.close();
				}
			} catch (SQLException e) {
				System.out.println("ERROR:  Error in closing preparedStatment");// все же  лучше System.err - если пока не пользуемся логгером
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
	public Client findById(int id) throws DAOException {
		Client client = null;

		Connection connection = ConnectorFactory.getInstance().getConnection();
		Statement statement = null;
		ResultSet resultSet = null;

		try {
			statement = (Statement) connection.createStatement();
			resultSet = statement.executeQuery("SELECT * FROM client WHERE id_client='" + id + "'");
			if (resultSet.next()) {
				client = createClient(resultSet);
			}
		} catch (SQLException e1) {
			throw new DAOException("ERROR: Can not find such client");
		}
		finally {
			try {
				if (statement != null) {
					statement.close();
				}
			} catch (SQLException e) {
				System.out.println("ERROR: Error in closing statment");
			}
			try {
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				System.out.println("ERROR: Error in closing connection");
			}
		}
		return client;
	}

	@Override
	public List<Client> allClients() throws DAOException {
		ArrayList<Client> clients = new ArrayList<>();

		Connection connection = ConnectorFactory.getInstance().getConnection();
		Statement statement = null;
		ResultSet resultSet = null;
		try {
			statement = (Statement) connection.createStatement();
			resultSet = statement.executeQuery("SELECT * FROM client");
			while (resultSet.next()) {
				clients.add(createClient(resultSet));
			}
		} catch (SQLException e) {
			throw new DAOException("ERROR: Can not get all clients");
		} finally {
			try {
				if (statement != null) {
					statement.close();
				}
			} catch (SQLException e) {
				System.out.println("ERROR: Error in closing statment");
			}
			try {
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				System.out.println("ERROR: Error in closing connection");
			}
		}
		return clients;
	}

	private Client createClient(ResultSet result) throws SQLException {

		Client client = new Client();

		client.setLogin("login");
		client.setPassword("password");
		client.setName(result.getString("name"));
		client.setSurname(result.getString("surname"));
		client.setPatronymic(result.getString("patronymic"));
		client.setId(result.getInt("id_client"));
		return client;
	}

	@Override
	public Client singIn(String login, String password) throws DAOException {

		Connection connection = ConnectorFactory.getInstance().getConnection();
		Statement statement = null;
		ResultSet resultSet = null;
		Client client = null;

		try {
			statement = (Statement) connection.createStatement();
			resultSet = statement.executeQuery(
					"SELECT id_client, name, surname, patronymic FROM client WHERE login='" + login
							+ "' && password='" + password + "'");
			if (resultSet.next()) {
				client = createClient(resultSet);
				return client;
			}
		} catch (SQLException e) {
			throw new DAOException("ERROR: Can not find such client");
		} finally {
			try {
				if (statement != null) {
					statement.close();
				}
			} catch (SQLException e) {
				System.out.println("ERROR: Error in closing Statment");
			}
			try {
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				System.out.println("ERROR: Error in closing connection");
			}
		}
		return client;
	}

}
