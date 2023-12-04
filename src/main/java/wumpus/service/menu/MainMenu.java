package wumpus.service.menu;

import wumpus.exceptions.*;

import java.io.IOException;
import java.sql.SQLException;

public interface MainMenu {
    void uploadFromFile() throws InvalidInputException, InvalidSizeException, IOException, InvalidObjectAmountException, HeroException, InvalidPositionException;

    void playGame() throws InvalidInputException;

    void saveGameToDB() throws InvalidInputException, SQLException;

    void loadGameFromDB() throws InvalidInputException, SQLException, InvalidSizeException, InvalidObjectAmountException, HeroException, InvalidPositionException;

    void exitGame();

    void chooseMenu();

    String getInputUsername();
}
