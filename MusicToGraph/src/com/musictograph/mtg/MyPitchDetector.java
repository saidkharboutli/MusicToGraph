package com.musictograph.mtg;

import be.tarsos.dsp.AudioEvent;
import be.tarsos.dsp.pitch.PitchDetectionHandler;
import be.tarsos.dsp.pitch.PitchDetectionResult;

public class MyPitchDetector implements PitchDetectionHandler {

	public void handlePitch(PitchDetectionResult pitchDetectionResult, AudioEvent audioEvent) {
		if (pitchDetectionResult.getPitch() != -1) {
			double timeStamp = audioEvent.getTimeStamp();
			float pitch = pitchDetectionResult.getPitch();

			float probability = pitchDetectionResult.getProbability();
			double rms = audioEvent.getRMS() * 100;
			String message = String.format("Pitch detected at %.2fs: %.2fHz ( %.2f probability, RMS: %.5f )\n",
					timeStamp, pitch, probability, rms);
			System.out.println(message);
			String addMe1;
			if (probability < 0.5 && Pitch.pitches.getItemCount() > 2) {
				addMe1 = Pitch.pitches.getItem(Pitch.pitches.getItemCount() - 1);
			} else {
				addMe1 = String.valueOf(pitch);
			}
			String addMe2 = String.valueOf(timeStamp);
			Pitch.pitches.add(addMe1);
			Pitch.time.add(addMe2);

		}
	} // TODO Auto-generated method stub
}
