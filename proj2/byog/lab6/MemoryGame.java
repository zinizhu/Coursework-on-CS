package byog.lab6;

import edu.princeton.cs.introcs.StdDraw;

import java.awt.*;
import java.util.Random;

public class MemoryGame {
    private static final char[] CHARACTERS = "abcdefghijklmnopqrstuvwxyz".toCharArray();
    private static final String[] ENCOURAGEMENT = {"Good job!", "Impressive!", "Are you serious?"};
    private int width;
    private int height;
    private int round;
    private Random rand;
    private boolean gameOver;
    private boolean playerTurn;

    public MemoryGame(int width, int height, Random r) {
        /* Sets up StdDraw so that it has a width by height grid of 16 by 16 squares as its canvas
         * Also sets up the scale so the top left is (0,0) and the bottom right is (width, height)
         */
        this.width = width;
        this.height = height;
        this.rand = r;
        StdDraw.setCanvasSize(this.width * 16, this.height * 16);
        Font font = new Font("Monaco", Font.BOLD, 30);
        StdDraw.setFont(font);
        StdDraw.setXscale(0, this.width);
        StdDraw.setYscale(0, this.height);
        StdDraw.clear(Color.BLACK);
        StdDraw.enableDoubleBuffering();
    }

    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("Please enter a seed");
            return;
        }

        int seed = Integer.parseInt(args[0]);
        Random r = new Random(seed);
        MemoryGame game = new MemoryGame(40, 40, r);
        game.startGame();
    }

    public String generateRandomString(int n) {
        if (n == 0) {
            throw new RuntimeException("The length of the string cannot be zero.");
        }
        if (n < 0) {
            throw new RuntimeException("The length of the string cannot be negative.");
        }
        String randomString = "";
        for (int i = 0; i < n; i += 1) {
            int r = Math.abs(rand.nextInt());
            int index = (r) % 26;
            randomString += CHARACTERS[index];
        }
        return randomString;
    }

    public void drawFrame(String s) {
        StdDraw.clear(Color.BLACK);
        Font myFont = new Font("Arial", Font.BOLD, 50);
        StdDraw.setPenColor(Color.YELLOW);
        StdDraw.setFont(myFont);
        StdDraw.text(width / 2, height / 2, s);
        StdDraw.show();
        StdDraw.pause(1000);
        StdDraw.clear(Color.BLACK);
        StdDraw.show();
        StdDraw.pause(300);
    }

    public void flashSequence(String letters) {
        int length = letters.length();
        for (int i = 0; i < length; i += 1) {
            char c = letters.charAt(i);
            String s = Character.toString(c);
            drawFrame(s);
        }
        StdDraw.setPenColor(Color.BLACK);
        StdDraw.text(width / 2, height / 2, "");
        StdDraw.show();
    }

    public String solicitNCharsInput(int n) {
        String letter = "";
        Font myFont = new Font("Arial", Font.BOLD, 50);
        StdDraw.setFont(myFont);
        while (letter.length() < n) {
            if (StdDraw.hasNextKeyTyped()) {
                char c = StdDraw.nextKeyTyped();
                letter += c;
                int move = 3 * letter.length();
                String s = Character.toString(c);
                StdDraw.setPenColor(Color.YELLOW);
                StdDraw.text(width / 2 + move, height / 2, s);
                StdDraw.show();
            }
        }
        return letter;
    }

    public void startGame() {
        boolean correct = true;
        StdDraw.setPenColor(Color.YELLOW);
        Font myFont = new Font("Arial", Font.BOLD, 50);
        StdDraw.setFont(myFont);
        while (correct) {
            StdDraw.text(width / 2, height / 2, "Round:" + round);
            StdDraw.show();
            StdDraw.pause(2000);
            StdDraw.clear(Color.BLACK);
            String randomString = generateRandomString(round);
            flashSequence(randomString);
            String myAnswer = solicitNCharsInput(round);
            if (myAnswer.equals(randomString)) {
                StdDraw.clear(Color.BLACK);
                StdDraw.pause(1500);
                round += 1;
                if (round % 3 == 0 && round <= 9) {
                    int index = round / 3;
                    String message = ENCOURAGEMENT[index];
                    StdDraw.text(width / 2, height / 2, message);
                    StdDraw.show();
                    StdDraw.pause(2000);
                    StdDraw.clear(Color.BLACK);
                }
            } else {
                correct = false;
                StdDraw.clear(Color.BLACK);
                StdDraw.show();
            }
        }
        String message = "Game Over! You made it to round: " + round;
        StdDraw.text(width / 2, height / 2, message);
        StdDraw.show();
        StdDraw.pause(5000);

    }

}
