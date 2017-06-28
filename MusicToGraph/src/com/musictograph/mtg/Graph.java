package com.musictograph.mtg;

import javax.swing.JFrame;
import org.apache.commons.math3.stat.regression.SimpleRegression;

import de.erichseifert.gral.data.DataTable;
import de.erichseifert.gral.plots.XYPlot;
import de.erichseifert.gral.plots.lines.DefaultLineRenderer2D;
import de.erichseifert.gral.plots.lines.LineRenderer;
import de.erichseifert.gral.ui.InteractivePanel;

public class Graph extends JFrame {
	
	private static final long serialVersionUID = 3L;
	
	@SuppressWarnings("unchecked")
	DataTable data = new DataTable(Double.class, Double.class);
	@SuppressWarnings("unchecked")
	DataTable dataFull = new DataTable(Double.class, Double.class);
	double[] pitch = new double[Pitch.pitches.getItemCount()];
	double[] time = new double[Pitch.pitches.getItemCount()];
	int position = 0;
	int counter = 0;
	double totalx = 0;

	public Graph(boolean simple) {
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(1200, 1200);

		for (int i = 0; i < Pitch.pitches.getItemCount(); i++) {
			pitch[i] = Double.valueOf(Pitch.pitches.getItem(i));
			time[i] = Double.valueOf(Pitch.time.getItem(i));
		}

		for (int w = 0; w < pitch.length; w++) {
			dataFull.add(time[w], pitch[w]);
		}

		for (int i = 0; i < Pitch.pitches.getItemCount() / 8; i++) {
			SimpleRegression Regression = new SimpleRegression();
			double x = 0;
			for (int s = 0; s < 8; s++) {
				position++;
				try {
					Regression.addData(time[position], pitch[position]);
					x = x + time[position];
				} catch (Exception e) {
					/* silence... */}
			}
			double average = x / 8;
			data.add(average, Regression.predict(average));
		}

		XYPlot plot = new XYPlot(data);
		XYPlot plotFull = new XYPlot(dataFull);
		LineRenderer lines = new DefaultLineRenderer2D();

		if (simple) {
			getContentPane().add(new InteractivePanel(plot));
			plot.setLineRenderers(data, lines);
		} else {
			getContentPane().add(new InteractivePanel(plotFull));
			plotFull.setLineRenderers(dataFull, lines);
		}
	}
}
