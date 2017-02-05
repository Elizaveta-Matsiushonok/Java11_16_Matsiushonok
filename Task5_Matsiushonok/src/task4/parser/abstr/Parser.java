package task4.parser.abstr;

import java.util.List;

import task4.element.IElement;

public abstract class Parser {
	
    protected String filePath;
	
	public void setFilePath(String filePath){
		this.filePath = filePath;
	}
	
	public void printElements(List<IElement> elements){
		int i = 1;
		for (IElement element : elements) {
			System.out.print(i + ".");
			System.out.println(element);
			i++;
		}
	}
	
	public abstract void parseXML();

}
