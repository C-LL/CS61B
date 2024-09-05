public class GuitarHero {
    private static final double CONCERT = 440;
    private static final String keyboard = "q2we4r5ty7u8i9op-[=zxdcfvgbnjmk,.;/' ";
    private static final int totalNotes = 37;

    private static double ithConcert(int i){
        return CONCERT * Math.pow(2, (i - 24) / 12);
    }

    public static void main(String[] args) {
        /* create two guitar strings, for concert A and C */
        synthesizer.GuitarString[] string = new synthesizer.GuitarString[totalNotes];
        for(int i = 0; i < totalNotes; i++){
            string[i] = new synthesizer.GuitarString(ithConcert(i));
        }

        while (true) {

            /* check if the user has typed a key; if so, process it */
            if (StdDraw.hasNextKeyTyped()) {
                char key = StdDraw.nextKeyTyped();
                int index = keyboard.indexOf(key);
                if (index < 0){
                    System.out.println("please enter valid key : " + keyboard);
                    continue;
                }
                string[index].pluck();
            }
            double sample = 0;
            /* compute the superposition of samples */
            for(synthesizer.GuitarString string_i : string){
                sample += string_i.sample();
            }

            /* play the sample on standard audio */
            StdAudio.play(sample);

            /* advance the simulation of each guitar string by one step */
            for(synthesizer.GuitarString string_i : string){
                string_i.tic();
            }
        }
    }
}
