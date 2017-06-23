package com.musictograph.ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JTextPane;
import java.awt.SystemColor;

public class MusicToGraph extends JFrame {

	private JPanel contentPane;
	private JTextField textField;

	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 */
	public MusicToGraph() {
		setTitle("Music <-> Graph");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 150);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(85, 54, 339, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblFilePath = new JLabel("File Path:");
		lblFilePath.setFont(new Font("DialogInput", Font.PLAIN, 12));
		lblFilePath.setBounds(10, 53, 76, 20);
		contentPane.add(lblFilePath);
		
		JButton btnLoad = new JButton("Load...");
		btnLoad.setBounds(335, 85, 89, 23);
		contentPane.add(btnLoad);
		
		JTextPane txtpnThisWillConvert = new JTextPane();
		txtpnThisWillConvert.setBackground(SystemColor.menu);
		txtpnThisWillConvert.setFont(new Font("DialogInput", Font.PLAIN, 11));
		txtpnThisWillConvert.setText("This will convert your audio files into a graph! Enter the file path below:");
		txtpnThisWillConvert.setBounds(10, 5, 287, 38);
		contentPane.add(txtpnThisWillConvert);
	}

}
