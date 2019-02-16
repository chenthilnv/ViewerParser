package com.chenthil.lpv.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.chenthil.lpv.vo.ViewerBean;

public class ParserDAO {
	
	public void insertOrUpdate(ViewerBean viewverBean) {
		
		PreparedStatement  pstmt = null;
		ResultSet rs  = null;
		
		try(Connection connection = DBConnectionHandler.getConnection()) {
				
			String sql = "Select * from TEST where GUID=?";
			
			pstmt = connection.prepareStatement(sql);
			
			pstmt.setString(1, viewverBean.getGuid());
			
			rs = pstmt.executeQuery();
			
			if (rs.getRow() > 0) {
				
				sql = "update TEST set TIMEREQ=?,TIMERESP=?,URI=?,ACTION=?"
						+ " where GUID=?";
				
				PreparedStatement pstmtInner = connection.prepareStatement(sql);
				
				pstmtInner.setString(1, viewverBean.getRequestTimestamp());
				pstmtInner.setString(2, viewverBean.getResponseTimestamp());
				pstmtInner.setString(3, viewverBean.getUri());
				pstmtInner.setString(4, viewverBean.getAction());
				pstmtInner.setString(5, viewverBean.getGuid());
				
				if (pstmtInner.executeUpdate() > 0) {
					
					System.out.println("Row updated successfully GUID:" + viewverBean.toString());
					
				} else {
					
					System.out.println("Row NOT updated successfully GUID:" + viewverBean.toString());
				}
				
				if (pstmtInner != null) pstmtInner.close();
				
			} else {
				
				
				sql = "insert into TEST (GUID,TIMEREQ,TIMERESP,URI,ACTION) "
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
