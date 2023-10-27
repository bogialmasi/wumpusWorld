package wumpus.validator;

import wumpus.exceptions.HeroException;
import wumpus.exceptions.InvalidPositionException;
import wumpus.objects.Hero;
import wumpus.world.World;

import java.awt.*;

public class HeroValidatorImpl implements HeroValidator {
    @Override
    public void validateHeroPosition(Hero hero, World world, int row, int col) throws HeroException {
        if (row >= world.getN()-1 || row <= 0) {
            throw new HeroException("The Hero is standing in an invalid row");
        }
        if (col >= world.getN()-1 || col <= 0) {
            throw new HeroException("The Hero is standing in an invalid column");
        }
    }

    @Override
    public void validateHeroStartingPosition(Point startingPoint, Point heroPosition) throws InvalidPositionException {
        if (!startingPoint.equals(heroPosition)) {
            throw new InvalidPositionException("Hero is not on the given starting position on the map.");
        }
    }

    @Override
    public boolean canHeroGoThere(String[][] goToSquare) {
        return false;
    }

    @Override
    public int numOfArrows() {
        return 0;
    }

    @Override
    public boolean isHeroAlive() {
        return false;
    }

    @Override
    public boolean isHeroShootingWall() {
        return false;
    }

    @Override
    public boolean doesHeroHaveTheGold() {
        return false;
    }
}
