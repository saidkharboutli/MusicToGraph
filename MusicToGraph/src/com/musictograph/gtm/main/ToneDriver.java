package com.musictograph.gtm.main;

import javax.sound.sampled.LineUnavailableException;

import com.musictograph.gtm.Computable;
import com.musictograph.gtm.audio.StdAudio;
import com.musictograph.gtm.audio.Tone;

public class ToneDriver {
	public static void main(String args[]) throws LineUnavailableException
	{
//		for(int hz = 260; hz <= 490; hz+=15.5)
//		{
//	        double duration = .25;
//	        double[] a = Tone.tone(hz, duration);
//	        StdAudio.play(a);
//		}
		
		double dub[] = {2, 2, 6};
		Computable computable = new Computable(dub);
		for(double x = -100; x <= 100; x+=.10)
		{
			if(computable.compute(x) <= 700 && computable.compute(x) >= 150)
			{
				double info[] = Tone.tone(computable.compute(x), .10);
				StdAudio.play(info);
				//System.out.println("" + x + ": " + computable.compute(x));
			}
		}
	}
}
