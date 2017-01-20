package task3.main;

import task3.service.FileAnalyzer;
import task3.service.impl.FileAnalyzerImpl;

public class Main {

	public static void main(String[] args) {
		 FileAnalyzer analyzer = new FileAnalyzerImpl();
		 
		 analyzer.setFilePath("src/notes.xml");
		 
		 while(analyzer.hasNext()){
				analyzer.next();
		 }

	}

}
