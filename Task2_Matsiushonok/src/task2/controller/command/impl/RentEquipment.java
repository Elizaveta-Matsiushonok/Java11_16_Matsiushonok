package task2.controller.command.impl;

import task2.controller.command.Command;
import task2.service.exception.ServiceException;
import task2.service.factory.ServiceFactory;
import task2.service.service.RentedEquipmentService;

public class RentEquipment implements Command{

	@Override
	public String execute(String request) { //request состоит из RENT_EQUIPMENT и id клиента, id снаряжения, даты
		int clientId = 0;
		int equipmentId = 0;

		String date = null;
        String response = null;
		String[] args = request.split(" ");

		clientId = Integer.parseInt(args[1]);
		equipmentId = Integer.parseInt(args[2]);
		date = args[3];

		ServiceFactory serviceFactory = ServiceFactory.getInstance();

		RentedEquipmentService rentedEquipmentService = serviceFactory.getRentedEquipmentService();

			try {
				rentedEquipmentService.rentEquipment(clientId, equipmentId, date);
				response = "OK , you have rent it!";
				
			} catch (ServiceException e) {
				response = "Error in renting equipment.";
			}
			
		return response;
	}

}
