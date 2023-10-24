package wumpus;

import wumpus.exceptions.InvalidInputException;
import wumpus.menu.MainMenu;

public class Main {
    public static void main(String[] args) throws InvalidInputException {
        MainMenu mainMenu = new MainMenu();
        mainMenu.startMenu();
    }
}
