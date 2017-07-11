package com.musictograph.ui;

import javax.swing.JFrame;

import de.erichseifert.gral.data.DataTable;
import de.erichseifert.gral.plots.XYPlot;
import de.erichseifert.gral.plots.lines.DefaultLineRenderer2D;
import de.erichseifert.gral.plots.lines.LineRenderer;
import de.erichseifert.gral.ui.InteractivePanel;

public class Graph extends JFrame {

	private DataTable myFuncDataTable;
	private InteractivePanel panel;

	public Graph(DataTable funcDataTable) {
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setTitle("Graph...");
		setSize(1200, 1200);
		myFuncDataTable = funcDataTable;

		XYPlot plotFull = new XYPlot(myFuncDataTable);
		panel = new InteractivePanel(plotFull);
		getContentPane().add(panel);
		LineRenderer lines = new DefaultLineRenderer2D();
		plotFull.setLineRenderers(funcDataTable, lines);
	}
}
