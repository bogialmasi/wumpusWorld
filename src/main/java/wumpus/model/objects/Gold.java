package wumpus.model.objects;

import wumpus.constants.Constants;

import java.awt.*;

public class Gold extends GameObject {
    public Gold(Point pos) {
        super(pos, Constants.GOLD);
    }
}
