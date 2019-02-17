package com.chenthil.vpt.parser;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import com.chenthil.vpt.db.ParserDAO;
import com.chenthil.vpt.vo.ViewerBean;

public class TextFileParserImpl implements FileParserIface{
	
	public static Map<String,ViewerBean> hashMap = new HashMap<String,ViewerBean>();
	
	
	public static Boolean isNotValid(String str) {
		
		return str == null || str.trim().length() <= 0 || str.trim().equalsIgnoreCase("*");
	}
	
	public static Boolean isValid(String str) {
		
		return str != null && str.trim().length() > 0 &&
				(str.trim().startsWith("TIME:") ||
						str.trim().startsWith("SOAP URI:")  ||
						str.trim().startsWith("SOAPAction:")  ||
						str.trim().startsWith("GUID:")  ||
						str.trim().startsWith("SOAP URI:"));
						
	}
	
	public   void parseFile(String filePath) {
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
	
	
	private static void streamData(String line, String lineItemType, ViewerBean viewverBean) {
		
		if (isValid(line)) {
			
			line = line.trim();
			
			if (line.startsWith("TIME:")) {
			
				String lineVal = line.substring(line.indexOf(":")+1);
				
				
				if ("REQUEST".equalsIgnoreCase(lineItemType)) {
					
					viewverBean.setRequestTimestamp(lineVal.trim());
					
				} else {
					
					viewverBean.setResponseTimestamp(lineVal.trim());
				}
		    	
		    } else if (line.startsWith("SOAP URI:")) {
		    	String lineVal = line.substring(line.indexOf(":")+1);
		    	viewverBean.setUri(lineVal.trim());
		    	
		    } else if (line.startsWith("SOAPAction:")) {
		    	String lineVal = line.substring(line.indexOf(":")+1);
		    	viewverBean.setAction(lineVal.trim());
		    	
		    } else if (line.startsWith("GUID:")) {
		    	
		    	String lineVal = line.substring(line.indexOf(":")+1);
		    	viewverBean.setGuid(lineVal.trim());
		    	
		    	
		    }
			
			if (viewverBean.canBeSentToDB()) {
			
				System.out.println("CAN BE SENT TO DB:" + viewverBean.toString());
				
				ParserDAO parserDAO = new ParserDAO();
				parserDAO.insertOrUpdate(viewverBean);
				
			} else {
				
			
				
			//	System.out.println("CANNOT BE SENT TO DB:" + viewverBean.toString());
				
			}
			
			
		 //  System.out.println(lineItemType + ":>>" + line + "<<");	
		}
	
	}
	
	


	public static void main(String args[]) throws SQLException {
		
		//DBConnectionHandler.getConnection();
		
		TextFileParserImpl parser = new TextFileParserImpl();
		parser.parseFile("C:\\Users\\chent\\Desktop\\test\\trace.log");
		
	}

}
