package wumpus.service.game;

import wumpus.exceptions.InvalidInputException;
import wumpus.model.objects.Hero;

public interface GamePlayer {
    void startGame() throws InvalidInputException;
}
