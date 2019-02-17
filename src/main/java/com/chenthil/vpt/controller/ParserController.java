package com.chenthil.vpt.controller;

import com.chenthil.vpt.parser.FileParserIface;
import com.chenthil.vpt.parser.TextFileParserImpl;

public class ParserController {
	
	
	public void parseFile(String fileName) {
		
		FileParserIface fileParser = new TextFileParserImpl();
		fileParser.parseFile(fileName);
		
	}

}
