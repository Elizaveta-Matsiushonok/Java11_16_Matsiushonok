package task4.handler;

import java.util.ArrayList;
import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.helpers.DefaultHandler;

import task4.bean.DisplayName;
import task4.bean.ErrorPage;
import task4.bean.Filter;
import task4.bean.FilterMapping;
import task4.bean.InitParam;
import task4.bean.Listener;
import task4.bean.Servlet;
import task4.bean.ServletMapping;
import task4.bean.WelcomeFileList;
import task4.element.IElement;
import task4.element.TagName;

public class SaxHandler extends DefaultHandler {

	private List<IElement> elements = new ArrayList<>();
	private DisplayName displayName;
	private WelcomeFileList welcomeFileList;
	private Filter filter;
	private InitParam initParam;
	private FilterMapping filterMapping;
	private Listener listener;
	private Servlet servlet;
	private ServletMapping servletMapping;
	private ErrorPage errorPage;
	private StringBuilder text;

	public List<IElement> getElementList() {
		return elements;
	}

	@Override
	public void startDocument() throws SAXException {
		System.out.println("Parsing started.");
	}

	@Override
	public void endDocument() throws SAXException {
		System.out.println("Parsing ended.");
	}

	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		
		text = new StringBuilder();
		
		if (qName.equals("display-name")) {
			displayName = new DisplayName();
		} else if (qName.equals("welcome-file-list")) {
			welcomeFileList = new WelcomeFileList();
		} else if (qName.equals("filter")) {
			filter = new Filter();
		} else if (qName.equals("init-param")) {
			initParam = new InitParam();
		} else if (qName.equals("filter-mapping")) {
			filterMapping = new FilterMapping();
		} else if (qName.equals("listener")) {
			listener = new Listener();
		} else if (qName.equals("servlet")) {
			servlet = new Servlet();
		} else if (qName.equals("servlet-mapping")) {
			servletMapping = new ServletMapping();
		} else if (qName.equals("error-page")) {
			errorPage = new ErrorPage();
		}

	}

	@Override
	public void characters(char[] buffer, int start, int length) throws SAXException {
		text.append(buffer, start, length);
	}

	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		
		TagName tagName = TagName.valueOf(qName.toUpperCase().replace("-", "_"));
		
		switch (tagName) {
		case DISPLAY_NAME:
			displayName.setDisplayName(text.toString());
			elements.add(displayName);
			displayName = null;
			break;
		case WELCOME_FILE:
			welcomeFileList.setWelcomeFile(text.toString());
			break;
		case WELCOME_FILE_LIST:
			elements.add(welcomeFileList);
			welcomeFileList = null;
			break;
		case FILTER_NAME:
			if (filter != null) {
				filter.setFilterName(text.toString());
			} else if (filterMapping != null) {
				filterMapping.setFilterName(text.toString());
			}
			break;
		case FILTER_CLASS:
			filter.setFilterClass(text.toString());
			break;
		case FILTER:
			elements.add(filter);
			filter = null;
			break;
		case PARAM_NAME:
			initParam.setParamName(text.toString());
			break;
		case PARAM_VALUE:
			initParam.setParamValue(text.toString());
			break;
		case INIT_PARAM:
			if(filter != null){
				filter.setInitParam(initParam);
			}
			else if(servlet != null){
				servlet.setInitParam(initParam);
			}
			initParam = null;
			break;

		case URL_PATTERN:
			if (filterMapping != null) {
				filterMapping.setUrlPattern(text.toString());
			} else if (servletMapping != null) {
				servletMapping.setUrlPattern(text.toString());
			}
			break;
		case DISPATCHER:
			filterMapping.setDispatcher(text.toString());
			break;
		case FILTER_MAPPING:
			elements.add(filterMapping);
			filterMapping = null;
			break;

		case LISTENER_CLASS:
			listener.setListenerClass(text.toString());
			break;

		case LISTENER:
			elements.add(listener);
			listener = null;
			break;
		case SERVLET_NAME:
			if (servlet != null) {
				servlet.setServletName(text.toString());
			} else if (servletMapping != null) {
				servletMapping.setServletName(text.toString());
			}
			break;
		case SERVLET_CLASS:
			servlet.setServletClass(text.toString());
			break;
		case SERVLET:
			elements.add(servlet);
			servlet = null;
			break;
		case SERVLET_MAPPING:
			elements.add(servletMapping);
			servletMapping = null;
			break;
		case EXCEPTION_TYPE:
			errorPage.setExceptionType(text.toString());
			break;
		case ERROR_CODE:
			errorPage.setErrorCode(Integer.parseInt(text.toString()));
			break;
		case LOCATION:
			errorPage.setLocation(text.toString());
			break;
		case ERROR_PAGE:
			elements.add(errorPage);
			errorPage = null;
			break;
		}

	}

	@Override
	public void warning(SAXParseException e) throws SAXException {
		System.err.println("WARNING: line " + e.getLineNumber() + " : " + e.getMessage());
	}

	@Override
	public void error(SAXParseException e) throws SAXException {
		System.err.println("ERROR: line " + e.getLineNumber() + " : " + e.getMessage());
	}

	@Override
	public void fatalError(SAXParseException e) throws SAXException {
		System.err.println("FATAL ERROR: line " + e.getLineNumber() + " : " + e.getMessage());
	}

}
