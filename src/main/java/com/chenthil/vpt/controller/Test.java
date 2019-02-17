package com.chenthil.parser.main;

import com.chenthil.parser.util.FileParserIface;
import com.chenthil.parser.util.TextFileParser;

public class Test {
	
	public static void main(String args[]) {
		
		FileParserIface fileParser = new TextFileParser();
		fileParser.parseFile("C:\\projects\\ch\\trace.log");
		
		
	}
}
