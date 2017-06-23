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
	private JTextField aInput;
	private JTextField bInput;
	private JTextField cInput;


	public GraphToMusic() {
		setTitle("Music <-- Graph");
		setIconImage(Toolkit.getDefaultToolkit().getImage(GraphToMusic.class.getResource("/logo.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 150);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTextPane gToMInfoPane = new JTextPane();
		gToMInfoPane.setEditable(false);
		gToMInfoPane.setText("This will convert your function into an audio file! Enter the coeffecients below (include negatives):");
		gToMInfoPane.setFont(new Font("DialogInput", Font.PLAIN, 11));
		gToMInfoPane.setBackground(SystemColor.menu);
		gToMInfoPane.setBounds(10, 5, 381, 38);
		contentPane.add(gToMInfoPane);
		
		JLabel aLabel = new JLabel("x^2");
		aLabel.setBounds(43, 54, 25, 20);
		contentPane.add(aLabel);
		
		aInput = new JTextField();
		aInput.setBounds(10, 53, 31, 20);
		contentPane.add(aInput);
		aInput.setColumns(10);
		
		JLabel firstPlusLabel = new JLabel("+");
		firstPlusLabel.setBounds(69, 56, 16, 14);
		contentPane.add(firstPlusLabel);
		
		bInput = new JTextField();
		bInput.setBounds(80, 53, 31, 20);
		contentPane.add(bInput);
		bInput.setColumns(10);
		
		JLabel bLabel = new JLabel("x");
		bLabel.setBounds(113, 57, 16, 14);
		contentPane.add(bLabel);
		
		JLabel secondPlusLabel = new JLabel("+");
		secondPlusLabel.setBounds(122, 56, 16, 14);
		contentPane.add(secondPlusLabel);
		
		cInput = new JTextField();
		cInput.setBounds(132, 54, 31, 20);
		contentPane.add(cInput);
		cInput.setColumns(10);
		
		JButton processButton = new JButton("Process");
		processButton.setBounds(335, 78, 89, 23);
		contentPane.add(processButton);
	}

}
