package com.chenthil.vpt.controller;

import java.util.List;

import com.chenthil.vpt.db.ParserDAO;

public class ViewLoaderController {
	
	public List<Object[]> loadDataFromDB() {
		
		ParserDAO parserDAO = new ParserDAO();
		return parserDAO.loadDataFromDB();
		
	}

}
