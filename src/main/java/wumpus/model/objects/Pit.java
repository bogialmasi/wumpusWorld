package wumpus.model.objects;

import wumpus.constants.Constants;

import java.awt.*;

public class Pit extends GameObject {
    public Pit(Point pos) {
        super(pos, Constants.PIT);
    }
}
