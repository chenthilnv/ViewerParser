package com.chenthil.vpt.controller;

import java.util.List;

import com.chenthil.vpt.db.ParserDAO;

/**
 * Controller for View Loading from the embeded H2 DB
 * 
 * @author Chenthil Natarajan
 *
 */
public class ViewLoaderController {
	
	/**
	 * This method gets the current persisted data from PArserDAO and returns to the GUI 
	 * 
	 * 
	 * @return
	 */
	public List<Object[]> loadDataFromDB() {
		
		ParserDAO parserDAO = new ParserDAO();
		return parserDAO.loadDataFromDB();
		
	}

}
