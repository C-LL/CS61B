package lab14;

import edu.princeton.cs.algs4.StdAudio;
import lab14lib.Generator;

public class AcceleratingSawToothGenerator implements Generator {
    private double period;
    private int state;
    private double factor;

    public AcceleratingSawToothGenerator(double period, double factor) {
        state = 0;
        this.period = period;
        this.factor = factor;
    }

    public double next() {
        state = (state + 1);
        if(state % period > 0){
            state -= period;
            period *= factor;
        }
        return normalized(state % period, period);
    }

    public static double normalized(double x, double period) {
        return 2 * x / period - 1;
    }
}