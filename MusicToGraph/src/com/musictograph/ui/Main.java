package com.musictograph.ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextPane;
import java.awt.Font;
import java.awt.Image;
import java.awt.SystemColor;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main extends JFrame {

	private JPanel contentPane;
	private JTextField byText;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main frame = new Main();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Main() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(Main.class.getResource("/logo.png")));
		setTitle("Music <-> Graph");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 390);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JTextPane welcomeText = new JTextPane();
		welcomeText.setToolTipText("Genesis API");
		welcomeText.setEditable(false);
		welcomeText.setText("Welcome to Music <-> Graph");
		welcomeText.setBackground(SystemColor.menu);
		welcomeText.setFont(new Font("DialogInput", Font.PLAIN, 28));
		welcomeText.setBounds(62, 11, 457, 54);
		contentPane.add(welcomeText);

		byText = new JTextField();
		byText.setEditable(false);
		byText.setBackground(SystemColor.menu);
		byText.setFont(new Font("DialogInput", Font.PLAIN, 14));
		byText.setText("By Sa'id Kharboutli and Thomas Han");
		byText.setBounds(159, 76, 281, 20);
		contentPane.add(byText);
		byText.setColumns(10);

		JButton graphToMusicButton = new JButton("Graph -> Music");
		graphToMusicButton.setBounds(10, 291, 150, 23);
		contentPane.add(graphToMusicButton);
		graphToMusicButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				new GraphToMusic().setVisible(true);
				dispose();
			}
		});

		JButton musicToGraphButton = new JButton("Music -> Graph");
		musicToGraphButton.setBounds(424, 291, 150, 23);
		contentPane.add(musicToGraphButton);
		musicToGraphButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				new MusicToGraph().setVisible(true);
				dispose();
			}
		});

		JButton imFeelingLuckyButton = new JButton("I'm Feeling Lucky");
		imFeelingLuckyButton.setBounds(216, 318, 150, 23);
		contentPane.add(imFeelingLuckyButton);
		imFeelingLuckyButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int rand = (int) Math.random();
				if (rand == 1) {
					setVisible(false);
					new GraphToMusic().setVisible(true);
					dispose();
				} else if (rand == 0) {
					setVisible(false);
					new MusicToGraph().setVisible(true);
					dispose();
				}
			}
		});
		JLabel imageLabel = new JLabel("");
		imageLabel.setIcon(new ImageIcon(new ImageIcon(Main.class.getResource("/logo.png")).getImage()
				.getScaledInstance(250, 175, Image.SCALE_DEFAULT)));
		imageLabel.setBounds(159, 95, 281, 184);
		contentPane.add(imageLabel);
	}
}
