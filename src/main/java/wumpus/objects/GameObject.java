package wumpus.objects;

import java.awt.*;

public class GameObject {
    Point pos;
    String type;

    public GameObject(Point pos, String type) {
        this.pos = pos;
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        type = type;
    }

    public Point getPos() {
        return pos;
    }

    public void setPos(Point pos) {
        this.pos = pos;
    }
}
