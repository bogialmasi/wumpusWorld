package wumpus.service.game.commands;

import wumpus.exceptions.InvalidInputException;
import wumpus.model.objects.Direction;
import wumpus.model.objects.GameObject;
import wumpus.model.objects.Hero;
import wumpus.model.objects.World;

import java.awt.*;

public interface Commands {
    void goUp(Hero hero, World world) throws InvalidInputException;
    void goDown(Hero hero, World world);
    void goLeft(Hero hero, World world);
    void goRight(Hero hero, World world);
    void shoot(Hero hero, World world);
    void pickUpGold(Hero hero, World world);
    Direction turnLeft(Direction direction);
    Direction turnRight(Direction direction);
    void handleHeroMove(String command, World world);
    void gameOver(World world);

}
