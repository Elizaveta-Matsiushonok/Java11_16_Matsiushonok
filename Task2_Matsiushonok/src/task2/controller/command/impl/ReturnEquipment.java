package task2.controller.command.impl;

import task2.controller.command.Command;
import task2.service.exception.ServiceException;
import task2.service.factory.ServiceFactory;
import task2.service.service.RentedEquipmentService;

public class ReturnEquipment implements Command{

	@Override
	public String execute(String request) { //request состоит из RETUEN_EQUIPMENT и id снаряжения
		
		ServiceFactory serviceFactory = ServiceFactory.getInstance();
		RentedEquipmentService equipmentService = serviceFactory.getRentedEquipmentService();
		
		int  equipmentId = Integer.parseInt(request.substring(request.indexOf(" ") + 1, request.length())); 

		String response = null;
		
		try {
		  equipmentService.deleteRentedEquipment(equipmentId);
		  response = "OK, you return it!";
		} catch (ServiceException e) {
			response = "Can not get equipment by this id.";
		}
		
		return response;
	}

}
