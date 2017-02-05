package task4.bean;

import task4.element.IElement;

public class DisplayName implements IElement{
	
	private String displayName;

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	@Override
	public String toString() {
		return "DISPLAY-NAME: " + displayName;
	}

}
