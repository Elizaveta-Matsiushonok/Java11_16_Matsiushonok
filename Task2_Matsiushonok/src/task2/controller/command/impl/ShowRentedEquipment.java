package task2.controller.command.impl;

import java.util.HashMap;
import java.util.Map;

import task2.bean.Equipment;
import task2.bean.RentedEquipment;
import task2.controller.command.Command;
import task2.service.exception.ServiceException;
import task2.service.factory.ServiceFactory;
import task2.service.service.RentedEquipmentService;

public class ShowRentedEquipment implements Command {

	@Override
	public String execute(String request) {  //request состоит из команды SHOW_RENTED_EQUIPMENT и id клиента
		
		ServiceFactory serviceFactory = ServiceFactory.getInstance();

		RentedEquipmentService equipmentService = serviceFactory.getRentedEquipmentService();

		String response = null;
		int clientId = Integer.parseInt(request.substring(request.indexOf(" ") + 1, request.length())); 
		StringBuilder equipmentToString = new StringBuilder();

		Map<RentedEquipment, Equipment> rentedEquipments = new HashMap<>();
		
		try {
			rentedEquipments = equipmentService.rentedEquipments(clientId);
		} catch (ServiceException e) {
			response = "Can not get all equipments by this id.";
		}
		//формируется map из арендованнвый equipment и информации об этих equipment
		for(Map.Entry<RentedEquipment, Equipment> equipment : rentedEquipments.entrySet()){  
			
			equipmentToString.append(equipment.getKey() + " " + equipment.getValue() + "\n");
		}
		
		response = equipmentToString.toString();
		
		return response;
	}
	

}
