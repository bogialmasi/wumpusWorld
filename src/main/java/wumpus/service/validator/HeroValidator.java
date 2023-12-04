package wumpus.service.validator;

import wumpus.exceptions.HeroException;
import wumpus.exceptions.InvalidPositionException;
import wumpus.model.objects.World;

import java.awt.*;

public interface HeroValidator {
    void validateHeroPositionIsInsideMap(World world, int row, int col) throws HeroException;

    void validateHeroStartingPosition(Point startingPoint, Point heroPosition) throws InvalidPositionException;

}
