package lab14;

import lab14lib.*;

import java.util.ArrayList;

public class Main {
	public static void main(String[] args) {
		/** Your code here. */
//		Generator generator = new SineWaveGenerator(440);
//		GeneratorPlayer gp = new GeneratorPlayer(generator);
//		gp.play(1000000);

//		Generator generator = new SineWaveGenerator(200);
//		GeneratorDrawer gd = new GeneratorDrawer(generator);
//		gd.draw(4096);

//		Generator generator = new SineWaveGenerator(200);
//		GeneratorAudioVisualizer gav = new GeneratorAudioVisualizer(generator);
//		gav.drawAndPlay(4096, 1000000);

//		Generator g1 = new SineWaveGenerator(200);
//		Generator g2 = new SineWaveGenerator(201);
//
//		ArrayList<Generator> generators = new ArrayList<Generator>();
//		generators.add(g1);
//		generators.add(g2);
//		MultiGenerator mg = new MultiGenerator(generators);
//
//		GeneratorAudioVisualizer gav = new GeneratorAudioVisualizer(mg);
//		gav.drawAndPlay(500000, 1000000);

//		// Test SawToothGenerator
//		Generator generator = new SawToothGenerator(512);
//		GeneratorAudioVisualizer gav = new GeneratorAudioVisualizer(generator);
//		gav.drawAndPlay(4096, 1000000);

//		// Test AcceleratingSawToothGenerator
//		Generator generator = new AcceleratingSawToothGenerator(200, 1.1);
//		GeneratorAudioVisualizer gav = new GeneratorAudioVisualizer(generator);
//		gav.drawAndPlay(4096, 1000000);

 		// Test StrangeBitwiseGenerator
		Generator generator = new StrangeBitwiseGenerator(512);
		GeneratorAudioVisualizer gav = new GeneratorAudioVisualizer(generator);
		gav.drawAndPlay(4096, 1000000);
	}
} 