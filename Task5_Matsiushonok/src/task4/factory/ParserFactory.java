package task4.factory;

import task4.parser.DomParser;
import task4.parser.SaxParser;
import task4.parser.StaxParser;
import task4.parser.abstr.Parser;

public class ParserFactory {
	
	private static final ParserFactory parserFactory = new ParserFactory();
	
	private ParserFactory(){
		
	}
	
	private final Parser saxParser = new SaxParser();
	private final Parser staxParser = new StaxParser();
	private final Parser domParser = new DomParser();
	
	public Parser getSaxParser(){
		return saxParser;
	}
	
	public Parser getStaxParser(){
		return staxParser;
	}
	
	public Parser getDomParser(){
		return domParser;
	}
	
	public static ParserFactory getInstance(){
		return parserFactory;
	}

}
