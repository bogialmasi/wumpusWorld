package wumpus.service.game.commands;

import wumpus.exceptions.InvalidInputException;
import wumpus.model.objects.Direction;
import wumpus.model.objects.World;

public interface Commands {
    void goUp(World world) throws InvalidInputException;

    void goDown(World world);

    void goLeft(World world);

    void goRight(World world);

    void shoot(World world);

    void pickUpGold(World world);

    Direction turnLeft(Direction direction);

    Direction turnRight(Direction direction);

    void handleHeroMove(String command, World world);
}
