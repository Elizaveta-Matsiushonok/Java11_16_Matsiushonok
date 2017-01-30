package task2.controller.command.impl;

import java.util.List;

import task2.bean.Equipment;
import task2.controller.command.Command;
import task2.service.exception.ServiceException;
import task2.service.factory.ServiceFactory;
import task2.service.service.EquipmentService;

public class AllEquipmentInCategory implements Command{    
	@Override
	public String execute(String request) {     //request состоит из команды ALL_EQUIPMENT_IN_CATEGORY и введенной категории  
		ServiceFactory serviceFactory = ServiceFactory.getInstance();

		EquipmentService equipmentService = serviceFactory.getEquipmentService();

		String response = null;
		String category = request.substring(request.indexOf(" ") + 1, request.length()); 
		StringBuilder equipmentToString = new StringBuilder();

		List<Equipment> equipments = null;
		try {
			equipments = equipmentService.findByCategory(category);
		} catch (ServiceException e) {
			response = "Ñan not get all equipments in this category.";// смотри внимательно на workflow твоего кода
			//ты делаешь ошибки не продумавая, как будет выполнять твой код не только при позитивном сценарии, но и при негативном
		}
		
		for (Equipment equipment : equipments) {
			equipmentToString.append(equipment + "\n");
		}
		
		response = equipmentToString.toString();
		
		return response;
	}
}
