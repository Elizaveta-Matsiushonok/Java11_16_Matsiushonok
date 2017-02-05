package task4.parser;

import java.io.IOException;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import task4.handler.SaxHandler;
import task4.parser.abstr.Parser;

public class SaxParser extends Parser{
	
	@Override
	public void parseXML() {
		XMLReader reader = null;
		SaxHandler handler = null;
		
		try {
			reader = XMLReaderFactory.createXMLReader();
			handler = new SaxHandler();
			reader.setContentHandler(handler);
			reader.parse(new InputSource(filePath));
			printElements(handler.getElementList());
		} catch (SAXException | IOException e) {
			System.err.println("Error in parsing with sax. " + e.getMessage());
		}
		
	}
	
}
