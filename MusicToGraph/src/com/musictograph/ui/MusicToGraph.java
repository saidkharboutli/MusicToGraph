package com.musictograph.ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.musictograph.mtg.Graph;
import com.musictograph.mtg.MyPitchDetector;
import com.musictograph.mtg.Pitch;

import be.tarsos.dsp.AudioDispatcher;
import be.tarsos.dsp.io.jvm.JVMAudioInputStream;
import be.tarsos.dsp.pitch.PitchProcessor;
import be.tarsos.dsp.pitch.PitchProcessor.PitchEstimationAlgorithm;

import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Font;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JButton;
import javax.swing.JTextPane;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class MusicToGraph extends JFrame {

	private JPanel contentPane;
	private JTextField filePathInput;

	public MusicToGraph() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(MusicToGraph.class.getResource("/logo.png")));
		setTitle("Music --> Graph");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 150);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		filePathInput = new JTextField();
		filePathInput.setBounds(85, 54, 339, 20);
		contentPane.add(filePathInput);
		filePathInput.setColumns(10);

		JLabel filePathLabel = new JLabel("File Path:");
		filePathLabel.setFont(new Font("DialogInput", Font.PLAIN, 12));
		filePathLabel.setBounds(10, 53, 76, 20);
		contentPane.add(filePathLabel);

		JButton loadButton = new JButton("Load...");
		loadButton.setBounds(335, 85, 89, 23);
		contentPane.add(loadButton);
		loadButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!(filePathInput.getText() == "")) {
					try {
						System.out.println("memes");
						float sampleRate = 44100;
						int audioBufferSize = 4096;
						int bufferOverlap = 0;

						// Create an AudioInputStream from my .wav file

						AudioInputStream stream = AudioSystem.getAudioInputStream(new File(filePathInput.getText()));

						// Convert into TarsosDSP API
						JVMAudioInputStream audioStream = new JVMAudioInputStream(stream);
						AudioDispatcher dispatcher = new AudioDispatcher(audioStream, audioBufferSize, bufferOverlap);
						MyPitchDetector myPitchDetector = new MyPitchDetector();
						dispatcher.addAudioProcessor(new PitchProcessor(PitchEstimationAlgorithm.YIN, sampleRate,
								audioBufferSize, myPitchDetector));
						dispatcher.run();

						int size = Pitch.time.getItemCount();
						double[] time = new double[size];
						float[] pitch = new float[Pitch.pitches.getItemCount()];

						for (int i = 0; i < Pitch.pitches.getItemCount(); i++) {
							System.out.println(
									"Pitch: " + Pitch.pitches.getItem(i) + " and time: " + Pitch.time.getItem(i));
						}
						Graph frame = new Graph(true);
						Graph frame2 = new Graph(false);
						frame.setVisible(true);
						frame2.setVisible(true);

					} catch (FileNotFoundException fne) {
						System.out.println(1);
						fne.printStackTrace();
					}

					catch (UnsupportedAudioFileException uafe) {
						System.out.println(2);
						uafe.printStackTrace();
					} catch (IOException ie) {
						System.out.println(3);
						ie.printStackTrace();
					}
				}
			}
		});

		JTextPane mToGInfoPane = new JTextPane();
		mToGInfoPane.setEditable(false);
		mToGInfoPane.setBackground(SystemColor.menu);
		mToGInfoPane.setFont(new Font("DialogInput", Font.PLAIN, 11));
		mToGInfoPane.setText("This will convert your audio files into a graph! Enter the file path below:");
		mToGInfoPane.setBounds(10, 5, 287, 38);
		contentPane.add(mToGInfoPane);

		JButton backButton = new JButton("Back");
		backButton.setBounds(10, 85, 89, 23);
		contentPane.add(backButton);
		backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Main().setVisible(true);
				dispose();
			}
		});
	}

}
