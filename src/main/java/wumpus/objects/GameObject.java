package wumpus.objects;

import java.awt.*;

public class GameObject {
    Point pos;

    public GameObject(Point pos) {
        this.pos = pos;
    }

    public Point getPos() {
        return pos;
    }

    public void setPos(Point pos) {
        this.pos = pos;
    }
}
