package wumpus.model.objects;

import wumpus.constants.Constants;

import java.awt.*;
import java.util.ArrayList;

public class World {
    private int N;

    public String[][] map;
    private boolean gameOver = false;
    private boolean gameWon = false;

    public boolean isGameWon(Point heroProvisionalPoint) {
        Hero hero = this.getHero();
        return gameWon = heroProvisionalPoint.equals(hero.getStartingPosition()) && hero.hasGold();
    }

    public boolean isGameWon() {
        return gameWon;
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public void setGameOver(boolean gameOver) {
        this.gameOver = gameOver;
    }

    public ArrayList<GameObject> gameObjects = new ArrayList<>();

    public ArrayList<GameObject> getGameObjects() {
        return gameObjects;
    }

    public ArrayList<GameObject> getWalls() {
        return genericGetObject(Constants.WALL);
    }

    public ArrayList<GameObject> getWumpuses() {
        return genericGetObject(Constants.WUMPUS);
    }

    public ArrayList<GameObject> getPits() {
        return genericGetObject(Constants.PIT);
    }

    public ArrayList<GameObject> getEmptyFields() {
        return genericGetObject(Constants.EMPTY);
    }

    public GameObject getGold() {
        return genericGetObject(Constants.GOLD).get(0);
    }

    public void removeGold() {
        Gold g = (Gold) this.getGold();
        this.gameObjects.remove(g);
    }


    public void killWumpus(int arrowPosX, int arrowPosY) {
        for (GameObject wumpus : this.getWumpuses()) {
            if (wumpus.getPos().equals(new Point(arrowPosX, arrowPosY))) {
                this.gameObjects.remove(wumpus);
                this.map[wumpus.getPos().x][wumpus.getPos().y] = Constants.EMPTY;
                this.gameObjects.add(new GameObject(new Point(wumpus.getPos().x, wumpus.getPos().y), Constants.EMPTY));
            }
        }
    }

    public Hero getHero() {
        return (Hero) genericGetObject(Constants.HERO).get(0);
    }

    public World(int mapSize) {
        map = new String[mapSize][mapSize];
        N = mapSize;
    }

    public int getN() {
        return N;
    }

    public ArrayList<GameObject> genericGetObject(String type) {
        ArrayList<GameObject> objects = new ArrayList<>();
        for (int i = 0; i < this.gameObjects.size(); i++) {
            GameObject object = this.gameObjects.get(i);
            if (object.getType().equals(type)) {
                objects.add(object);
            }
        }
        return objects;
    }

    public void showMap() {
        for (int i = 0; i < this.getN(); i++) {
            for (int j = 0; j < this.getN(); j++) {
                System.out.print(this.map[i][j]);
            }
            System.out.print("\n");
        }
    }

    public String parseMapToString() {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < this.getN(); i++) {
            for (int j = 0; j < this.getN(); j++) {
                builder.append(this.map[i][j]);
            }
            builder.append("\r\n");
        }
        return builder.toString();
    }
}
