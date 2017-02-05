package task4.bean;

import task4.element.IElement;

public class InitParam implements IElement{
	
	private String paramName;
	private String paramValue;
	
	public String getParamName() {
		return paramName;
	}

	public void setParamName(String paramName) {
		this.paramName = paramName;
	}
	public String getParamValue() {
		return paramValue;
	}
	public void setParamValue(String paramValue) {
		this.paramValue = paramValue;
	}
	@Override
	public String toString(){
		return "INIT-PARAM: " + "param-name: " + paramName + ", param-value: " + paramValue;
	}
	
	

}
