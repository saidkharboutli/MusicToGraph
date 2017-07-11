package com.musictograph.gtm;

import java.awt.geom.Point2D;

import javax.swing.JOptionPane;

import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

public class Expressions {
	
	public static PointsMinMaxPair producePoints(double xMin, double xMax, String expression) {
		java.awt.geom.Point2D.Double[] points = new Point2D.Double[Math.abs((int) ((xMax - xMin) / .1) + 1)];
		double min = Double.NaN, max = Double.NaN;
		
		Expression builtExpression = new ExpressionBuilder(expression).variables("x").build();
		
		for (double x = xMin, c = 0; x <= xMax; x += .1, c++) {
			try {
				double val = builtExpression.setVariable("x", x).evaluate();
				points[(int) c] = new Point2D.Double(x, val);

				if (Double.compare(min, Double.NaN) == 0)
					min = val;
				if (Double.compare(max, Double.NaN) == 0)
					max = val;
				if (val < min)
					min = val;
				if (val > max)
					max = val;
			} catch (ArithmeticException | IllegalArgumentException ex) {
				ex.printStackTrace();
				if (ex instanceof ArithmeticException) {
					ex.printStackTrace();
					return null;
				} else {
					ex.printStackTrace();
					Object[] options = { "OK" };
					JOptionPane.showOptionDialog(null, "BAD INPUT!", "Bad Input", JOptionPane.PLAIN_MESSAGE,
							JOptionPane.ERROR_MESSAGE, null, options, options[0]);
					return null;
				}
			}
		}
		double minMax[] = new double[2];
		minMax[0] = min;
		minMax[1] = max;
		
		return new PointsMinMaxPair(points, minMax);
	}
	
	public static double scale(final double valueIn, final double baseMin, final double baseMax, final double limitMin,
			final double limitMax) {
		return ((limitMax - limitMin) * (valueIn - baseMin) / (baseMax - baseMin)) + limitMin;
	}

	public static double[] minMax(double yArr[]) {
		double info[] = new double[2];
		double min = yArr[0], max = yArr[0];

		for (int x = 0; x < yArr.length; x++) {
			if (yArr[x] < min)
				min = yArr[x];
			if (yArr[x] > max)
				max = yArr[x];
		}

		info[0] = min;
		info[1] = max;
		return info;
	}
}
