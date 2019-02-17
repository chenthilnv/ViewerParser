package com.chenthil.vpt.util;

import java.io.File;

/**
 * General utility Validator
 * 
 * @author Chenthil Natarajan
 *
 */
public class Validator {
	
	/**
	 * Validates if the file really exists
	 * 
	 * @param filePath
	 * @return Boolean
	 */
	public static Boolean doesFileExist(String filePath) {
		
		File file = new File(filePath);
		return file.exists() && file.isFile();
		
	}

}
