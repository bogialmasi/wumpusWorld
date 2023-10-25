package wumpus.objects;

import java.awt.*;

public class Hero {
    private Point pos;
    private int arrows;
    private boolean hasGold;

    public Hero(Point pos) {
        this.pos = pos;
    }

    public Hero(Point pos, int arrows, boolean hasGold) {
        this.pos = pos;
        this.arrows = arrows;
        this.hasGold = hasGold;
    }

    public Point getPos() {
        return pos;
    }

    public void setPos(Point pos) {
        this.pos = pos;
    }

    public boolean hasGold() {
        return hasGold;
    }

    public void setHasGold(boolean hasGold) {
        this.hasGold = hasGold;
    }

    public int getArrows() {
        return arrows;
    }

    public void setArrows(int arrows) {
        this.arrows = arrows;
    }

    public String heroOnMap() {
        return "H";
    }

    public boolean equals(String c) {
        if (heroOnMap().equals(c)) return true;
        return false;
    }
}
