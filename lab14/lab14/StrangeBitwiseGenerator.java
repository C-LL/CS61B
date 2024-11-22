package lab14;

import lab14lib.Generator;

public class StrangeBitwiseGenerator implements Generator {
    private double period;
    private int state;

    public StrangeBitwiseGenerator(double period) {
        state = 0;
        this.period = period;
    }

    public double next() {
        state = (state + 1);
//        int weirdState = state & (state >>> 3) % (int) period;
        int weirdState = state & (state >> 3) & (state >> 8) % (int) period;
        return normalized(weirdState % period, period);
    }

    public static double normalized(double x, double period) {
        return 2 * x / period - 1;
    }
}