package wumpus.service.validator;

import wumpus.exceptions.HeroException;
import wumpus.exceptions.InvalidPositionException;
import wumpus.model.objects.Hero;
import wumpus.model.objects.World;

import java.awt.*;

public interface HeroValidator {
    void validateHeroPosition(Hero hero, World world, int row, int col) throws HeroException;
    void validateHeroStartingPosition(Point startingPoint, Point heroPosition) throws InvalidPositionException;
    boolean canHeroGoThere(String[][] goToSquare);
    int numOfArrows();
    boolean isHeroAlive();
    boolean isHeroShootingWall();
    boolean doesHeroHaveTheGold();

}
