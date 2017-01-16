package task2.view;

import java.util.Scanner;

import task2.controller.Controller;
import task2.controller.ControllerFactory;

public class EnteringView {
	
	public static void singIn(String answer) {

		StringBuilder request = new StringBuilder(answer + " ");
	
		System.out.println("Please, enter your login: ");
		String login = enterAnswer();

		request.append(login + " ");

		System.out.println("password: ");
		String password = enterAnswer();
		request.append(password + " ");

		ControllerFactory controllerFactory = ControllerFactory.getInstance();

		Controller controller = controllerFactory.getController();
 
		/*  запрос SING_IN отправляется в контроллер вместе с login, password
		*   контроллер возвращает ответ в виде строки
		*   "Welcom, имя_клиента id клиента"
		 * */
		String[] responseArgs = controller.executeRequest(request.toString()).split(" ");    
		                                                                                     
		int clientId = Integer.parseInt(responseArgs[2]);                                    
		
		System.out.println(responseArgs[0] + responseArgs[1]);
		
		ShoppingView.startShoping(clientId);          //пользователь вошел в систему, и переходит на новое view под своим id

	}

	public static void register(String answer) {

		StringBuilder request = new StringBuilder(answer + " ");

		System.out.println("Please, enter your name: ");
		String name = enterAnswer();
		request.append(name + " ");

		System.out.println("surname: ");
		String surname = enterAnswer();
		request.append(surname + " ");

		System.out.println("patronymic: ");
		String patronymic = enterAnswer();
		request.append(patronymic + " ");

		System.out.println("Please, enter your login: ");
		String login = enterAnswer();
		request.append(login + " ");

		System.out.println("password: ");
		String password = enterAnswer();
		request.append(password + " ");

		ControllerFactory controllerFactory = ControllerFactory.getInstance();

		Controller controller = controllerFactory.getController();
		
		/*  запрос REGISTER отправляется в контроллер вместе с name, surname и тд.
		*   контроллер возвращает ответ в виде строки
		*   "Welcom, имя_клиента, id клиента"
		 * */
		
		String[] responseArgs = controller.executeRequest(request.toString()).split(" ");
		
		int clientId = Integer.parseInt(responseArgs[2]);
		
		System.out.println(responseArgs[0] + responseArgs[1]);
		
		ShoppingView.startShoping(clientId);             //пользователь вошел в систему, и переходит на новое view под своим id

	}

	public static String enterAnswer() {
		Scanner sc = new Scanner(System.in);
		if (sc.hasNext()) {
			return sc.nextLine();
		} else {
			System.out.println("enter something");
		}
		return null;
	}


}
