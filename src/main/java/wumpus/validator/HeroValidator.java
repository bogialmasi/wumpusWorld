package wumpus.validator;

import wumpus.exceptions.HeroException;
import wumpus.exceptions.InvalidPositionException;
import wumpus.objects.Hero;
import wumpus.world.World;

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
