package wumpus.service.validator.impl;

import wumpus.exceptions.HeroException;
import wumpus.exceptions.InvalidPositionException;
import wumpus.model.objects.World;
import wumpus.service.validator.HeroValidator;

import java.awt.*;

public class HeroValidatorImpl implements HeroValidator {


    @Override
    public void validateHeroPositionIsInsideMap(World world, int row, int col) throws HeroException {
        if (row >= world.getN() - 1 || row <= 0) {
            throw new HeroException("The Hero is standing in an invalid row");

        }
        if (col >= world.getN() - 1 || col <= 0) {
            throw new HeroException("The Hero is standing in an invalid column");
        }
    }

    @Override
    public void validateHeroStartingPosition(Point startingPoint, Point objectPosition) throws InvalidPositionException {
        if (!objectPosition.equals(startingPoint)) {
            throw new InvalidPositionException("Hero is not on the given starting position on the map.");
        }
    }
}
