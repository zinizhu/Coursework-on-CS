package byog.Core;
import byog.TileEngine.TETile;
/**
 * This is the main entry point for the program. This class simply parses
 * the command line inputs, and lets the byog.Core.Game class take over
 * in either keyboard or input string mode.
=======


/** This is the main entry point for the program. This class simply parses
 *  the command line inputs, and lets the byog.Core.Game class take over
 *  in either keyboard or input string mode.
>>>>>>> 4015694a75d14cccbc3dc9a064e8ecb9efa69097
 */
public class Main {
    public static void main(String[] args) {
        if (args.length > 1) {
            System.out.println("Can only have one argument - the input string");
            System.exit(0);
        } else if (args.length == 1) {
            Game game1 = new Game();
            TETile[][] worldState = game1.playWithInputString(args[0]);
            System.out.println(TETile.toString(worldState));
        } else {
            Game game1 = new Game();
            game1.playWithKeyboard();
        }
    }
}
