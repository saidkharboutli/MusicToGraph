package com.musictograph.gtm;

import javax.swing.JFrame;

import com.musictograph.gtm.audio.StdAudio;
import com.musictograph.gtm.audio.Tone;
import com.musictograph.gtm.evalex.Expression;

import de.erichseifert.gral.data.DataTable;
import de.erichseifert.gral.plots.XYPlot;
import de.erichseifert.gral.plots.lines.DefaultLineRenderer2D;
import de.erichseifert.gral.plots.lines.LineRenderer;
import de.erichseifert.gral.ui.InteractivePanel;

public class Graph extends JFrame {

	private DataTable funcDataTable = new DataTable(Double.class, Double.class);
	
	public Graph(String expression) {
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(1200, 1200);
		
		for(double x = -500; x <= 500; x+=.15)
		{
			funcDataTable.add(x, new Expression(expression).with("x", "" + x).eval().doubleValue());
		}
		
		XYPlot plotFull = new XYPlot(funcDataTable);
		getContentPane().add(new InteractivePanel(plotFull));
		LineRenderer lines = new DefaultLineRenderer2D();
		plotFull.setLineRenderers(funcDataTable, lines);
	}
	
}
