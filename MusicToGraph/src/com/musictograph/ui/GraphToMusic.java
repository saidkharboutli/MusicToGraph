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
import javax.swing.JButton;

public class GraphToMusic extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;


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
		txtpnThisWillConvert.setEditable(false);
		txtpnThisWillConvert.setText("This will convert your function into an audio file! Enter the coeffecients below (include negatives):");
		txtpnThisWillConvert.setFont(new Font("DialogInput", Font.PLAIN, 11));
		txtpnThisWillConvert.setBackground(SystemColor.menu);
		txtpnThisWillConvert.setBounds(10, 5, 381, 38);
		contentPane.add(txtpnThisWillConvert);
		
		JLabel lblX = new JLabel("x^2");
		lblX.setBounds(43, 54, 25, 20);
		contentPane.add(lblX);
		
		textField = new JTextField();
		textField.setBounds(10, 53, 31, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel label = new JLabel("+");
		label.setBounds(69, 56, 16, 14);
		contentPane.add(label);
		
		textField_1 = new JTextField();
		textField_1.setBounds(80, 53, 31, 20);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblX_1 = new JLabel("x");
		lblX_1.setBounds(113, 57, 16, 14);
		contentPane.add(lblX_1);
		
		JLabel lblNewLabel = new JLabel("+");
		lblNewLabel.setBounds(122, 56, 16, 14);
		contentPane.add(lblNewLabel);
		
		textField_2 = new JTextField();
		textField_2.setBounds(132, 54, 31, 20);
		contentPane.add(textField_2);
		textField_2.setColumns(10);
		
		JButton processButton = new JButton("Process");
		processButton.setBounds(335, 78, 89, 23);
		contentPane.add(processButton);
	}

}
