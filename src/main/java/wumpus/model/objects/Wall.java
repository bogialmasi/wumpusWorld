package wumpus.model.objects;

import wumpus.constants.Constants;

import java.awt.*;

public class Wall extends GameObject {
    public Wall(Point pos) {
        super(pos, Constants.WALL);
    }
}
