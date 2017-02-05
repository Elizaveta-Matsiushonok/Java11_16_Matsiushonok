package task4.bean;

import task4.element.IElement;

public class ServletMapping implements IElement{
	
	private String servletName;
	private String urlPattern;
	public String getServletName() {
		return servletName;
	}
	public void setServletName(String servletName) {
		this.servletName = servletName;
	}
	public String getUrlPattern() {
		return urlPattern;
	}
	public void setUrlPattern(String urlPattern) {
		this.urlPattern = urlPattern;
	}
	@Override
	public String toString() {
		return "SERVLET-MAPPING: " + "servlet-name: " + servletName + ", url-pattern: " + urlPattern;
	}
	

}
