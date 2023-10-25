package wumpus.menu;

import wumpus.exceptions.InvalidInputException;

public interface MenuOptions {
    void uploadFromFile() throws InvalidInputException;
    void playGame() throws InvalidInputException;
    void saveGameToDB() throws InvalidInputException;
    void loadGameFromDB() throws InvalidInputException;
    void backToMenu() throws InvalidInputException;

    void exitGame();
}
