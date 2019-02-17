package com.chenthil.vpt.parser;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import com.chenthil.vpt.db.ParserDAO;
import com.chenthil.vpt.vo.ViewerBean;

/**
 * 
 * The main Text parser utility that reads the file, parses the data and calls ParserDAO to store the data 
 * to H2 embeded DB
 * 
 * @author Chenthil Natarajan
 *
 */
public class TextFileParserImpl implements FileParserIface{

	

	/**
	 * Helper method that aids the parser to find the valid string that should be considered for parsing
	 * @param str
	 * @return Boolean
	 */
	public Boolean isValid(String str) {
		
		return str != null && str.trim().length() > 0 &&
				(str.trim().startsWith("TIME:") ||
						str.trim().startsWith("SOAP URI:")  ||
						str.trim().startsWith("SOAPAction:")  ||
						str.trim().startsWith("GUID:")  ||
						str.trim().startsWith("SOAP URI:"));
						
	}
	
	/**
	 * Main parser method that reads the file (by chunks - Used BufferReader which is very effective in dealing with large files) 
	 * and calls streamData method that maps the data to a bean which is then sent to H2 DB (embeded) for persistance.
	 * @param fileName
	 */
	public  void parseFile(String filePath) {
		// TODO Auto-generated method stub
		
		try(BufferedReader in = new BufferedReader(new FileReader(filePath))) {
			
			String line = "";
			
			String lineItemType = "";
			
			ViewerBean viewverBean = null;
		    
	    
		    while ((line = in.readLine()) != null ) {
		    	
		    	if ("SOAP REQUEST RECEIVED".equalsIgnoreCase(line)) {
		    		
		    		 viewverBean = new ViewerBean(); 
		    		 
		    		 lineItemType = "REQUEST";
		    		 
		    	} else if ("SOAP RESPONSE".equalsIgnoreCase(line)) {
		    		
		    		 viewverBean = new ViewerBean(); 
		    		 
		    		 lineItemType = "RESPONSE";
		    		 
		    	}
		    	 
		    	 streamData(line,lineItemType,viewverBean);
				
		    }
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}
	
	/**
	 * Helper method that maps the streamed data to ViewerBean and sends the bean to H2 embeded DB for persistence 
	 * 
	 * @param line
	 * @param lineItemType
	 * @param viewverBean
	 */
	private void streamData(String line, String lineItemType, ViewerBean viewverBean) {
		
		if (isValid(line)) {
			
			line = line.trim();
			
			if (line.startsWith("TIME:")) {
			
				String lineVal = line.substring(line.indexOf(":") + 1);
				
				if ("REQUEST".equalsIgnoreCase(lineItemType)) {
					
					viewverBean.setRequestTimestamp(lineVal.trim());
					
				} else {
					
					viewverBean.setResponseTimestamp(lineVal.trim());
				}
		    	
		    } else if (line.startsWith("SOAP URI:")) {
		    	
		    	String lineVal = line.substring(line.indexOf(":") + 1);
		    	
		    	viewverBean.setUri(lineVal.trim());
		    	
		    } else if (line.startsWith("SOAPAction:")) {
		    	
		    	String lineVal = line.substring(line.indexOf(":") + 1);
		    	
		    	viewverBean.setAction(lineVal.trim());
		    	
		    } else if (line.startsWith("GUID:")) {
		    	
		    	String lineVal = line.substring(line.indexOf(":") + 1);
		    	
		    	viewverBean.setGuid(lineVal.trim());
		    	
		    	
		    }
			
			if (viewverBean.canBeSentToDB()) {
			
				ParserDAO parserDAO = new ParserDAO();
				parserDAO.insertOrUpdate(viewverBean);
				
			} 
		}
	
	}
	
}
