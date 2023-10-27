package wumpus.validator;
import wumpus.exceptions.*;
import wumpus.objects.GameObject;
import wumpus.world.World;

import java.awt.*;
import java.util.List;

public interface MapValidator {
    int validateAmountOfWumpuses(World world) throws InvalidObjectAmountException;
    void validateWallsOnEdgesOfMap(World world) throws InvalidPositionException;
    void validateSizeOfMap(int mapSize, World world) throws InvalidSizeException;
    void validateRowSize(int mapSize, int rowLength) throws InvalidSizeException;
    void validateColumnSize(int mapSize, int columnLength) throws InvalidSizeException;
    void validateHero(World world) throws InvalidObjectAmountException, HeroException;
    void validateGold(World world) throws InvalidObjectAmountException;
    void isThereAnythingOnThisPosition(Point pos, World world) throws InvalidPositionException;
    void isThisPositionEmpty(Point pos, World world) throws InvalidInputException;
}
