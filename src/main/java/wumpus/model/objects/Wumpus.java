package wumpus.model.objects;

import wumpus.constants.Constants;

import java.awt.*;

public class Wumpus extends GameObject {
    public Wumpus(Point pos) {
        super(pos, Constants.WUMPUS);
    }
}
