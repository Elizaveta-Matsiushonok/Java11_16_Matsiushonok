package task2.controller.command.impl;

import task2.bean.Client;
import task2.controller.command.Command;
import task2.service.exception.ServiceException;
import task2.service.factory.ServiceFactory;
import task2.service.service.ClientService;

public class Register implements Command{

	@Override
	public String execute(String request) {    //request состоит из команды REGISTER и login, password, name и тд. клиента
		String name = null;
		String surname = null;
		String patronymic = null;
		String login = null;
		String password = null;

		String response = null;

		Client client = new Client();

		String[] args = request.split(" ");

		name = args[1];
		surname = args[2];
		patronymic = args[3];
		login = args[4];
		password = args[5];
		
		
		client.setName(name);
		client.setSurname(surname);
		client.setPatronymic(patronymic);
		client.setLogin(login);
		client.setPassword(password);
		

		ServiceFactory serviceFactory = ServiceFactory.getInstance();

		ClientService clientService = serviceFactory.getClientService();

		try {
			clientService.addClient(client);
			client = clientService.singIn(login, password);
			if (client != null) {
				response = "Welcom, " + client.getName() + " " + client.getId();
			} else {
				response = "Ñan not add this client.";
			}
		} catch (ServiceException e) {
			response = "Error during registration.";
		}
		return response;


	}

}
