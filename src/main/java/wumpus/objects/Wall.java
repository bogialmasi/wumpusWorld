package wumpus.objects;

import java.awt.*;

public class Wall {
    private Point pos;

    public Wall(Point pos) {
        this.pos = pos;
    }

    public Point getPos() {
        return pos;
    }

    public void setPos(Point pos) {
        this.pos = pos;
    }
}
