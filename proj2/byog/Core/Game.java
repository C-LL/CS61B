package byog.Core;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import edu.princeton.cs.introcs.StdDraw;

import java.awt.*;

public class Game {
    /* Feel free to change the width and height. */
    private boolean isloaded = false;
    /* Feel free to change the width and height. */
    public static final int WIDTH = 80;
    public static final int HEIGHT = 30;
    private long seed;

    /**
     * Method used for playing a fresh game. The game should start from the main menu.
     */
    public void playWithKeyboard() {

    }

    /**
     * Method used for autograding and testing the game code. The input string will be a series
     * of characters (for example, "n123sswwdasdassadwas", "n123sss:q", "lwww". The game should
     * behave exactly as if the user typed these characters into the game after playing
     * playWithKeyboard. If the string ends in ":q", the same world should be returned as if the
     * string did not end with q. For example "n123sss" and "n123sss:q" should return the same
     * world. However, the behavior is slightly different. After playing with "n123sss:q", the game
     * should save, and thus if we then called playWithInputString with the string "l", we'd expect
     * to get the exact same world back again, since this corresponds to loading the saved game.
     * @param input the input string to feed to your program
     * @return the 2D TETile[][] representing the state of the world
     */

    public TETile[][] playWithInputString(String input) {
        // TODO: Fill out this method to run the game using the input passed in,
        // and return a 2D tile representation of the world that would have been
        // drawn if the same inputs had been given to playWithKeyboard().
        StdDraw.clear();
        StdDraw.clear(Color.black);

        Font topFont = new Font("Monaco", Font.BOLD, 20);
        StdDraw.setFont(topFont);
        StdDraw.text(1, HEIGHT/2, "CS61B: THE GAME");
        Font midFont = new Font("Monaco", Font.BOLD, 10);
        StdDraw.setFont(midFont);
        StdDraw.text(WIDTH/2-1, HEIGHT/2, "New Game (N)");
        StdDraw.text(WIDTH/2, HEIGHT/2, "Load Game (L)");
        StdDraw.text(WIDTH/2+1, HEIGHT/2, "Quit (Q)");

        TETile[][] finalWorldFrame = null;
        return finalWorldFrame;
    }
    public static void main(String[] args) {
        Game game = new Game();
        String s = "n123sss:q";

        StdDraw.clear(Color.black);

        Font topFont = new Font("Monaco", Font.BOLD, 20);
        StdDraw.setFont(topFont);
//        StdDraw.setPenColor(Color.white);
        StdDraw.text(WIDTH-1, HEIGHT-1, "CS61B: THE GAME");
        StdDraw.line(0, HEIGHT - 2, WIDTH, HEIGHT - 2);
        StdDraw.pause(1500);

//        game.playWithInputString(s);
    }
}
