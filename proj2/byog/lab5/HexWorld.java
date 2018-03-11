package byog.lab5;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

import java.awt.*;
import java.util.Random;

/**
 * Draws a world consisting of hexagonal regions.
 */
public class HexWorld {


    /**
     * Draw one line of appointed tiles.
     */
    private static void drawLine(TETile[][] world, int x, int y, int length, TETile t) {
        int end = x + length;
        for (int i = x; i < end; i++) {
            world[i][y] = t;
        }
    }

    /**
     * adds a hexagon of side length s to a given position in the world.
     */
    //1. initialize a world and decide the start point of the hexagon.
    //2. start fill in the space with appointed tiles, line by line.
    public static void addHexagon(TETile[][] world, Position p, int size, TETile t) {
        int total = 2 * p.yPosition + 2 * size - 1;
        int y = p.yPosition;
        int x = p.xPosition;
        int length = size;
        Random r = new Random(255);
        t = TETile.colorVariant(t, 25, 27, 25, r);
        for (int j = 1; j <= size; j++) {
            int upY = total - y;
            drawLine(world, x, y, length, t);
            drawLine(world, x, upY, length, t);
            y = y + 1;
            x = x - 1;
            length = length + 2;
        }
    }

    private static int initialX(int column, int size, Position p) {
        return p.xPosition + (column - 1) * (2 * size - 1);
    }

    private static int initialY(int column, int size, Position p) {
        if (column <= 3) {
            return p.yPosition - (column - 1) * size;
        }
        return initialY(6 - column, size, p);
    }

    private static int number(int column) {
        if (column <= 6 - column) {
            return column + 2;
        }
        return 8 - column;
    }

    /**
     * Draw the Tesselation of hexagons.
     * we can find that the tesselation is made up of 3-4-5-4-3 columns.
     *
     * @param world
     * @param p
     * @param size
     * @param t
     */
    public static void drawColumn(TETile[][] world, Position p, int size, TETile t, int column) {
        int number = number(column);
        int x = initialX(column, size, p);
        int y = initialY(column, size, p);
        for (int i = 0; i < number; i++) {
            Position currentPosition = new Position(x, y);
            addHexagon(world, currentPosition, size, t);
            y = y + 2 * size;
        }

    }

    public static void main(String[] args) {
        TERenderer ter = new TERenderer();
        int width = 80;
        int height = 100;
        ter.initialize(width, height);
        TETile[][] world = new TETile[width][height];
        for (int x = 0; x < width; x += 1) {
            for (int y = 0; y < height; y += 1) {
                world[x][y] = Tileset.NOTHING;
            }
        }
        Position p = new Position(20, 30);
        TETile t = new TETile('a', Color.yellow, Color.black, "null");
        drawColumn(world, p, 3, t, 1);
        drawColumn(world, p, 3, t, 2);
        drawColumn(world, p, 3, t, 3);
        drawColumn(world, p, 3, t, 4);
        drawColumn(world, p, 3, t, 5);
        ter.renderFrame(world);
    }

}
