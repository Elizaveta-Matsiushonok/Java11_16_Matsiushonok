package task4.bean;

import task4.element.IElement;

public class FilterMapping implements IElement{
	
	private String filterName;
	private String urlPattern;
	private String dispatcher;
	public String getFilterName() {
		return filterName;
	}
	public void setFilterName(String filterName) {
		this.filterName = filterName;
	}
	public String getUrlPattern() {
		return urlPattern;
	}
	public void setUrlPattern(String urlPattern) {
		this.urlPattern = urlPattern;
	}
	public String getDispatcher() {
		return dispatcher;
	}
	public void setDispatcher(String dispatcher) {
		this.dispatcher = dispatcher;
	}
	@Override
	public String toString(){
		return "FILTER-MAPPING: " + "filter-name: " + filterName + ", url-pattern: " + urlPattern + ", dispatcher: " + dispatcher;
	}
	
	

}
