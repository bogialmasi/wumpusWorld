package wumpus.model.objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import wumpus.constants.Constants;
import wumpus.service.game.commands.impl.CommandsImpl;

import java.awt.*;
import java.util.ArrayList;

public class World {
    private int N;
    public String[][] map;
    private boolean gameOver = false;
    private boolean gameWon = false;
    public boolean isGameWon(Point heroProvisionalPoint) {
        Hero hero = (Hero) this.getHero();
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
        return GenericGetObject(Constants.WALL);
    }

    public ArrayList<GameObject> getWumpuses() {
        return GenericGetObject(Constants.WUMPUS);
    }

    public ArrayList<GameObject> getPits() {
        return GenericGetObject(Constants.PIT);
    }

    public ArrayList<GameObject> getEmptyFields() {
        return GenericGetObject(Constants.EMPTY);
    }

    public GameObject getGold() {
        return GenericGetObject(Constants.GOLD).get(0);
    }

    public void removeGold() {
        Gold g = (Gold)this.getGold();
        this.gameObjects.remove(g);
    }

    public GameObject getHero() {
        return GenericGetObject(Constants.HERO).get(0);
    }

    public World(int mapSize) {
        map = new String[mapSize][mapSize];
        N = mapSize;
    }

    public int getN() {
        return N;
    }

    public ArrayList<GameObject> GenericGetObject(String type) {
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

}
