package wumpus.objects;

import java.awt.*;

public class Wumpus {
   Point pos;

    public Wumpus(Point pos) {
        this.pos = pos;
    }

    public Point getPos() {
        return pos;
    }

    public void setPos(Point pos) {
        this.pos = pos;
    }
}
