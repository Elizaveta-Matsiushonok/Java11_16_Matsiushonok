package task4.parser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.sun.org.apache.xerces.internal.parsers.DOMParser;

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
import task4.parser.abstr.Parser;

public class DomParser extends Parser{
		
	@Override
	public void parseXML() {

		try {
			DOMParser parser = new DOMParser();
			parser.parse(filePath);
			Document document = parser.getDocument();
			printElements(getElementsList(document));
		} catch (SAXException | IOException e) {
			System.err.println("Error in parsing with dom. " + e.getMessage());
		}
	}

	private List<IElement> getElementsList(Document document) {
		
		Element root = document.getDocumentElement();

		List<IElement> elements = new ArrayList<>();

		NodeList displayNameNodes = root.getElementsByTagName("display-name");
		DisplayName displayName = null;

		for (int i = 0; i < displayNameNodes.getLength(); i++) {
			displayName = new DisplayName();
			Element displayNameElement = (Element) displayNameNodes.item(i);
			displayName.setDisplayName(displayNameElement.getTextContent());
			elements.add(displayName);
		}

		NodeList welcomeFileListNodes = root.getElementsByTagName("welcome-file-list");
		WelcomeFileList welcomeFileList = null;

		for (int i = 0; i < welcomeFileListNodes.getLength(); i++) {
			welcomeFileList = new WelcomeFileList();
			Element welcomeFileListElement = (Element) welcomeFileListNodes.item(i);

			NodeList welcomeFileNodes = welcomeFileListElement.getElementsByTagName("welcome-file");

			for (int j = 0; j < welcomeFileNodes.getLength(); j++) {
				Element welcomeFileElement = (Element) welcomeFileNodes.item(j);
				welcomeFileList.setWelcomeFile(welcomeFileElement.getTextContent());
			}
			elements.add(welcomeFileList);
		}

		NodeList filterNodes = root.getElementsByTagName("filter");
		Filter filter = null;
		InitParam initParam = null;

		for (int i = 0; i < filterNodes.getLength(); i++) {
			filter = new Filter();
			Element filterElement = (Element) filterNodes.item(i);
			filter.setFilterName(filterElement.getElementsByTagName("filter-name").item(0).getTextContent());
			filter.setFilterClass(filterElement.getElementsByTagName("filter-class").item(0).getTextContent());
			
			NodeList initParamNodes = filterElement.getElementsByTagName("init-param");
			for (int j = 0; j < initParamNodes.getLength(); j++) {
				initParam = new InitParam();
				Element initParamElement = (Element) initParamNodes.item(j);
				initParam.setParamName(initParamElement.getElementsByTagName("param-name").item(0).getTextContent());
				initParam.setParamValue(initParamElement.getElementsByTagName("param-value").item(0).getTextContent());
				filter.setInitParam(initParam);
			}
			elements.add(filter);
		}

		NodeList filterMappingNodes = root.getElementsByTagName("filter-mapping");
		FilterMapping filterMapping = null;

		for (int i = 0; i < filterMappingNodes.getLength(); i++) {
			filterMapping = new FilterMapping();
			Element filterMappingElement = (Element) filterMappingNodes.item(i);
			filterMapping.setFilterName(filterMappingElement.getElementsByTagName("filter-name").item(0).getTextContent());
			filterMapping.setUrlPattern(filterMappingElement.getElementsByTagName("url-pattern").item(0).getTextContent());
			filterMapping.setDispatcher(filterMappingElement.getElementsByTagName("dispatcher").item(0).getTextContent());
			elements.add(filterMapping);
		}

		NodeList listenerNodes = root.getElementsByTagName("listener");
		Listener listener = null;

		for (int i = 0; i < listenerNodes.getLength(); i++) {
			listener = new Listener();
			Element listenerElement = (Element) listenerNodes.item(i);
			listener.setListenerClass(listenerElement.getElementsByTagName("listener-class").item(0).getTextContent());
			elements.add(listener);
		}

		NodeList servletNodes = root.getElementsByTagName("servlet");
		Servlet servlet = null;

		for (int i = 0; i < servletNodes.getLength(); i++) {
			servlet = new Servlet();
			Element servletElement = (Element) servletNodes.item(i);
			servlet.setServletName(servletElement.getElementsByTagName("servlet-name").item(0).getTextContent());
			servlet.setServletClass(servletElement.getElementsByTagName("servlet-class").item(0).getTextContent());
			
			NodeList initParamNodes = servletElement.getElementsByTagName("init-param");
			for (int j = 0; j < initParamNodes.getLength(); j++) {
				initParam = new InitParam();
				Element initParamElement = (Element) initParamNodes.item(j);
				initParam.setParamName(initParamElement.getElementsByTagName("param-name").item(0).getTextContent());
				initParam.setParamValue(initParamElement.getElementsByTagName("param-value").item(0).getTextContent());
				servlet.setInitParam(initParam);
			}
			
			elements.add(servlet);
		}

		NodeList servletMappingNodes = root.getElementsByTagName("servlet-mapping");
		ServletMapping servletMapping = null;

		for (int i = 0; i < servletMappingNodes.getLength(); i++) {
			servletMapping = new ServletMapping();
			Element servletMappingElement = (Element) servletMappingNodes.item(i);
			servletMapping.setServletName(servletMappingElement.getElementsByTagName("servlet-name").item(0).getTextContent());
			servletMapping.setUrlPattern(servletMappingElement.getElementsByTagName("url-pattern").item(0).getTextContent());
			elements.add(servletMapping);
		}

		NodeList errorPageNodes = root.getElementsByTagName("error-page");
		ErrorPage errorPage = null;

		for (int i = 0; i < errorPageNodes.getLength(); i++) {
			errorPage = new ErrorPage();
			Element errorPageElement = (Element) errorPageNodes.item(i);
			if (errorPageElement.getElementsByTagName("exception-type").item(0) != null) {
				errorPage.setExceptionType(errorPageElement.getElementsByTagName("exception-type").item(0).getTextContent());
			}
			if (errorPageElement.getElementsByTagName("error-code").item(0) != null) {
				errorPage.setErrorCode(Integer.parseInt(errorPageElement.getElementsByTagName("error-code").item(0).getTextContent()));
			}
			errorPage.setLocation(errorPageElement.getElementsByTagName("location").item(0).getTextContent());
			elements.add(errorPage);
		}

		return elements;
	}


}
