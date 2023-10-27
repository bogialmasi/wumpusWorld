package wumpus.objects;

import wumpus.Constants;

import java.awt.*;


public class Hero extends GameObject {
    private int arrows;
    private boolean hasGold;
    Direction dir;
    Point startingPosition;

    public Hero(Point pos) {
        super(pos, Constants.HERO);
    }

    public Hero(Point pos, Direction dir) {
        super(pos, Constants.HERO);
        this.dir = dir;
        this.startingPosition = pos;
    }

    public Hero(Point pos, int arrows, boolean hasGold, Direction dir) {
        super(pos, Constants.HERO);
        this.arrows = arrows;
        this.hasGold = hasGold;
        this.dir = dir;
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

    public Direction getDir() {
        return dir;
    }

    /*
    public String heroOnMap() {
        return "H";
    }

    public boolean equals(String c) {
        if (heroOnMap().equals(c)) return true;
        return false;
    }*/
}
