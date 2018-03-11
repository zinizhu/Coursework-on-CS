package byog.Core;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;
import edu.princeton.cs.introcs.StdDraw;

import java.awt.*;
import java.io.*;

public class Game {
    /* Feel free to change the width and height. */
    public static final int WIDTH = 80;
    public static final int HEIGHT = 30;
    private TERenderer ter = new TERenderer();
    private boolean inTheGame = true;

    private static TETile[][] loadGame() {
        File f = new File("./world.ser");
        if (f.exists()) {
            try {
                FileInputStream fs = new FileInputStream(f);
                ObjectInputStream os = new ObjectInputStream(fs);
                TETile[][] finalWorldFrame = (TETile[][]) os.readObject();
                os.close();
                return finalWorldFrame;
            } catch (FileNotFoundException e) {
                System.out.println("file not found");
                System.exit(0);
            } catch (IOException e) {
                System.out.println(e);
                System.exit(0);
            } catch (ClassNotFoundException e) {
                System.out.println("class not found");
                System.exit(0);
            }
        }

        return new TETile[60][50];
    }

    private static Position loadPosition() {
        File f = new File("./p.ser");
        if (f.exists()) {
            try {
                FileInputStream fs = new FileInputStream(f);
                ObjectInputStream os = new ObjectInputStream(fs);
                Position playerPosition = (Position) os.readObject();
                os.close();
                return playerPosition;
            } catch (FileNotFoundException e) {
                System.out.println("file not found");
                System.exit(0);
            } catch (IOException e) {
                System.out.println(e);
                System.exit(0);
            } catch (ClassNotFoundException e) {
                System.out.println("class not found");
                System.exit(0);
            }
        }

        return null;
    }

    /**
     * Method used for playing a fresh game. The game should start from the main menu.
     */
    public void playWithKeyboard() {
        StdDraw.enableDoubleBuffering();

        //Main menu
        StdDraw.setCanvasSize(60 * 16, 50 * 16);
        StdDraw.setXscale(0, 60);
        StdDraw.setYscale(0, 50);
        StdDraw.clear(Color.BLACK);
        Font largeFont = new Font("Monaco", Font.BOLD, 50);
        Font smallFont = new Font("Monaco", Font.BOLD, 30);
        StdDraw.setPenColor(Color.WHITE);
        StdDraw.setFont(largeFont);
        StdDraw.text(30, 40, "Boring Game");
        StdDraw.setFont(smallFont);
        StdDraw.text(30, 30, "New Game (N)");
        StdDraw.text(30, 25, "Load Game (L)");
        StdDraw.text(30, 20, "Quit (Q)");
        StdDraw.show();

        //user select a command
        while (true) {
            if (StdDraw.hasNextKeyTyped()) {
                char c = StdDraw.nextKeyTyped();
                switch (c) {
                    default: break;
                    case 'n':
                        TETile[][] finalWorldFrame1 = new TETile[60][50];
                        startNewGame(finalWorldFrame1);
                    case 'l':
                        //load the previous game
                        TETile[][] finalWorldFrame2 = loadGame();
                        Position playerPosition = loadPosition();
                        startOldGame(finalWorldFrame2, playerPosition);
                    case 'q':
                        //quit the game
                        System.exit(0);
                        break;
                }
            }
        }
    }

    private void control(TETile[][] finalWorldFrame, Position player) {
        finalWorldFrame[player.xPosition][player.yPosition] = Tileset.PLAYER;
        ter.renderFrame(finalWorldFrame);
        playGame(finalWorldFrame, player);
    }

