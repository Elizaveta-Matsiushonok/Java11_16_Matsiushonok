package task3.main;

import task3.service.FileAnalyzer;
import task3.service.exception.ServiceException;

public class Main {

	public static void main(String[] args) {

		FileAnalyzer analyzer = new FileAnalyzer();

		try {
			analyzer.setFilePath("src/notes.xml");
			
			while (analyzer.hasNext()) {
				analyzer.next();
			}
			
		} catch (ServiceException e) {
			System.err.println(e.getMessage());
		}
	}

}
