package wumpus.service.menu;

import wumpus.exceptions.*;
import wumpus.model.objects.World;

import java.io.IOException;

public interface MainMenu {
    void uploadFromFile() throws InvalidInputException, InvalidSizeException, IOException, InvalidObjectAmountException, HeroException, InvalidPositionException;
    void playGame() throws InvalidInputException;
    void saveGameToDB() throws InvalidInputException;
    void loadGameFromDB() throws InvalidInputException;
    void exitGame();
    void chooseMenu();
}
