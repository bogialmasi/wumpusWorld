package wumpus;

import wumpus.exceptions.InvalidInputException;
import wumpus.menu.MainMenuImpl;
import wumpus.validator.MapValidator;
import wumpus.validator.MapValidatorImpl;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class Main {


    //TODO find a way to close this and be testable as well
    public static final String MAP_FILE_PATH = "src/main/resources/wumpuszinput.txt";
    public static void main(String[] args) throws InvalidInputException, FileNotFoundException{
        BufferedReader reader = new BufferedReader(new FileReader(MAP_FILE_PATH));
        MapValidator mapValidator = new MapValidatorImpl();
        MainMenuImpl mainMenuImpl = new MainMenuImpl(reader, mapValidator);
        mainMenuImpl.startMenu();

    }
}
