package task2.controller;

import java.util.HashMap;
import java.util.Map;

import task2.controller.command.Command;
import task2.controller.command.impl.AllCategories;
import task2.controller.command.impl.AllEquipmentInCategory;
import task2.controller.command.impl.Register;
import task2.controller.command.impl.RentEquipment;
import task2.controller.command.impl.ReturnEquipment;
import task2.controller.command.impl.SelectedEquipment;
import task2.controller.command.impl.ShowRentedEquipment;
import task2.controller.command.impl.SingIn;
import task2.controller.command.impl.WrongRequest;

public class CommandProvider {

	private final Map<CommandName, Command> repository = new HashMap<>();

	CommandProvider() {// public класс и единственный friendly конструктор, - это зачем так делать?
		repository.put(CommandName.SING_IN, new SingIn());
		repository.put(CommandName.REGISTER, new Register());
		repository.put(CommandName.ALL_CATEGORIES, new AllCategories());
		repository.put(CommandName.ALL_EQUIPMENT_IN_CATEGORY, new AllEquipmentInCategory());
		repository.put(CommandName.SELECTED_EQUIPMENT, new SelectedEquipment());
		repository.put(CommandName.RENT_EQUIPMENT, new RentEquipment());
		repository.put(CommandName.SHOW_RENTED_EQUIPMENT, new ShowRentedEquipment());
		repository.put(CommandName.RETURN_EQUIPMENT, new ReturnEquipment());
		repository.put(CommandName.WRONG_REQUEST, new WrongRequest());
	}

	Command getCommand(String name) {
		CommandName commandName = null;
		Command command = null;
		try {
			commandName = CommandName.valueOf(name.toUpperCase());
			command = repository.get(commandName);
		} catch (IllegalArgumentException | NullPointerException e) {
			command = repository.get(CommandName.WRONG_REQUEST);
		}
		return command;
	}

}
