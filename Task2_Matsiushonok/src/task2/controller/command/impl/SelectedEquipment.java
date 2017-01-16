package task2.controller.command.impl;

import task2.bean.Equipment;
import task2.controller.command.Command;
import task2.service.exception.ServiceException;
import task2.service.factory.ServiceFactory;
import task2.service.service.EquipmentService;

public class SelectedEquipment implements Command{

	@Override
	public String execute(String request) {      //request состоит из команды SELECTED_EQUIPMENT и введенного equipment
		ServiceFactory serviceFactory = ServiceFactory.getInstance();

		EquipmentService equipmentService = serviceFactory.getEquipmentService();

		String response = null;
		String title = request.substring(request.indexOf(" ") + 1, request.length()); 

		Equipment selectedEquipment = null;
		try {
			selectedEquipment = equipmentService.getEquipmentByTitle(title);
			if(selectedEquipment != null){
			response = Integer.toString(selectedEquipment.getId());
			}
			else{
				response = "There is no such equipment.";
			}
		} catch (ServiceException e) {
			response = "Can not get this equipment.";
		}

		return response;
	}

}
