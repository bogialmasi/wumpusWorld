package wumpus.validator;
import wumpus.exceptions.HeroException;
import wumpus.exceptions.InvalidInputException;
import wumpus.exceptions.InvalidObjectAmountException;
import wumpus.exceptions.InvalidPositionException;
import wumpus.objects.GameObject;
import wumpus.world.World;

import java.awt.*;
import java.util.List;

public interface MapContentValidator {
    int validateAmountOfWumpuses(World world) throws InvalidObjectAmountException;
    void validateWallsOnEdgesOfMap(World world) throws InvalidPositionException;
    void validateHero(World world) throws InvalidObjectAmountException, HeroException;
    void validateGold(World world) throws InvalidObjectAmountException;
    void isThereAnythingOnThisPosition(Point pos, World world) throws InvalidPositionException;
    void isThisPositionEmpty(Point pos, World world) throws InvalidInputException;
}
