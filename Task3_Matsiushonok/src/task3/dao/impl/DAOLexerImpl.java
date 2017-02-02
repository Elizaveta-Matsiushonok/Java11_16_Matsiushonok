package task3.dao.impl;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import task3.bean.Lexeme;
import task3.dao.dao.DAOLexer;
import task3.dao.exception.DAOException;

public class DAOLexerImpl implements DAOLexer {

	private BufferedReader reader;
	private int current;
	private StringBuilder builder = new StringBuilder();
	private int posToSkip;
	private String file;

	@Override
	public void setFilePath(String file) {
		this.file = file;
	}

	@Override
	public Lexeme getLexeme() throws DAOException {

		try {
			reader = new BufferedReader(new FileReader(file));
		} catch (FileNotFoundException e) {
			throw new DAOException("can not find such file.");
		}

		try {
			reader.skip(posToSkip);
			builder = new StringBuilder();
			readChar();
			return new Lexeme(builder.toString());    // создаем новую лексему
			
		} catch (IOException e) {
			System.err.println("error in skiping file." + e);
		}

		return null; 
	}

	private void readChar() {                   

		try {
			if ((current = reader.read()) == -1) {
				return;
			}
			posToSkip++;
		} catch (IOException e) {
			System.err.println("error in reading character." + e);
			return;                                   // не хотелось запихивать все в try, поэтому return прямо с catch
		}

		if (current != '\n' && current != '\r') {
			builder.append((char) current);
		}

		if (current == '<') {
			readChar();
		}
		else if (current == '>') {
			return;
		}
		else if (builder.length() == 1 && builder.charAt(0) != '<') { // начало считывания текстового узла
			while (current != '<') {
				try {
					current = (char) reader.read();
					if (current != '<') {
						builder.append((char) current);
					} else {
						posToSkip--;
					}
				} catch (IOException e) {
					System.err.println("error in reading character." + e);
					return;
				}
				posToSkip++;
			}
		} else {
			readChar();
		}
	}

	@Override
	public boolean hasNextLexeme() throws DAOException {
		if (current != -1) {  // не достигнут ли конец файла при чтении
			return true; 
		}

		try {
			reader.close();
		} catch (IOException e) {
			throw new DAOException("can not close file.");
		}
		return false;
	}

}
