package wumpus.menu;

import wumpus.exceptions.InvalidInputException;
import wumpus.exceptions.InvalidSizeException;

import java.io.IOException;

public interface MenuOptions {
    void uploadFromFile() throws InvalidInputException, InvalidSizeException, IOException;
    void playGame() throws InvalidInputException;
    void saveGameToDB() throws InvalidInputException;
    void loadGameFromDB() throws InvalidInputException;
    void backToMenu() throws InvalidInputException;
    void exitGame();
}
