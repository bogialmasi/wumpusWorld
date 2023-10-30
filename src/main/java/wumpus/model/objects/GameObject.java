package wumpus.model.objects;

import java.awt.*;

public class GameObject {
    private Point pos;
    private String type;

    public GameObject(Point pos, String type) {
        this.pos = pos;
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public Point getPos() {
        return pos;
    }

    public void setPos(Point pos) {
        this.pos = pos;
    }
}
