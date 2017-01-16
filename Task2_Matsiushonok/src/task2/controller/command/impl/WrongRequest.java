package task2.controller.command.impl;

import task2.controller.command.Command;

public class WrongRequest implements Command{

	@Override
	public String execute(String request) {
		return "You have entered incorrect information";
	}

}
