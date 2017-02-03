package task3.service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import task3.bean.Lexeme;
import task3.bean.Node;
import task3.bean.NodeType;
import task3.dao.dao.DAOLexer;
import task3.dao.exception.DAOException;
import task3.dao.factory.DAOFactory;
import task3.service.exception.ServiceException;

public class FileAnalyzer {

	public FileAnalyzer() {

	}

	public void setFilePath(String file) throws ServiceException {
		if (file == null || file.isEmpty()) {
			throw new ServiceException("incorrect path to file.");
		}
		DAOFactory daoFactory = DAOFactory.getInstance();
		DAOLexer daoLexer = daoFactory.getDAOLexer();
		daoLexer.setFilePath(file);
	}

	public boolean hasNext() {
		DAOFactory daoFactory = DAOFactory.getInstance();
		DAOLexer daoLexer = daoFactory.getDAOLexer();
		try {
			return daoLexer.hasNextLexeme();
		} catch (DAOException e) {
			System.err.println(e.getMessage());
		}
		return false;
	}

	public void next() {
		DAOFactory daoFactory = DAOFactory.getInstance();
		DAOLexer daoLexer = daoFactory.getDAOLexer();
		Node node = null;
		Lexeme lexeme = null;
		try {
			lexeme = daoLexer.getLexeme();
			if (lexeme != null) {
				
				node = analyzeNode(lexeme);
			}
			
			if (node != null && node.getNodeType() != NodeType.DOCUMENT_NODE) {
				System.out.println(node.getNodeType() + " : " + node.getContent());
			}

		} catch (DAOException e) {
			System.err.println(e.getMessage());
		}

	}

	private Node analyzeNode(Lexeme lexeme) {

		Node node = null;
		Pattern startTagPattern = Pattern.compile("<\\w");
		Matcher startTagMatcher = startTagPattern.matcher(lexeme.getLexeme());
		Pattern endTagPattern = Pattern.compile("</\\w");
		Matcher endTagMatcher = endTagPattern.matcher(lexeme.getLexeme());
		Pattern textPattern = Pattern.compile("[(\\w) | [а-яА-Я] | \\p{Punct}]");
	    Matcher textMatcher = textPattern.matcher(lexeme.getLexeme().trim());
		Pattern documentPattern = Pattern.compile("[<?]");
		Matcher documentMatcher = documentPattern.matcher(lexeme.getLexeme());
		Pattern selfClosingTagPattern = Pattern.compile("/>");
		Matcher selfClosingTagMatcher = selfClosingTagPattern.matcher(lexeme.getLexeme());

		if (selfClosingTagMatcher.find()) {
			node = new Node();
			node.setContent(lexeme.getLexeme());
			node.setNodeType(NodeType.SELF_CLOSING_TAG);
		}

		else if (startTagMatcher.find()) {
			node = new Node();
			node.setContent(lexeme.getLexeme());
			node.setNodeType(NodeType.START_TAG);
		}

		else if (endTagMatcher.find()) {
			node = new Node();
			node.setContent(lexeme.getLexeme());
			node.setNodeType(NodeType.END_TAG);
		}

		else if (documentMatcher.find()) {
			node = new Node();
			node.setContent(lexeme.getLexeme());
			node.setNodeType(NodeType.DOCUMENT_NODE);
		}

		else if (textMatcher.find()) {
			node = new Node();
			node.setContent(lexeme.getLexeme().trim());
			node.setNodeType(NodeType.TEXT_NODE);
		}

		else {
			return null;
		}
		return node;
	}

}