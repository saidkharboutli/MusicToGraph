package com.musictograph.gtm.audio;

public class Tone {
	public static double[] tone(double hz, double duration) {
		int n = (int) (44100 * duration);
		double[] a = new double[n + 1];
		for (int i = 0; i <= n; i++) {
			a[i] = Math.sin(2 * Math.PI * i * hz / 44100);
		}
		return a;
	}
}