    private void playGame(TETile[][] finalWorldFrame, Position player) {
        TETile originalTile;
        while (true) {
            if (StdDraw.hasNextKeyTyped()) {
                char inputCommand = StdDraw.nextKeyTyped();
                if (inputCommand == 'w') {
                    originalTile = finalWorldFrame[player.xPosition][player.yPosition + 1];
                    boolean check = originalTile.equals(Tileset.FLOOR);
                    if (check) {
                        finalWorldFrame[player.xPosition][player.yPosition] = originalTile;
                        player.yPosition += 1;
                        finalWorldFrame[player.xPosition][player.yPosition] = Tileset.PLAYER;
                        ter.renderFrame(finalWorldFrame);
                    }
                } else if (inputCommand == 's') {
                    originalTile = finalWorldFrame[player.xPosition][player.yPosition - 1];
                    boolean check = originalTile.equals(Tileset.FLOOR);
                    if (check) {
                        finalWorldFrame[player.xPosition][player.yPosition] = originalTile;
                        player.yPosition -= 1;
                        finalWorldFrame[player.xPosition][player.yPosition] = Tileset.PLAYER;
                        ter.renderFrame(finalWorldFrame);
                    }
                } else if (inputCommand == 'a') {
                    originalTile = finalWorldFrame[player.xPosition - 1][player.yPosition];
                    boolean check = originalTile.equals(Tileset.FLOOR);
                    if (check) {
                        finalWorldFrame[player.xPosition][player.yPosition] = originalTile;
                        player.xPosition -= 1;
                        finalWorldFrame[player.xPosition][player.yPosition] = Tileset.PLAYER;
                        ter.renderFrame(finalWorldFrame);
                    }
                } else if (inputCommand == 'd') {
                    originalTile = finalWorldFrame[player.xPosition + 1][player.yPosition];
                    boolean check = originalTile.equals(Tileset.FLOOR);
                    if (check) {
                        finalWorldFrame[player.xPosition][player.yPosition] = originalTile;
                        player.xPosition += 1;
                        finalWorldFrame[player.xPosition][player.yPosition] = Tileset.PLAYER;
                        ter.renderFrame(finalWorldFrame);
                    }
                } else if (inputCommand == 'q') {
                    saveWorld(finalWorldFrame);
                    //System.out.println(player.xPosition + " " + player.yPosition);
                    savePosition(player);
                    System.exit(0);
                    break;
                }
            }
        }
    }

    private void saveWorld(TETile[][] world) {
        File f = new File("./world.ser");
        try {
            if (!f.exists()) {
                f.createNewFile();
            }
            FileOutputStream fs = new FileOutputStream(f);
            ObjectOutputStream os = new ObjectOutputStream(fs);
            os.writeObject(world);
            os.close();
        } catch (FileNotFoundException e) {
            System.out.println("file not found");
            System.exit(0);
        } catch (IOException e) {
            System.out.println(e);
            System.exit(0);
        }
    }

    private void savePosition(Position p) {
        File f = new File("./p.ser");
        try {
            if (!f.exists()) {
                f.createNewFile();
            }
            FileOutputStream fs = new FileOutputStream(f);
            ObjectOutputStream os = new ObjectOutputStream(fs);
            os.writeObject(p);
            os.close();
        } catch (FileNotFoundException e) {
            System.out.println("file not found");
            System.exit(0);
        } catch (IOException e) {
            System.out.println(e);
            System.exit(0);
        }
    }

    private void startNewGame(TETile[][] finalWorldFrame) {
        StdDraw.clear(Color.BLACK);
        StdDraw.text(30, 30, "Please enter a seed: ");
        StdDraw.show();
        int seed = enterSeed();
        //create a new world
        ter.initialize(60, 50);
        World.initialize(finalWorldFrame);
        World newWorld = new World(seed);
        finalWorldFrame = newWorld.createRandomWorld(finalWorldFrame);
        Position playerPosition = new Position(15, 15);
        startOldGame(finalWorldFrame, playerPosition);
    }

    private void startOldGame(TETile[][] finalWorldFrame, Position playerPosition) {
        ter.initialize(60, 50);
        while (inTheGame) {
            ter.renderFrame(finalWorldFrame);
            control(finalWorldFrame, playerPosition);
        }
    }

    private int enterSeed() {
        boolean stopTyping = false;
        int x = 27;
        String seed = "";
        while (!stopTyping) {
            if (StdDraw.hasNextKeyTyped()) {
                char readInput = StdDraw.nextKeyTyped();
                if (readInput == 's') {
                    stopTyping = true;
                } else {
                    String s = Character.toString(readInput);
                    StdDraw.text(x, 25, s);
                    StdDraw.show();
                    seed = seed + s;
                    x = x + 1;
                }
            }
        }
        return Integer.parseInt(seed);
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
     *
     * @param input the input string to feed to your program
     * @return the 2D TETile[][] representing the state of the world
     */
    public TETile[][] playWithInputString(String input) {
        // and return a 2D tile representation of the world that would have been
        // drawn if the same inputs had been given to playWithKeyboard().
        TETile[][] finalWorldFrame = new TETile[60][50];
        ter.initialize(60, 50);
        World.initialize(finalWorldFrame);
        String seedString = input.substring(1, input.length() - 2);
        int seed = Integer.parseInt(seedString);
        World world = new World(seed);
        world.createRandomWorld(finalWorldFrame);
        ter.renderFrame(finalWorldFrame);
        return finalWorldFrame;
    }
}
