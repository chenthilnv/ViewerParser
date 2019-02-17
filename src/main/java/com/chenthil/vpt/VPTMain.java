package com.chenthil.vpt;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import com.chenthil.vpt.controller.ParserController;
import com.chenthil.vpt.controller.ViewLoaderController;
import com.chenthil.vpt.util.Validator;

public class VPTMain {

	private JFrame frame;
	private JTextField textField;
	private JTable table;



	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VPTMain window = new VPTMain();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public VPTMain() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 1243, 721);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblFilePath = new JLabel("File Path");
		lblFilePath.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblFilePath.setBounds(441, 28, 157, 56);
		frame.getContentPane().add(lblFilePath);
		
		textField = new JTextField();
		textField.setBounds(539, 43, 328, 34);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JButton btnParseFile = new JButton("Parse File Into DB");
		btnParseFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String fileName = textField.getText();
				if (Validator.doesFileExist(fileName)) {
					ParserController parserController = new ParserController();
					parserController.parseFile(textField.getText());
					JOptionPane.showMessageDialog(null, "File:" + fileName + " parsed and loaded into DB");
				} else {
					JOptionPane.showMessageDialog(null, "File:" + fileName + " doesn't exist");
				}
				
			}
		});
		btnParseFile.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnParseFile.setBackground(Color.GREEN);
		btnParseFile.setBounds(311, 114, 227, 56);
		frame.getContentPane().add(btnParseFile);
		
		
	
		
		JButton btnLoadList = new JButton("Load List from DB");
		btnLoadList.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				String[] headers = new String[] {
			            "Guid", "TimeReq", "TimeResp", "Uri", "Action"
			        };
				 
		        DefaultTableModel dtm = new DefaultTableModel(0, 0);
		        dtm.setColumnIdentifiers(headers);
		        
		        ViewLoaderController viewLoaderController = new ViewLoaderController();
		        
		        List<Object[]> returnList = viewLoaderController.loadDataFromDB();
		        
		        if (returnList.size() > 0) {
		      
		        	for (Object[] object : returnList) {
				    	  
				    	  dtm.addRow(object);  
				    	  
				      }
		        	
		        } else {
		        	
		        	JOptionPane.showMessageDialog(null, "No data to load");
		        	
		        }
		        
		      
		        
		        table.setModel(dtm);
		        
				
			}
		});
		btnLoadList.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnLoadList.setBackground(Color.GREEN);
		btnLoadList.setBounds(621, 114, 227, 56);
		frame.getContentPane().add(btnLoadList);
		
		
	         
		  //actual data for the table in a 2d array
        Object[][] data = new Object[][] { };
        String[] headers = new String[] {
	            "Guid", "TimeReq", "TimeResp", "Uri", "Action"
	        };
	
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(28, 215, 1190, 442);
		frame.getContentPane().add(scrollPane);
		
		table = new JTable(data,headers);
		scrollPane.setViewportView(table);
		
	}
}
