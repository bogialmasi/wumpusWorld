package wumpus.objects;

import java.awt.*;

public class Pit {
    private Point pos;

    public Pit() {
    }

    public Pit(Point pos) {
        this.pos = pos;
    }

    public Point getPos() {
        return pos;
    }

    public void setPos(Point pos) {
        this.pos = pos;
    }
}
