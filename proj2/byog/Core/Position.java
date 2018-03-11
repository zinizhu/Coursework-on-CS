package byog.Core;

import java.io.Serializable;

public class Position implements Serializable {
    int xPosition;
    int yPosition;

    public Position(int x, int y) {
        xPosition = x;
        yPosition = y;
    }
}
