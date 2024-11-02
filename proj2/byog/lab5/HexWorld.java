package byog.lab5;
import org.junit.Test;
import static org.junit.Assert.*;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

import java.util.Random;

/**
 * Draws a world consisting of hexagonal regions.
 */
public class HexWorld {
    private static final int WIDTH = 15;
    private static final int HEIGHT = 15;

    public static void addHexagon(TETile[][] world, int x, int y, int s, TETile t){
        // generate hexagon world
        int beginELement = s-1;
        for(int i = 0; i < s; i++){
            for(int j = 0; j < s+2*i; j++){
                world[x+i][y+beginELement+j] = t;
                world[x+2*s-i-1][y+beginELement+j] = t;
            }
            beginELement--;
        }
    }
    public static void main(String[] args) {
        TERenderer ter = new TERenderer();
        ter.initialize(WIDTH, HEIGHT);

        TETile grid[][] = new TETile[WIDTH][HEIGHT];
        for (int x = 0; x < WIDTH; x += 1) {
            for (int y = 0; y < HEIGHT; y += 1) {
                grid[x][y] = Tileset.NOTHING;
            }
        }
        addHexagon(grid, 0, 0, 2, Tileset.FLOWER);
        addHexagon(grid, 3, 2, 2, Tileset.WALL);
        addHexagon(grid, 6, 0, 2, Tileset.FLOWER);

        ter.renderFrame(grid);
    }
}
