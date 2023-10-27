package wumpus.objects;

import wumpus.Constants;

import java.awt.*;

public class Wall extends GameObject {
    public Wall(Point pos) {
        super(pos, Constants.WALL);
    }
}
