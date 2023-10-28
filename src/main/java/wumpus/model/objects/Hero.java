package wumpus.model.objects;

import wumpus.constants.Constants;

import java.awt.*;


public class Hero extends GameObject {
    private int arrows;
    private boolean hasGold;
    private Direction dir;


    public void setDir(Direction dir) {
        this.dir = dir;
    }

    public Point getStartingPosition() {
        return startingPosition;
    }

    public void setStartingPosition(Point startingPosition) {
        this.startingPosition = startingPosition;
    }

    private Point startingPosition;

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

    public int getArrows() {
        return arrows;
    }

    public void setArrows(int arrows) {
        this.arrows = arrows;
    }

    public Direction getDir() {
        return dir;
    }



}
