package wumpus.world;

import static ch.qos.logback.core.joran.JoranConstants.NULL;

public class World {
    private int size;
    private String world[][];
    private int row = 1;
    private int col = 1;
    private boolean gameOver = false;

    // hero's directions
    //public enum dir {N, E, S, W}

    //public enum go {UP, DOWN, LEFT, RIGHT}

    public World(int size) {
        this.size = size;
        world = new String[size + 1][size + 1];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                world[i][j] = NULL;
            }
        }
    }

    public World(int size, String[][] world, int row, int col) {
        this.size = size;
        this.world = world;
        this.row = row;
        this.col = col;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public String[][] getWorld() {
        return world;
    }

    public void setWorld(String[][] world) {
        this.world = world;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
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
