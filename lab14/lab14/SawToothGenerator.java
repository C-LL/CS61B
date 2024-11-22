package lab14;

import edu.princeton.cs.algs4.StdAudio;
import lab14lib.Generator;

public class SawToothGenerator implements Generator {
    private double period;
    private int state;

    public SawToothGenerator(double period) {
        state = 0;
        this.period = period;
    }

    public double next() {
        state = (state + 1);
        return normalized(state % period, period);
    }

    public static double normalized(double x, double period) {
        return 2 * x / period - 1;
    }
}