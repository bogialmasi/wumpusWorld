package wumpus.service.game.commands.impl;

import wumpus.model.objects.Direction;
import wumpus.service.game.commands.Commands;

public class CommandsImpl implements Commands {
    //goUp();
    //goDown();
    //goLeft();
    //goRight();
    //shoot();
    //pickUpGold();


    @Override
    public Direction turnLeft(Direction direction) {
        switch (direction) {
            case N:
                return Direction.W;
            case E:
                return Direction.N; // TODO duplicate problem
            case S:
                return Direction.E;
            case W:
                return Direction.S;
            default:
                return Direction.N;
        }
    }

    @Override
    public Direction turnRight(Direction direction) {
        switch (direction) {
            case N:
                return Direction.E;
            case E:
                return Direction.S;
            case S:
                return Direction.W;
            case W:
                return Direction.N; // TODO duplicate problem
            default:
                return Direction.N;
        }
    }
}
