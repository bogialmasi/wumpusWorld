package wumpus.service.validator;
import wumpus.exceptions.*;
import wumpus.model.objects.World;

import java.awt.*;

public interface MapValidator {
    void validateSizeOfMap(int mapSize, World world) throws InvalidSizeException;
    void validateRowSize(int mapSize, int rowLength) throws InvalidSizeException;
    void validateColumnSize(int mapSize, int columnLength) throws InvalidSizeException;

    int validateAmountOfWumpuses(World world) throws InvalidObjectAmountException;
    void validateWallsOnEdgesOfMap(World world) throws InvalidPositionException;
    void validateHero(World world) throws InvalidObjectAmountException, HeroException;
    void validateGold(World world) throws InvalidObjectAmountException;
    void isThereAnythingOnThisPosition(Point pos, World world) throws InvalidPositionException;
    void isThisPositionEmpty(Point pos, World world) throws InvalidInputException;
}

