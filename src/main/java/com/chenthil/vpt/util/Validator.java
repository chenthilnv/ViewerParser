package com.chenthil.vpt.util;

import java.io.File;

public class Validator {
	
	
	public static Boolean doesFileExist(String filePath) {
		
		File file = new File(filePath);
		return file.exists() && file.isFile();
		
	}

}
