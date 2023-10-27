package wumpus.objects;

import wumpus.Constants;

import java.awt.*;

public class Pit extends GameObject {
    public Pit(Point pos) {
        super(pos, Constants.PIT);
    }
}
