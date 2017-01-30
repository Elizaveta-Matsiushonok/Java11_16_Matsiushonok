package task2.controller;

public class ControllerFactory {
	
	private static ControllerFactory controllerFactory = new ControllerFactory();
	
	private ControllerFactory(){
		
	}
	
	Controller controller = new Controller();// следи за расположением кода в классе
	// и где потерялся атрибут доступа?
	
	public Controller getController(){
		return  controller;
	}
	
	public static ControllerFactory getInstance(){
		return controllerFactory;
	}
	
	

}
