package com.chenthil.vpt.controller;

import com.chenthil.vpt.parser.FileParserIface;
import com.chenthil.vpt.parser.TextFileParserImpl;
/**
 * 
 * Controller for the parser functionality
 * 
 * @author Chenthil Natarajan
 *
 */
public class ParserController {
	
	/**
	 * This calls file parser - TextFileParserImpl
	 * 
	 * 
	 * @param fileName
	 */
	public void parseFile(String fileName) {
		
		FileParserIface fileParser = new TextFileParserImpl();
		fileParser.parseFile(fileName);
		
	}

}
