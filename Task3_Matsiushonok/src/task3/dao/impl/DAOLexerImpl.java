package task3.dao.impl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import task3.bean.Lexeme;
import task3.dao.dao.DAOLexer;
import task3.dao.exception.DAOException;

public class DAOLexerImpl implements DAOLexer {

	private BufferedReader reader;
	private char current;
	ArrayList<Character> chars = new ArrayList<>();
	private int posToSkip = 0;
	private String file;
	long size;
	
	public void setFilePath(String file) {
		this.file = file;
	}

	@Override
	public Lexeme getLexeme() throws DAOException {
		try {
			reader = new BufferedReader(new InputStreamReader( new FileInputStream(file)));
		} catch (FileNotFoundException e) {
			throw new DAOException("can not find such file.");
		}

		try {
			reader.skip(posToSkip);
		} catch (IOException e1) {
			System.out.println("error in skiping file.");
		}

		readChar();

		StringBuilder builder = new StringBuilder(); //из коллекции полученных символов строим строку 
		for (Character ch : chars) {
			builder.append(ch);
		}

		chars.removeAll(chars);
		return new Lexeme(builder.toString());    //создаем новую лексему
	}

	private void readChar() {
		try {
			current = (char) reader.read();
			posToSkip++;
		} catch (IOException e) {
			System.out.println("error in reading character.");
		}

		if (current != '\n' && current != '\r') {
			chars.add(current);
		}

		if (current == '<') {
			readChar();
		}

		else if (current == '>') {
			return;
		}

		else if (chars.size() == 1 && chars.get(0) != '<') {   //начало считывания текстового узла 
			while (current != '<') {
			if (size < posToSkip) {
					return;
			}
				try {
					current = (char) reader.read();
					if (current != '<') {
			  		chars.add(current);
					} else {
						posToSkip--;
					}
				} catch (IOException e) {
					System.out.println("error in reading character.");
				}
				posToSkip++;
			}
		} else {
				readChar();
		}
	}

	@Override
	public boolean hasNextLexeme() throws DAOException{   
		size = new File(file).length();    // узнаем размер файла 
		if (size > posToSkip) {            //не достигнут ли конец файла при чтении
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
