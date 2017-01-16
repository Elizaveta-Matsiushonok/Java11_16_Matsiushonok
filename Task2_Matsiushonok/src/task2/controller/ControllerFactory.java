package task2.controller;

public class ControllerFactory {
	
	private static ControllerFactory controllerFactory = new ControllerFactory();
	
	private ControllerFactory(){
		
	}
	
	Controller controller = new Controller();
	
	public Controller getController(){
		return  controller;
	}
	
	public static ControllerFactory getInstance(){
		return controllerFactory;
	}
	
	

}
