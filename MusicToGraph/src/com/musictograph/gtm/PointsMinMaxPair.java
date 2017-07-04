package com.musictograph.gtm;

import java.awt.geom.Point2D;

public class PointsMinMaxPair {
	private Point2D.Double[] points;
	private double[] minMax;

	public PointsMinMaxPair(Point2D.Double[] points, double[] minMax) {
		this.points = points;
		this.minMax = minMax;
	}

	public Point2D.Double[] getPoints() {
		return points;
	}

	public double[] getMinMax() {
		return minMax;
	}
}