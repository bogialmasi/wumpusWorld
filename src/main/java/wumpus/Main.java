package wumpus;

import wumpus.exceptions.InvalidInputException;
import wumpus.menu.MainMenu;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class Main {

    public static final String MapFilePath = "src/main/resources/wumpuszinput.txt"; //TODO find a way to close this and be testable as well
    public static void main(String[] args) throws InvalidInputException, FileNotFoundException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(MapFilePath));

        MainMenu mainMenu = new MainMenu(bufferedReader);
        mainMenu.startMenu();
    }
}
