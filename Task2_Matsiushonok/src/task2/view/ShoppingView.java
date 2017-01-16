package task2.view;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import task2.controller.Controller;
import task2.controller.ControllerFactory;

public class ShoppingView {

	public static void startShoping(int clientId) {
		System.out.println("Select: START_SHOPPING | SHOW_RENTED_EQUIPMENT | RETURN_EQUIPMENT | EXIT... ");

		Scanner sc = new Scanner(System.in);
		String answer = null;

		if (sc.hasNextLine()) {
			answer = sc.nextLine().toUpperCase();

		} else {
			System.out.println("enter your answer");
		}

		switch (answer) {
		case "START_SHOPPING":
			selectingEquipment(clientId);      //переход к выбору equipment
			break;
		case "SHOW_RENTED_EQUIPMENT":         //посмотреть все арендованные вещи
			showRentedItems(clientId); 
			break;
		case "RETURN_EQUIPMENT":
			returnEquipment(clientId);         //сдать арендованую вещь
			break;
		case "EXIT":
			return;
		default:
			System.out.println("incorrect answer");
		}

	}

	public static void selectingEquipment(int clientId) {

		ControllerFactory controllerFactory = ControllerFactory.getInstance();

		Controller controller = controllerFactory.getController();

		System.out.println("Enter category from this list: ");
		System.out.println("_______________________________");

		/*отправляется запрос ALL_CATEGORIES
		 * возвращается строковое представление всех категорий
		  * */
		
		
		System.out.println(controller.executeRequest("ALL_CATEGORIES "));  

		System.out.println("_______________________________");

		Scanner sc = new Scanner(System.in);              //пользователь вводит нужную категорию
		String category = null;

		if (sc.hasNextLine()) {
			category = sc.nextLine();
		}
		
		/*отправляется запрос ALL_EQUIPMENT_IN_CATEGORY вместе с введенной категорией 
		 * возвращается строковое представление всех equipment в выбраной категории
		 * */
		
		System.out.println(controller.executeRequest("ALL_EQUIPMENT_IN_CATEGORY" + " " + category));

		System.out.println("Enter equipment from this list: ");

		String selectedEquipment = null;

		if (sc.hasNextLine()) {                      //пользователь вводит нужный equipment
			selectedEquipment = sc.nextLine();
		}
		
		/*отправляется запрос SELECTED_EQUIPMENT вместе с введенным equipment
		 * возвращается id выбранного equipment
		  * */
		
		int equipmentId = 0;
		
		try {
			equipmentId = Integer.parseInt(controller.executeRequest("SELECTED_EQUIPMENT" + " " + selectedEquipment));
		} catch (NumberFormatException e) {
			System.out.println("Sorry, can not find this equipment :(");
		}
		
		System.out.println("Do you want to rent it?(yes/no)");  //арендовать вещь?

		String rentOrNot = null;

		boolean rightAnswer = false;
		while (!rightAnswer) {
			if (sc.hasNextLine()) {
				rentOrNot = sc.nextLine();
				if (rentOrNot.equals("yes")) {
					rent(clientId, equipmentId);   //если пользователь ответил "yes"
					rightAnswer = true;
				} else if (rentOrNot.equals("no")) {
					rightAnswer = true;
					return;
				} else {
					System.out.println("incorrect answer, try again..");
				}
			}
		}
	}

	public static void rent(int clientId, int equipmentId) {
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");      //пользователь вводит, до какого числа арендует equipment 
		System.out.println("Today is " + dateFormat.format(new Date()));
		System.out.println("Till what date your want rent this equipment?Enter in format 'yyyy-mm-dd...'");

		String date = null;
		Scanner sc = new Scanner(System.in);
		if (sc.hasNextLine()) {
			date = sc.nextLine();
		}
		ControllerFactory controllerFactory = ControllerFactory.getInstance();

		Controller controller = controllerFactory.getController();
		
		/*отправляется запрос RENT_EQUIPMENT вместе с id клиента, id снаряжения, датой
		 * возвращается информация об удачной аренде, либо проблеме возникшей во время аренды
		  * */
		
		
		System.out.println(controller.executeRequest("RENT_EQUIPMENT" + " " + clientId + " " + equipmentId + " " + date));
	}

	public static void showRentedItems(int clientId) {   // клиет захотел посмотреть список арендованный вещей
		ControllerFactory controllerFactory = ControllerFactory.getInstance();

		Controller controller = controllerFactory.getController();
		
		/*отправляется запрос SHOW_RENTED_EQUIPMENT вместе с id клиента
		 * возвращается строковое представление арендованного equipment
		 * */
		
		System.out.println(controller.executeRequest("SHOW_RENTED_EQUIPMENT " + clientId));
	}

	public static void returnEquipment(int clientId) {     //пользователь сдает вещь
		ControllerFactory controllerFactory = ControllerFactory.getInstance();

		Controller controller = controllerFactory.getController();
		System.out.println("Enter ID of equipment you want to return : ");
		System.out.println("___________________________________________");
		
		/*отправляется запрос SHOW_RENTED_EQUIPMENT вместе с id клиента
		 * возвращается строковое представление арендованного equipment
		 * */
		
		System.out.println(controller.executeRequest("SHOW_RENTED_EQUIPMENT " + clientId));
		System.out.println("___________________________________________");

		int equipmentId = 0;               //пользователь вводит id снаряжения, которое хочет сдать
		Scanner sc = new Scanner(System.in);
		if (sc.hasNextInt()) {
			equipmentId = sc.nextInt();
		}

		/*отправляется запрос RETURN_EQUIPMENT вместе с id снаряжения
		 * возвращается информация об удачной сдаче , либо проблеме возникшей во время сдачи
		 * */
		
		
		System.out.println(controller.executeRequest("RETURN_EQUIPMENT " + equipmentId));
	}

}
