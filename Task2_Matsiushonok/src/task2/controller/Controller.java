package task2.controller;

import task2.controller.command.Command;

public class Controller {
	
	private final CommandProvider provider = new CommandProvider();
	
	public String executeRequest(String request){
		String commandName;
		Command executionCommand;
		
		commandName = request.substring(0, request.indexOf(" "));
		executionCommand = provider.getCommand(commandName);
		
		String response;
		response = executionCommand.execute(request);
		
		return response;
	}
	

}
