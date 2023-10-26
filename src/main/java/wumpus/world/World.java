package wumpus.world;

import wumpus.objects.*;

import java.awt.*;
import java.util.ArrayList;

public class World {
    private int N;
    private boolean gameOver = false;

    private int row;
    private int col;

    public ArrayList<Wall> walls;
    public ArrayList<Wumpus> wumpuses;
    public ArrayList<Pit> pits;
    public ArrayList<Point> emptyFields;
    public Gold gold;
    public Hero hero;


    // hero's directions
    //public enum dir {N, E, S, W}

    //public enum go {UP, DOWN, LEFT, RIGHT}


    public ArrayList<Wall> getWalls() {
        return walls;
    }

    public ArrayList<Wumpus> getWumpuses() {
        return wumpuses;
    }

    public ArrayList<Pit> getPits() {
        return pits;
    }

    public ArrayList<Point> getEmptyFields() {
        return emptyFields;
    }

    public Gold getGold() {
        return gold;
    }

    public Hero getHero() {
        return hero;
    }

    public World() {
    }

    public int getN() {
        return N;
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public void setGameOver(boolean gameOver) {
        this.gameOver = gameOver;
    }
    /*
    public boolean isWall(){}
    public boolean isHero(){}
    public boolean isWumpus(){}
    public boolean isGold(){}
    public boolean isPit(){}
    */
}
