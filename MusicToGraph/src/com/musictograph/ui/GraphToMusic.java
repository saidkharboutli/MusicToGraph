package com.musictograph.ui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.musictograph.gtm.Graph;
import com.musictograph.gtm.audio.StdAudio;
import com.musictograph.gtm.audio.Tone;

import net.objecthunter.exp4j.ExpressionBuilder;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextPane;
import java.awt.SystemColor;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JSlider;

public class GraphToMusic extends JFrame {

	private static final long serialVersionUID = 11L;
	
	private JPanel contentPane;
	private JTextField expressionInput;

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
		gToMInfoPane.setText("This will convert your expression into an audio file! Enter the expression below:");
		gToMInfoPane.setFont(new Font("DialogInput", Font.PLAIN, 11));
		gToMInfoPane.setBackground(SystemColor.menu);
		gToMInfoPane.setBounds(10, 5, 381, 38);
		contentPane.add(gToMInfoPane);

		JSlider durationSlider = new JSlider();
		durationSlider.setFont(new Font("DialogInput", Font.PLAIN, 11));
		durationSlider.setValue(500);
		durationSlider.setMaximum(500);
		durationSlider.setMinimum(1);
		durationSlider.setBounds(190, 85, 142, 23);
		contentPane.add(durationSlider);

		JButton processButton = new JButton("Process");
		processButton.setBounds(335, 85, 89, 23);
		contentPane.add(processButton);
		processButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String expression = expressionInput.getText();
					Graph graph = new Graph(expression);
					graph.setVisible(true);
					for (double x = -50; x <= 50; x += .05) {
						try {
							if (new ExpressionBuilder(expression).variables("x").build().setVariable("x", x).evaluate() >= 150
									&& new ExpressionBuilder(expression).variables("x").build().setVariable("x", x).evaluate() <= 750) {
								StdAudio.play(
										Tone.tone(new ExpressionBuilder(expression).variables("x").build().setVariable("x", x).evaluate(),
												durationSlider.getValue() / 1000.0));
							}
						} catch (ArithmeticException arithEx) {
						}
					}
				} catch (Exception ex) {
					ex.printStackTrace();
					Object[] options = { "OK" };
					JOptionPane.showOptionDialog(null, "BAD INPUT!", "Bad Input", JOptionPane.PLAIN_MESSAGE,
							JOptionPane.ERROR_MESSAGE, null, options, options[0]);
				}
			}
		});

		JButton backButton = new JButton("Back");
		backButton.setBounds(10, 85, 89, 23);
		contentPane.add(backButton);

		JLabel durationLabel = new JLabel("Duration(ms):");
		durationLabel.setFont(new Font("DialogInput", Font.PLAIN, 11));
		durationLabel.setBounds(100, 89, 97, 14);
		contentPane.add(durationLabel);

		JLabel expresssionLabel = new JLabel("Expression:");
		expresssionLabel.setFont(new Font("DialogInput", Font.PLAIN, 11));
		expresssionLabel.setBounds(10, 54, 79, 14);
		contentPane.add(expresssionLabel);

		expressionInput = new JTextField();
		expressionInput.setBounds(90, 48, 334, 26);
		contentPane.add(expressionInput);
		expressionInput.setColumns(10);

		backButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				new MainUI().setVisible(true);
				dispose();
			}
		});
	}
}
