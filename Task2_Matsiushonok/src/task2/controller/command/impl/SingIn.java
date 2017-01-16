package task2.controller.command.impl;

import task2.bean.Client;
import task2.controller.command.Command;
import task2.service.exception.ServiceException;
import task2.service.factory.ServiceFactory;
import task2.service.service.ClientService;

public class SingIn implements Command {

	@Override
	public String execute(String request) {   //request состоит из команды SING_IN и login, password клиента
		
		String login = null;
		String password = null;

		String response = null;

		Client client = null;

		String[] args = request.split(" ");

		login = args[1];
		password = args[2];

		ServiceFactory serviceFactory = ServiceFactory.getInstance();

		ClientService clientService = serviceFactory.getClientService();

		try {
			client = clientService.singIn(login, password);
			if (client != null) {
				response = "Welcom, " + client.getName() + " " + client.getId();
			} else {
				response = "There is no such client.";
			}
		} catch (ServiceException e) {
			response = "Error during login.";
		}
		return response;

	}

}
