package com.musictograph.gtm;

import javax.swing.JFrame;

import de.erichseifert.gral.data.DataTable;
import de.erichseifert.gral.plots.XYPlot;
import de.erichseifert.gral.plots.lines.DefaultLineRenderer2D;
import de.erichseifert.gral.plots.lines.LineRenderer;
import de.erichseifert.gral.ui.InteractivePanel;
import net.objecthunter.exp4j.ExpressionBuilder;

public class Graph extends JFrame {

	private static final long serialVersionUID = 2L;
	
	@SuppressWarnings("unchecked")
	private DataTable funcDataTable = new DataTable(Double.class, Double.class);

	public Graph(String expression) {
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setTitle("Graph...");
		setSize(1200, 1200);

		for (double x = -10; x <= 10; x += .05) {
			try {
				funcDataTable.add(x,
						new ExpressionBuilder(expression).variables("x").build().setVariable("x", x).evaluate());
			} catch (ArithmeticException e) {}
		}

		XYPlot plotFull = new XYPlot(funcDataTable);
		plotFull.autoscaleAxis(XYPlot.AXIS_X);
		plotFull.autoscaleAxis(XYPlot.AXIS_Y);
		getContentPane().add(new InteractivePanel(plotFull));
		LineRenderer lines = new DefaultLineRenderer2D();
		plotFull.setLineRenderers(funcDataTable, lines);
	}

}
