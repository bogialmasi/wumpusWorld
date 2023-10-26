package wumpus.validator;

import wumpus.exceptions.HeroException;
import wumpus.exceptions.InvalidObjectAmountException;
import wumpus.exceptions.InvalidSizeException;
import wumpus.world.World;

public class MapValidator implements IMapValidator {
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
    public boolean validateColumnSize(int mapSize, int columnLength) throws InvalidSizeException {
        if (mapSize != columnLength) {
            throw new InvalidSizeException("Column size is invalid!");
        } else {
            System.out.println("Size of column is OK.");
        }
        return false;
    }

    @Override
    public void validateContentOfMap(World world) throws InvalidObjectAmountException, HeroException {
        MapContentValidator mapContentValidator = new MapContentValidator();
        mapContentValidator.validateAmountOfWumpuses(world);
        mapContentValidator.validateGold(world);
        mapContentValidator.validateHero(world);
        mapContentValidator.validateWallsOnEdgesOfMap(world);
        mapContentValidator.validatePositionOfObjects(world);
    }
}

