package task4.bean;

import task4.element.IElement;

public class ErrorPage implements IElement {

	private String exceptionType;
	private int errorCode;
	private String location;

	public String getExceptionType() {
		return exceptionType;
	}

	public void setExceptionType(String exceptionType) {
		this.exceptionType = exceptionType;
	}

	public int getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	@Override
	public String toString() {
		if (exceptionType != null) {
			return "ERROR-PAGE:" + "exception-type: " + exceptionType + ", location: " + location;
		} else  {
			return "ERROR-PAGE:" + " error-code: " + errorCode + ", location: " + location;
		}
	}

}
