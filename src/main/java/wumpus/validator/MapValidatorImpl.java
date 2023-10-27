package wumpus.validator;

import wumpus.exceptions.*;
import wumpus.world.World;

import java.awt.*;

public class MapValidatorImpl implements MapValidator {

    MapContentValidator mapContentValidator;

    public MapValidatorImpl(MapContentValidator mapContentValidator) {
        this.mapContentValidator = mapContentValidator;
    }

    @Override
    public int validateAmountOfWumpuses(World world) throws InvalidObjectAmountException {
        return mapContentValidator.validateAmountOfWumpuses(world);
    }

    @Override
    public void validateWallsOnEdgesOfMap(World world) throws InvalidPositionException {
        mapContentValidator.validateWallsOnEdgesOfMap(world);
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
    public void validateHero(World world) throws InvalidObjectAmountException, HeroException {
        mapContentValidator.validateHero(world);
    }

    @Override
    public void validateGold(World world) throws InvalidObjectAmountException {
        mapContentValidator.validateGold(world);
    }

    @Override
    public void isThereAnythingOnThisPosition(Point pos, World world) throws InvalidPositionException {
        mapContentValidator.isThereAnythingOnThisPosition(pos, world);
    }

    @Override
    public void isThisPositionEmpty(Point pos, World world) throws InvalidInputException {
        mapContentValidator.isThisPositionEmpty(pos, world);
    }

}

