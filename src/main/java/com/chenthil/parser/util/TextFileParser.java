package com.chenthil.parser.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class TextFileParser implements  FileParserIface{

	public void parseFile(String filePath) {
		// TODO Auto-generated method stub
		
		try (FileInputStream fileInputStream = new FileInputStream(filePath);
				Scanner scanner = new Scanner(fileInputStream)) {
			
			while (scanner.hasNext()) {
				System.out.println("--------");
				System.out.println(scanner.nextLine());
				System.out.println("--------");
			}
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	

}
