package wumpus.menu;

import wumpus.exceptions.HeroAttributeException;
import wumpus.exceptions.HeroException;
import wumpus.exceptions.InvalidInputException;
import wumpus.exceptions.InvalidObjectAmountException;
import wumpus.exceptions.InvalidSizeException;

import java.io.IOException;

public interface MenuOptions {
    void uploadFromFile() throws InvalidInputException, InvalidSizeException, IOException, InvalidObjectAmountException, HeroAttributeException, HeroException;
    void playGame() throws InvalidInputException;
    void saveGameToDB() throws InvalidInputException;
    void loadGameFromDB() throws InvalidInputException;
    void backToMenu() throws InvalidInputException;
    void exitGame();
}
