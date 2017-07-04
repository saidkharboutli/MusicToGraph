package com.musictograph.gtm.main;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;

public class Test {
	public static void main(String args[]) throws LineUnavailableException {
		byte[] buf = new byte[1];
		AudioFormat af = new AudioFormat((float) 44100, 8, 1, true, false);
		SourceDataLine sdl = AudioSystem.getSourceDataLine(af);
		sdl.open();
		sdl.start();
		for(int c = 200; c < 650; c+=5)
		{
			for (int i = 0; i < 500 * (float) 44100 / 1000; i++) {
				double angle = i / ((float) 44100 / c) * 2.0 * Math.PI;
				buf[0] = (byte) (Math.sin(angle) * 50);
				sdl.write(buf, 0, 1);
			}
		}
		sdl.drain();
		sdl.stop();
	}
}
