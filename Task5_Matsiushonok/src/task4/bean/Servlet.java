package task4.bean;

import task4.element.IElement;

public class Servlet implements IElement{
	
	private String servletName;
	private String servletClass;
	private InitParam initParam;
	public InitParam getInitParam() {
		return initParam;
	}
	public void setInitParam(InitParam initParam) {
		this.initParam = initParam;
	}
	public String getServletName() {
		return servletName;
	}
	public void setServletName(String servletName) {
		this.servletName = servletName;
	}
	public String getServletClass() {
		return servletClass;
	}
	public void setServletClass(String servletClass) {
		this.servletClass = servletClass;
	}
	
	@Override
	public String toString(){
		if(initParam != null){
			return "SERVLET: " + "servlet-name: " + servletName + ", servlet-class: " + servletClass + "\n" + initParam;
		}
		return "SERVLET: " + "servlet-name: " + servletName + ", servlet-class: " + servletClass;
	}
	
	
	

}
