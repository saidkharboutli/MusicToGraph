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
import java.awt.Toolkit;

public class MusicToGraph extends JFrame {

	private JPanel contentPane;
	private JTextField filePathInput;

	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 */
	public MusicToGraph() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(MusicToGraph.class.getResource("/logo.png")));
		setTitle("Music --> Graph");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 150);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		filePathInput = new JTextField();
		filePathInput.setBounds(85, 54, 339, 20);
		contentPane.add(filePathInput);
		filePathInput.setColumns(10);
		
		JLabel filePathLabel = new JLabel("File Path:");
		filePathLabel.setFont(new Font("DialogInput", Font.PLAIN, 12));
		filePathLabel.setBounds(10, 53, 76, 20);
		contentPane.add(filePathLabel);
		
		JButton loadButton = new JButton("Load...");
		loadButton.setBounds(335, 85, 89, 23);
		contentPane.add(loadButton);
		
		JTextPane mToGInfoPane = new JTextPane();
		mToGInfoPane.setEditable(false);
		mToGInfoPane.setBackground(SystemColor.menu);
		mToGInfoPane.setFont(new Font("DialogInput", Font.PLAIN, 11));
		mToGInfoPane.setText("This will convert your audio files into a graph! Enter the file path below:");
		mToGInfoPane.setBounds(10, 5, 287, 38);
		contentPane.add(mToGInfoPane);
	}

}
