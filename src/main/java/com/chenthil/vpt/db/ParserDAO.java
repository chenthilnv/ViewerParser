package com.chenthil.vpt.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.chenthil.vpt.vo.ViewerBean;

public class ParserDAO {
	
	public void insertOrUpdate(ViewerBean viewverBean) {
		
		PreparedStatement  pstmt = null;
		ResultSet rs  = null;
		
		try(Connection connection = DBConnectionHandler.getConnection()) {
				
			String sql = "Select * from PARSER where GUID=?";
			
			pstmt = connection.prepareStatement(sql);
			
			pstmt.setString(1, viewverBean.getGuid());
			
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				
				PreparedStatement pstmtInner = null;
				
				if (viewverBean.getRequestTimestamp() != null) {
				
					sql = "update PARSER set TIMEREQ=? where GUID=?";
					pstmtInner = connection.prepareStatement(sql);
					pstmtInner.setString(1, viewverBean.getRequestTimestamp());
					
				} else if (viewverBean.getResponseTimestamp() != null) {
					
					sql = "update PARSER set TIMERESP=? where GUID=?";
					pstmtInner = connection.prepareStatement(sql);
					pstmtInner.setString(1, viewverBean.getResponseTimestamp());
				}
				
				pstmtInner.setString(2, viewverBean.getGuid());
				
				if (pstmtInner.executeUpdate() > 0) {
					
					System.out.println("Row updated successfully GUID:" + viewverBean.toString());
					
				} else {
					
					System.out.println("Row NOT updated successfully GUID:" + viewverBean.toString());
				}
				
				if (pstmtInner != null) pstmtInner.close();
				
			} else {
				
				
				sql = "insert into PARSER (GUID,TIMEREQ,TIMERESP,URI,ACTION) "
						+ "values (?,?,?,?,?)";
				
				PreparedStatement pstmtInner = connection.prepareStatement(sql);
				
				pstmtInner.setString(1, viewverBean.getGuid());
				pstmtInner.setString(2, viewverBean.getRequestTimestamp());
				pstmtInner.setString(3, viewverBean.getResponseTimestamp());
				pstmtInner.setString(4, viewverBean.getUri());
				pstmtInner.setString(5, viewverBean.getAction());
				
				
				if (pstmtInner.executeUpdate() > 0) {
					
					System.out.println("Row inserted successfully GUID:" + viewverBean.toString());
					
				} else {
					
					System.out.println("Row not INSERTED successfully GUID:" + viewverBean.toString());
					
				}
				
			}
			
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
			
		} finally {
			
			try {
			
				if (rs != null) rs.close();
				if (pstmt != null) pstmt.close();
				
				
			} catch(Exception e) {
				e.printStackTrace();
			}
			
			
		}
		
		
		
		
	}
	
	
}
