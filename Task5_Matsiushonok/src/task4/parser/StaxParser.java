package task4.parser;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

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
import task4.parser.abstr.Parser;

public class StaxParser extends Parser {

	@Override
	public void parseXML() {

		XMLInputFactory inputFactory = XMLInputFactory.newInstance();
		InputStream input = null;
		XMLStreamReader reader = null;

		try {
			input = new FileInputStream(filePath);
			reader = inputFactory.createXMLStreamReader(input);
			printElements(getElementList(reader));
		} catch (XMLStreamException | FileNotFoundException e) {
			System.err.println("Error in parsing file: " + e.getMessage());
		}

	}

	private List<IElement> getElementList(XMLStreamReader reader) throws XMLStreamException {

		List<IElement> elements = new ArrayList<>();
		TagName tagName = null;
		DisplayName displayName = null;
		WelcomeFileList welcomeFileList = null;
		Filter filter = null;
		InitParam initParam = null;
		FilterMapping filterMapping = null;
		Listener listener = null;
		Servlet servlet = null;
		ServletMapping servletMapping = null;
		ErrorPage errorPage = null;

		boolean isFilterNameOfFilter = false;
		boolean isInitParamOfFilter = false;
		boolean isServletnameOfServlet = false;
		boolean isUrlPatternOfFilterMapping = false;

		while (reader.hasNext()) {
			int type = reader.next();

			switch (type) {
			case XMLStreamConstants.START_ELEMENT:

				tagName = TagName.valueOf(reader.getLocalName().toUpperCase().replace("-", "_"));
				switch (tagName) {
				case DISPLAY_NAME:
					displayName = new DisplayName();
					break;
				case WELCOME_FILE_LIST:
					welcomeFileList = new WelcomeFileList();
					break;
				case FILTER:
					filter = new Filter();
					isFilterNameOfFilter = true;
					isInitParamOfFilter = true;
					break;
				case INIT_PARAM:
					initParam = new InitParam();
					break;
				case FILTER_MAPPING:
					filterMapping = new FilterMapping();
					isFilterNameOfFilter = false;
					isUrlPatternOfFilterMapping = true;
					break;
				case LISTENER:
					listener = new Listener();
					break;
				case SERVLET:
					servlet = new Servlet();
					isInitParamOfFilter = false;
					isServletnameOfServlet = true;
					break;
				case SERVLET_MAPPING:
					servletMapping = new ServletMapping();
					isServletnameOfServlet = false;
					isUrlPatternOfFilterMapping = false;
					break;
				case ERROR_PAGE:
					errorPage = new ErrorPage();
					break;
				}
				break;
			case XMLStreamConstants.CHARACTERS:

				String text = reader.getText().trim();
				if (text.isEmpty()) {
					break;
				}

				switch (tagName) {
				case DISPLAY_NAME:
					displayName.setDisplayName(text);
					break;
				case WELCOME_FILE:
					welcomeFileList.setWelcomeFile(text);
					break;
				case FILTER_NAME:
					if (isFilterNameOfFilter) {
						filter.setFilterName(text.toString());
					} else {
						filterMapping.setFilterName(text);
					}
					break;
				case FILTER_CLASS:
					filter.setFilterClass(text);
					break;
				case PARAM_NAME:
					initParam.setParamName(text);
					break;
				case PARAM_VALUE:
					initParam.setParamValue(text);
					break;
				case URL_PATTERN:
					if (isUrlPatternOfFilterMapping) {
						filterMapping.setUrlPattern(text);
					} else {
						servletMapping.setUrlPattern(text);
					}
					break;
				case DISPATCHER:
					filterMapping.setDispatcher(text);
					break;
				case LISTENER_CLASS:
					listener.setListenerClass(text);
					break;
				case SERVLET_NAME:
					if (isServletnameOfServlet) {
						servlet.setServletName(text);
					} else {
						servletMapping.setServletName(text);
					}
					break;
				case SERVLET_CLASS:
					servlet.setServletClass(text);
					break;
				case EXCEPTION_TYPE:
					errorPage.setExceptionType(text);
					break;
				case ERROR_CODE:
					errorPage.setErrorCode(Integer.parseInt(text));
					break;
				case LOCATION:
					errorPage.setLocation(text);
					break;
				}
				break;
			case XMLStreamConstants.END_ELEMENT:

				tagName = TagName.valueOf(reader.getLocalName().toUpperCase().replace("-", "_"));
				switch (tagName) {
				case DISPLAY_NAME:
					elements.add(displayName);
					break;
				case WELCOME_FILE_LIST:
					elements.add(welcomeFileList);
					break;
				case FILTER:
					elements.add(filter);
					break;
				case INIT_PARAM:
					if (isInitParamOfFilter) {
						filter.setInitParam(initParam);
					} else {
						servlet.setInitParam(initParam);
					}
					break;
				case FILTER_MAPPING:
					elements.add(filterMapping);
					break;
				case LISTENER:
					elements.add(listener);
					break;
				case SERVLET:
					elements.add(servlet);
					break;
				case SERVLET_MAPPING:
					elements.add(servletMapping);
					break;
				case ERROR_PAGE:
					elements.add(errorPage);
					break;
				}
			}
		}

		return elements;

	}

}
