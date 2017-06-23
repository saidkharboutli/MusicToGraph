package com.musictograph.ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Toolkit;
import javax.swing.JTextPane;
import java.awt.SystemColor;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class GraphToMusic extends JFrame {

	private JPanel contentPane;
	private JTextField textField;


	public GraphToMusic() {
		setTitle("Music <-- Graph");
		setIconImage(Toolkit.getDefaultToolkit().getImage(GraphToMusic.class.getResource("/logo.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 150);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTextPane txtpnThisWillConvert = new JTextPane();
		txtpnThisWillConvert.setText("This will convert your function into an audio file! Enter the coeffecients below:");
		txtpnThisWillConvert.setFont(new Font("DialogInput", Font.PLAIN, 11));
		txtpnThisWillConvert.setBackground(SystemColor.menu);
		txtpnThisWillConvert.setBounds(10, 5, 287, 38);
		contentPane.add(txtpnThisWillConvert);
		
		JLabel lblX = new JLabel("x^2");
		lblX.setBounds(45, 54, 31, 20);
		contentPane.add(lblX);
		
		textField = new JTextField();
		textField.setBounds(10, 53, 31, 20);
		contentPane.add(textField);
		textField.setColumns(10);
	}

}
