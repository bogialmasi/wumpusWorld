package wumpus.service.validator;

import wumpus.exceptions.InvalidInputException;
import wumpus.exceptions.InvalidObjectAmountException;
import wumpus.exceptions.InvalidPositionException;
import wumpus.exceptions.InvalidSizeException;
import wumpus.model.objects.World;

import java.awt.*;

public interface MapValidator {
    void validateSizeOfMap(int mapSize, World world) throws InvalidSizeException;

    void validateRowSize(int mapSize, int rowLength) throws InvalidSizeException;

    void validateColumnSize(int mapSize, int columnLength) throws InvalidSizeException;

    int validateAmountOfWumpuses(World world) throws InvalidObjectAmountException;

    void validateWallsOnEdgesOfMap(World world) throws InvalidPositionException;

    void validateOnlyOneGoldExists(World world) throws InvalidObjectAmountException;

    boolean validateHeroIsNotOnThisPosition(Point heroPos, Point actualPosition) throws InvalidPositionException;

    boolean isThisPositionEmpty(Point pos, World world) throws InvalidInputException;
}

