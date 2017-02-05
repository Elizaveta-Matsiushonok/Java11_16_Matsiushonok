package task4.bean;

import java.util.ArrayList;
import java.util.List;

import task4.element.IElement;

public class WelcomeFileList implements IElement{
	
	private List<String> welcomeFiles = new ArrayList<>();

	public List<String> getWelcomeFile() {
		return welcomeFiles;
	}

	public void setWelcomeFile(String welcomeFile) {
		welcomeFiles.add(welcomeFile);
	}

	@Override
	public String toString() {
		StringBuilder welcomFiles = new StringBuilder();
		welcomFiles.append("WELCOME-FILE-LIST:");
		for(String welcomeFile : welcomeFiles){
			welcomFiles.append("\n welcome-file: ");
			welcomFiles.append(welcomeFile);
		}
		return welcomFiles.toString();
	}
	
	

}
