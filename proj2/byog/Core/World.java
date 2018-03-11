package byog.Core;

import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

import java.util.Random;

public class World {
    /**
     * This class constructs the whole game elements.
     * The main procedures are:
     * 1. Build a single room
     * 2. Build a single hallway
     * 3. Figure out how to connect and arrange hallways and rooms
     * ......
     */
    private static final int WIDTH = 60;
    private static final int HEIGHT = 50;
    private Random r;

    World(long seed) {
        r = new Random(seed);
    }

    /**
     * Initialize the tiles.
     *
     * @param world
     */
    public static void initialize(TETile[][] world) {
        for (int x = 0; x < WIDTH; x += 1) {
            for (int y = 0; y < HEIGHT; y += 1) {
                world[x][y] = Tileset.NOTHING;
            }
        }
    }

    private static Position newPosition(Position p, int[] positionDeviation) {
        int newX = p.xPosition + positionDeviation[0];
        int newY = p.yPosition + positionDeviation[1];
        return new Position(newX, newY);
    }

    private static Position endPosition(Position startPosition, int x, int y) {
        int xPosition = startPosition.xPosition + x + 1;
        int yPosition = startPosition.yPosition + y + 1;
        return new Position(xPosition, yPosition);
    }

    private static void drawFloor(TETile[][] world, Position p, int x, int y) {
        for (int i = 1; i <= x; i += 1) {
            for (int j = 1; j <= y; j += 1) {
                world[p.xPosition + i][p.yPosition + j] = Tileset.FLOOR;
            }
        }
    }

    private static void drawWall(TETile[][] world, Position p, int x, int y) {
        for (int i = 0; i <= x + 1; i += 1) {
            for (int j = 0; j <= y + 1; j += 1) {
                int xp = p.xPosition + i;
                int yp = p.yPosition + j;
                if (!world[xp][yp].equals(Tileset.FLOOR)) {
                    world[xp][yp] = Tileset.FLOWER;
                }
            }
        }
    }

    /**
     * Build a single room.
     * The room is a rectangular.
     * The room's entrance......change as hallway is added.
     */
    private int[] deviationDistance(Position p, int x, int y) {
        int startX = r.nextInt(x) + 1;
        int startY = r.nextInt(y) + 1;
        int xp = p.xPosition;
        int yp = p.yPosition;
        if (xp - startX < 5 || yp - startY < 5) {
            return deviationDistance(p, x, y);
        }
        int[] positionDeviation = new int[]{startX, startY};
        return positionDeviation;
    }

    private Position startPosition(Position p, int[] positionDeviation) {
        int startX = p.xPosition - positionDeviation[0];
        int startY = p.yPosition - positionDeviation[1];
        Position startPosition = new Position(startX, startY);
        return startPosition;
    }

    public Position drawRoom(TETile[][] world, Position p) {
        int sizeLimit = 10;
        int xLength = r.nextInt(sizeLimit) + 2;
        int yLength = r.nextInt(sizeLimit) + 2;
        int[] deviationDistance1 = deviationDistance(p, xLength, yLength);
        Position startPosition = startPosition(p, deviationDistance1);
        Position endPosition = endPosition(startPosition, xLength, yLength);
        if (endPosition.xPosition <= WIDTH - 1 && endPosition.yPosition <= HEIGHT - 1) {
            drawWall(world, startPosition, xLength, yLength);
            drawFloor(world, startPosition, xLength, yLength);
            int[] deviationDistance2 = deviationDistance(p, xLength, yLength);
            Position nextPosition = newPosition(startPosition, deviationDistance2);
            return nextPosition;
        } else {
            return drawRoom(world, p);
        }
    }

    /**
     * Build a single hallway.
     * the width of the hallway should be one.
     * the ends of the hallway should be floor rather than walls.
     * hallways can be vertical or horizontal.
     *
     * @param world
     * @param p
     */
    public Position drawVerticalHallway(TETile[][] world, Position p) {
        int sizeLimit = 20;
        int xLength = 1;
        int yLength = r.nextInt(sizeLimit) + 5;
        int[] deviationDistance1 = deviationDistance(p, xLength, yLength);
        Position startPosition = startPosition(p, deviationDistance1);
        Position endPosition = endPosition(startPosition, xLength, yLength);
        if (endPosition.xPosition <= WIDTH - 1 && endPosition.yPosition <= HEIGHT - 1) {
            drawWall(world, startPosition, xLength, yLength);
            drawFloor(world, startPosition, xLength, yLength);
            int[] deviationDistance2 = deviationDistance(p, xLength, yLength);
            Position nextPosition = newPosition(startPosition, deviationDistance2);
            return nextPosition;
        } else {
            return drawVerticalHallway(world, p);
        }
    }

    public Position drawHorizontalHallway(TETile[][] world, Position p) {
        int sizeLimit = 20;
        int xLength = r.nextInt(sizeLimit) + 5;
        int yLength = 1;
        int[] deviationDistance1 = deviationDistance(p, xLength, yLength);
        Position startPosition = startPosition(p, deviationDistance1);
        Position endPosition = endPosition(startPosition, xLength, yLength);
        if (endPosition.xPosition <= WIDTH - 1 && endPosition.yPosition <= HEIGHT - 1) {
            drawWall(world, startPosition, xLength, yLength);
            drawFloor(world, startPosition, xLength, yLength);
            int[] deviationDistance2 = deviationDistance(p, xLength, yLength);
            Position nextPosition = newPosition(startPosition, deviationDistance2);
            return nextPosition;
        } else {
            return drawHorizontalHallway(world, p);
        }
    }

    public Position randomDraw(TETile[][] world, Position p) {
        int index = r.nextInt(3);
        switch (index) {
            case 0:
                return drawHorizontalHallway(world, p);
            case 1:
                return drawVerticalHallway(world, p);
            case 2:
                return drawRoom(world, p);
            default:
                return drawRoom(world, p);
        }
    }

    public TETile[][] createRandomWorld(TETile[][] world) {
        int x = 15;
        int y = 15;
        int objectNum = r.nextInt(30) + 30;
        Position p = new Position(x, y);
        for (int i = 0; i < objectNum; i += 1) {
            p = randomDraw(world, p);
        }
        return world;
    }
}
