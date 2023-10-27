package wumpus.validator;

import wumpus.Constants;
import wumpus.exceptions.*;
import wumpus.objects.GameObject;
import wumpus.objects.Wall;
import wumpus.world.World;

import java.awt.*;
import java.util.ArrayList;
import java.util.Optional;

public class MapValidatorImpl implements MapValidator {

    public MapValidatorImpl() {
    }

    @Override
    public void validateSizeOfMap(int mapSize, World world) throws InvalidSizeException {
        if (world.getN() != mapSize || !(world.getN() >= 6 && world.getN() <= 20)) {
            throw new InvalidSizeException("The size of the map is invalid!");
        } else {
            System.out.println("Size of map is OK.");
        }
    }

    @Override
    public void validateRowSize(int mapSize, int rowLength) throws InvalidSizeException {
        if (mapSize != rowLength) {
            throw new InvalidSizeException("Row size is invalid!");
        } else {
            System.out.println("Size of rows are OK.");
        }
    }

    @Override
    public void validateColumnSize(int mapSize, int columnLength) throws InvalidSizeException {
        if (mapSize != columnLength) {
            throw new InvalidSizeException("Column size is invalid!");
        }
    }

    @Override
    public int validateAmountOfWumpuses(World world) throws InvalidObjectAmountException {
        ArrayList<GameObject> wumpuses = world.getWumpuses();

        if (world.getN() <= 8) {
            if (wumpuses.size() != 1) {
                throw new InvalidObjectAmountException("Only one wumpus can exist on a " + world.getN() + " size map.");
            }
        } else if (world.getN() >= 9 && world.getN() <= 14) {
            if (wumpuses.size() != 2) {
                throw new InvalidObjectAmountException("Only two wumpuses can exist on a " + world.getN() + " size map.");
            }
        } else {
            if (wumpuses.size() != 3) {
                throw new InvalidObjectAmountException("Only three wumpuses can exist on a " + world.getN() + " size map.");
            }
        }
        return wumpuses.size();
    }

    @Override
    public void validateWallsOnEdgesOfMap(World world) throws InvalidPositionException {
        InvalidPositionException ipe = new InvalidPositionException("The edges of the world must be walls");
        for (GameObject gameObject : world.getGameObjects()) {
            int objectY = (int)gameObject.getPos().getY();
            int objectX = (int)gameObject.getPos().getX();
            if (objectY == 0 && !(gameObject instanceof Wall)) { // first column
                throw ipe;
            } else
            if (objectY == world.getN() && !(gameObject instanceof Wall)) { // last column
                throw ipe;
            } else
            if (objectX == 0 && !(gameObject instanceof Wall)) { // first row
                throw ipe;
            } else
            if (objectX == world.getN() && !(gameObject instanceof Wall)) { // last row
                throw ipe;
            }
        }
    }

    @Override
    public void validateHero(World world) throws InvalidObjectAmountException {
        for (int i = 0; i < world.gameObjects.size(); i++) {
            if (world.gameObjects.get(i).getType().equals(Constants.HERO)) {
                throw new InvalidObjectAmountException("There is already a hero created.");
            }
        }


        /*
        if (world.getHero().getArrows() != world.getWumpuses().size()) {
            validateAmountOfWumpuses(world);
            throw new HeroException("The Hero has incorrect amount of arrows.");
        }
        // check that hero stands on correct position, not on walls or on wumpus, starting pos is not on gold or pit either*/
    }

    @Override
    public void validateGold(World world) throws InvalidObjectAmountException {
        for (int i = 0; i < world.gameObjects.size(); i++) {
            if (world.gameObjects.get(i).getType().equals(Constants.GOLD)) {
                throw new InvalidObjectAmountException("There is already a gold on map.");
            }
        }
    }


    @Override
    public void isThereAnythingOnThisPosition(Point pos, World world) throws InvalidPositionException {
        for (int i = 0; i < world.gameObjects.size(); i++) {
            int objectX = (int) world.gameObjects.get(i).getPos().getX();
            int objectY = (int) world.gameObjects.get(i).getPos().getY();
            if (objectX == pos.getX() && objectY == pos.getY()) {
                throw new InvalidPositionException("Something already on this position: (" + objectX + "," + objectY + ")");
            }
        }
    }

    @Override
    public void isThisPositionEmpty(Point pos, World world) throws InvalidInputException {
        ArrayList<GameObject> emptyFields = world.getEmptyFields();
        Optional<GameObject> value = emptyFields
                .stream()
                .filter(a -> a.getType() == Constants.EMPTY)
                .findFirst();
        if (value.isEmpty()) {
            throw new InvalidInputException("Hero cannot be placed on filled spot.");
        }
    }


}

