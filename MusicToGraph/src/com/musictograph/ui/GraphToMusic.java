package com.musictograph.ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.musictograph.gtm.Computable;
import com.musictograph.gtm.Graph;
import com.musictograph.gtm.audio.StdAudio;
import com.musictograph.gtm.audio.Tone;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
	private JTextField durationInput;

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
		gToMInfoPane.setText(
				"This will convert your function into an audio file! Enter the coeffecients below (include negatives):");
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
		processButton.setBounds(335, 85, 89, 23);
		contentPane.add(processButton);
		processButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					double dub[] = new double[3];
					dub[0] = Double.parseDouble(aInput.getText());
					dub[1] = Double.parseDouble(bInput.getText());
					dub[2] = Double.parseDouble(cInput.getText());
					Graph graph = new Graph(new Computable(dub));
					graph.setVisible(true);
					for (double x = -50; x <= 50; x += .25) {
						if (new Computable(dub).compute(x) > 150 && new Computable(dub).compute(x) < 700) {
							StdAudio.play(Tone.tone(new Computable(dub).compute(x),
									Double.parseDouble(durationInput.getText())));
						}
					}
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		});

		JButton backButton = new JButton("Back");
		backButton.setBounds(10, 85, 89, 23);
		contentPane.add(backButton);

		JLabel durationLabel = new JLabel("Duration:");
		durationLabel.setBounds(180, 89, 46, 14);
		contentPane.add(durationLabel);

		durationInput = new JTextField();
		durationInput.setBounds(236, 86, 86, 20);
		contentPane.add(durationInput);
		durationInput.setColumns(10);
		backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Main().setVisible(true);
				dispose();
			}
		});
	}

}
