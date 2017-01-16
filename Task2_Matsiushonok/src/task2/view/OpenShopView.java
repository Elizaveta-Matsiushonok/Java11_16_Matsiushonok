package task2.view;

import java.util.Scanner;

public class OpenShopView{

	public void openShop() {

		System.out.println("Welcom to the shop!\nWhat would you like to do: SING_IN | REGISTER | EXIT...");

		Scanner sc = new Scanner(System.in);
		String answer = null;
		

		if (sc.hasNextLine()) {
			answer = sc.nextLine().toUpperCase();
		} else {
			System.out.println("enter your answer");
		}
		
		switch(answer){
		case "SING_IN": EnteringView.singIn(answer); break;
		case "REGISTER": EnteringView.register(answer); break;
		case "EXIT": return ; 
		default: System.out.println("incorrect answer");
		}

	}

}
