package com.musictograph.ui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.musictograph.gtm.Expressions;
import com.musictograph.gtm.Graph;
import com.musictograph.gtm.PointsMinMaxPair;
import com.musictograph.gtm.audio.StdAudio;
import com.musictograph.gtm.audio.Tone;

import de.erichseifert.gral.data.DataTable;
import net.objecthunter.exp4j.ExpressionBuilder;
import net.objecthunter.exp4j.tokenizer.UnknownFunctionOrVariableException;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextPane;
import java.awt.SystemColor;
import java.awt.Font;
import java.awt.geom.Point2D;

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
		durationSlider.setValue(125);
		durationSlider.setMaximum(250);
		durationSlider.setMinimum(1);
		durationSlider.setBounds(190, 85, 142, 23);
		contentPane.add(durationSlider);

		JButton processButton = new JButton("Process");
		processButton.setBounds(335, 85, 89, 23);
		contentPane.add(processButton);
		processButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DataTable funcDataTable = new DataTable(Double.class, Double.class);
				Graph graph = new Graph(funcDataTable);
				graph.setVisible(true);
				
				PointsMinMaxPair pair = Expressions.producePoints(-15, 15, expressionInput.getText());
				
				double[] minMax = pair.getMinMax();
				Point2D.Double[] points = pair.getPoints();
				
				for(int c = 0; c < points.length; c++)
				{
					graph.addPoint(points[c].getX(), points[c].getY());
					double[] sample = Tone.tone(Expressions.scale(points[c].getY(), minMax[0], minMax[1], 250, 750), durationSlider.getValue() / 1000.0);
					StdAudio.play(sample);
					System.out.println();
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
