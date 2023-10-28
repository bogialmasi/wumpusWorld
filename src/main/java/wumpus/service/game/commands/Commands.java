package wumpus.service.game.commands;

import wumpus.model.objects.Direction;

public interface Commands {
    //goUp();
    //goDown();
    //goLeft();
    //goRight();
    //shoot();
    //pickUpGold();
    public Direction turnLeft(Direction direction);
    public Direction turnRight(Direction direction);
}
