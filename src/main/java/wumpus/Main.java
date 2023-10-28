package wumpus;

import wumpus.exceptions.InvalidInputException;
import wumpus.service.menu.impl.MainMenuImpl;
import wumpus.service.validator.HeroValidator;
import wumpus.service.validator.MapValidator;
import wumpus.service.validator.impl.HeroValidatorImpl;
import wumpus.service.validator.impl.MapValidatorImpl;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class Main {
    private static final String MAP_FILE_PATH = "src/main/resources/wumpuszinput.txt";
    public static void main(String[] args) throws InvalidInputException, FileNotFoundException{
        BufferedReader reader = new BufferedReader(new FileReader(MAP_FILE_PATH));
        MapValidator mapValidator = new MapValidatorImpl();
        HeroValidator heroValidator = new HeroValidatorImpl();
        MainMenuImpl mainMenuImpl = new MainMenuImpl(reader, mapValidator, heroValidator);
        mainMenuImpl.startMenu();
    }
}
