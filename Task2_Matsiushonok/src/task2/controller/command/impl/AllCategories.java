package task2.controller.command.impl;

import java.util.List;

import task2.bean.Equipment;
import task2.controller.command.Command;
import task2.service.exception.ServiceException;
import task2.service.factory.ServiceFactory;
import task2.service.service.EquipmentService;

public class AllCategories implements Command {

	@Override
	public String execute(String request) {   ///request состоит из команды ALL_CATEGORIES
		
		ServiceFactory serviceFactory = ServiceFactory.getInstance();

		EquipmentService equipmentService = serviceFactory.getEquipmentService();

		String response = null;

		StringBuilder categories = new StringBuilder();

		List<Equipment> equipments = null;
		try {
			equipments = equipmentService.allEquipments();
		} catch (ServiceException e) {
			response = "Can not get all categories.";
		}
		
		for (Equipment equipment : equipments) {    ///из коллекции всех equipment формируется ответ в виде строки
			categories.append(equipment.getCategory() + "\n");
		}
		
		response = categories.toString();
		
		return response;
	}

}
