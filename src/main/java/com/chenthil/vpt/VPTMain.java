package com.chenthil.vpt;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JDesktopPane;
import javax.swing.JDialog;

import java.awt.BorderLayout;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import java.awt.Color;
import javax.swing.border.CompoundBorder;

import com.chenthil.vpt.controller.ParserController;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

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
		frame.setBounds(100, 100, 762, 426);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblFilePath = new JLabel("File Path");
		lblFilePath.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblFilePath.setBounds(186, 28, 157, 56);
		frame.getContentPane().add(lblFilePath);
		
		textField = new JTextField();
		textField.setBounds(273, 43, 328, 34);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JButton btnParseFile = new JButton("Parse File");
		btnParseFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ParserController parserController = new ParserController();
				parserController.parseFile(textField.getText());
			}
		});
		btnParseFile.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnParseFile.setBackground(Color.GREEN);
		btnParseFile.setBounds(95, 114, 227, 56);
		frame.getContentPane().add(btnParseFile);
		
		table = new JTable();
		table.setBackground(Color.WHITE);
		table.setBounds(48, 224, 678, 126);
		frame.getContentPane().add(table);
		
		JButton btnLoadList = new JButton("Load List");
		btnLoadList.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnLoadList.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnLoadList.setBackground(Color.GREEN);
		btnLoadList.setBounds(413, 114, 227, 56);
		frame.getContentPane().add(btnLoadList);
		
	
		
	}
}
