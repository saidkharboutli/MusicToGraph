package com.musictograph.mtg;

import java.awt.Color;

import javax.swing.JFrame;
import org.apache.commons.math3.stat.regression.SimpleRegression;

import de.erichseifert.gral.data.DataTable;
import de.erichseifert.gral.plots.XYPlot;
import de.erichseifert.gral.plots.lines.AbstractLineRenderer2D;
import de.erichseifert.gral.plots.lines.DefaultLineRenderer2D;
import de.erichseifert.gral.plots.lines.LineRenderer;
import de.erichseifert.gral.ui.InteractivePanel;

public class Graph extends JFrame{
	DataTable data = new DataTable(Double.class, Double.class);
	DataTable dataFull = new DataTable(Double.class, Double.class);
	double[] pitch = new double[Pitch.pitches.getItemCount()];
	double[] time = new double[Pitch.pitches.getItemCount()];
	int position = 0;
	int counter = 0;
	double totalx = 0; 
	public Graph(boolean simple){
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	    setSize(1200, 1200);
	    
	    for(int i = 0; i < Pitch.pitches.getItemCount(); i++){
			pitch[i] = Double.valueOf(Pitch.pitches.getItem(i));
			time[i] = Double.valueOf(Pitch.time.getItem(i));
			//time = time * 200;
			
		}
	    for (int w = 0; w < pitch.length;w++){
    		dataFull.add(time[w], pitch[w]);
    		System.out.println("pitch: " + pitch[w] + " and " + time[w]);
	    }
    
    
	    int added = 0;
	    for(int i = 0; i < Pitch.pitches.getItemCount()/8; i++){
	    	
	    	 SimpleRegression Regression = new SimpleRegression();
	    	 double x = 0;
	    	 
	    	 for(int s = 0; s < 8; s++){
	    		position++; 
	    		try{
	    		Regression.addData(time[position], pitch[position]);
	    		x = x + time[position];
	    	    added++;
	    		}
	    		catch(Exception e){
	    			x = x;
	    		}
	    	}
	    	double average = x/8;
	    	data.add(average, Regression.predict(average));
	    	//System.out.println("Predictedx: " + average + " Predictedy: " + Regression.predict(average));
	    	//System.out.println(added);
	    	
	    }
	  
	    
	    XYPlot plot = new XYPlot(data);
	    XYPlot plotFull = new XYPlot(dataFull);
	    LineRenderer lines = new DefaultLineRenderer2D();
	    
	    if(simple){
	    	getContentPane().add(new InteractivePanel(plot));
	    	plot.setLineRenderers(data, lines);
	    }
	    else{
	    	getContentPane().add(new InteractivePanel(plotFull));
	    	plotFull.setLineRenderers(dataFull, lines);
	    }
        Color color = new Color(0.0f, 0.3f, 1.0f);
        
        //plot.getAxisRenderer(XYPlot.AXIS_X).setTickSpacing(5.0);
        //plot.getAxisRenderer(XYPlot.AXIS_Y).setTickSpacing(100);
	
	}
}

