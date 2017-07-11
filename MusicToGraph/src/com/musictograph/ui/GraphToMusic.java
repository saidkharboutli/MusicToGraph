package com.musictograph.ui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.musictograph.gtm.Expressions;
import com.musictograph.gtm.PointsMinMaxPair;
import com.musictograph.gtm.audio.StdAudio;
import com.musictograph.gtm.audio.Tone;

import de.erichseifert.gral.data.DataTable;
import de.erichseifert.gral.plots.XYPlot;
import de.erichseifert.gral.plots.lines.DefaultLineRenderer2D;
import de.erichseifert.gral.plots.lines.LineRenderer;
import de.erichseifert.gral.ui.InteractivePanel;
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
	private JTextField xMinInput;
	private JTextField xMaxInput;
	
	public GraphToMusic() {
		setTitle("Music <-- Graph");
		setIconImage(Toolkit.getDefaultToolkit().getImage(GraphToMusic.class.getResource("/logo.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 200);
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
		durationSlider.setBounds(194, 128, 142, 23);
		contentPane.add(durationSlider);

		JButton processButton = new JButton("Process");
		processButton.setBounds(335, 128, 89, 23);
		contentPane.add(processButton);
		processButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DataTable funcDataTable = new DataTable(Double.class, Double.class);
				int xMin, xMax;
				if(xMinInput.getText().equals(""))
				{
					xMin = -15;
				} else {
					xMin = Integer.parseInt(xMinInput.getText());
				}
				
				if(xMaxInput.getText().equals("")) {
					xMax = 15;
				}
				else {
					xMax = Integer.parseInt(xMaxInput.getText());
				}
				PointsMinMaxPair pair = Expressions.producePoints(xMin, xMax, expressionInput.getText());
				
				double[] minMax = pair.getMinMax();
				Point2D.Double[] points = pair.getPoints();
				
				for(int c = 0; c < points.length; c++)
				{
					funcDataTable.add(points[c].getX(), points[c].getY());
					double val = Expressions.scale(points[c].getY(), minMax[0], minMax[1], 300, 650);
					double[] sample = Tone.tone(val, durationSlider.getValue() / 1000.0);
					StdAudio.play(sample);
				}
				Graph graph = new Graph(funcDataTable);
				graph.setVisible(true);
			}
		});

		JButton backButton = new JButton("Back");
		backButton.setBounds(10, 128, 89, 23);
		contentPane.add(backButton);

		JLabel durationLabel = new JLabel("Duration(ms):");
		durationLabel.setFont(new Font("DialogInput", Font.PLAIN, 11));
		durationLabel.setBounds(103, 132, 97, 14);
		contentPane.add(durationLabel);

		JLabel expresssionLabel = new JLabel("Expression:");
		expresssionLabel.setFont(new Font("DialogInput", Font.PLAIN, 11));
		expresssionLabel.setBounds(10, 54, 79, 14);
		contentPane.add(expresssionLabel);

		expressionInput = new JTextField();
		expressionInput.setBounds(90, 48, 334, 26);
		contentPane.add(expressionInput);
		expressionInput.setColumns(10);
		
		xMinInput = new JTextField();
		xMinInput.setBounds(103, 79, 57, 20);
		contentPane.add(xMinInput);
		xMinInput.setColumns(10);
		
		xMaxInput = new JTextField();
		xMaxInput.setBounds(268, 79, 57, 20);
		contentPane.add(xMaxInput);
		xMaxInput.setColumns(10);
		
		JLabel xMinLabel = new JLabel("Graph x-min:");
		xMinLabel.setBounds(30, 82, 63, 14);
		contentPane.add(xMinLabel);
		
		JLabel xMaxLabel = new JLabel("Graph x-max:");
		xMaxLabel.setBounds(194, 82, 79, 14);
		contentPane.add(xMaxLabel);

		backButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				new MainUI().setVisible(true);
				dispose();
			}
		});
	}
}
