package com.musictograph.gtm;

import javax.swing.JFrame;

import de.erichseifert.gral.data.DataTable;
import de.erichseifert.gral.plots.XYPlot;
import de.erichseifert.gral.plots.lines.DefaultLineRenderer2D;
import de.erichseifert.gral.plots.lines.LineRenderer;
import de.erichseifert.gral.ui.InteractivePanel;

public class Graph extends JFrame {

	private static final long serialVersionUID = 2L;
	
	private DataTable funcDataTable;
	private InteractivePanel panel;
	
	public Graph(DataTable funcDataTable) {
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setTitle("Graph...");
		setSize(1200, 1200);
		this.funcDataTable = funcDataTable;

		XYPlot plotFull = new XYPlot(this.funcDataTable);
		panel = new InteractivePanel(plotFull);
		getContentPane().add(panel);
		LineRenderer lines = new DefaultLineRenderer2D();
		plotFull.setLineRenderers(funcDataTable, lines);
	}
	
	public void addPoint(double x, double y)
	{
		funcDataTable.add(x, y);
	}
}
