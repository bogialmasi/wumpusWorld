package wumpus.objects;

import java.awt.*;

public class Gold {
    private Point pos;

    public Gold() {

    }

    public Gold(Point pos) {
        this.pos = pos;
    }

    public Point getPos() {
        return pos;
    }

    public void setPos(Point pos) {
        this.pos = pos;
    }
}
