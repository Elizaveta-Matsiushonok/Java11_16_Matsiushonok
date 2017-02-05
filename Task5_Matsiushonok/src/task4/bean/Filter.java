package task4.bean;

import task4.element.IElement;

public class Filter implements IElement {

	private String filterName;
	private String filterClass;
	private InitParam initParam;

	public String getFilterName() {
		return filterName;
	}

	public InitParam getInitParam() {
		return initParam;
	}

	public void setInitParam(InitParam initParam) {
		this.initParam = initParam;
	}

	public void setFilterName(String filterName) {
		this.filterName = filterName;
	}

	public String getFilterClass() {
		return filterClass;
	}

	public void setFilterClass(String filterClass) {
		this.filterClass = filterClass;
	}

	@Override
	public String toString() {
		if (initParam != null) {
			return "FILTER: " + "filter-name: " + filterName + ", filter-class: " + filterClass + "\n" + initParam;
		}
		return "FILTER: " + "filter-name: " + filterName + ", filter-class: " + filterClass;
	}

}
