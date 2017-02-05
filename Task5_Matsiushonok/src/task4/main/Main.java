package task4.main;

import java.util.Scanner;

import task4.factory.ParserFactory;
import task4.parser.abstr.Parser;


public class Main {

	public static void main(String[] args) {
	
		System.out.println("Enter: 1. to use Sax parser, 2. Stax parser, 3. DOM Parser");
		Scanner sc = new Scanner(System.in);

		int answer = 0;

		if (sc.hasNextInt()) {
			answer = sc.nextInt();
		}
		else{
			System.out.println("Incorrect answer.");
			return;
		}

		ParserFactory parserFactory = ParserFactory.getInstance();
		Parser parser = null;

		switch (answer) {
		case 1:
			parser = parserFactory.getSaxParser();
			break;
		case 2:
			parser = parserFactory.getStaxParser();
			break;
		case 3:
			parser = parserFactory.getDomParser();
			break;
		default:
			System.out.println("Incorrect answer.");
			return;
		}
		parser.setFilePath("src/web.xml");
		parser.parseXML();
	}

}
