package task4.bean;

import task4.element.IElement;

public class Listener implements IElement{
	
	private String listenerClass;

	public String getListenerClass() {
		return listenerClass;
	}

	public void setListenerClass(String listenerClass) {
		this.listenerClass = listenerClass;
	}

	@Override
	public String toString() {
		return "LISTENER: " + "listener-class: " + listenerClass;
	}
	

}
