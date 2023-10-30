package wumpus.service.game;

import wumpus.exceptions.InvalidInputException;

public interface GamePlayer {
    void startGame() throws InvalidInputException;
}
