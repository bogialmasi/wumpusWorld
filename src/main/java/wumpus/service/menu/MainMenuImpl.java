package wumpus.service.menu;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import wumpus.exceptions.*;
import wumpus.service.MapReaderImpl;
import wumpus.service.validator.HeroValidator;
import wumpus.service.validator.MapValidator;
import wumpus.model.objects.World;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Scanner;

public class MainMenuImpl implements MainMenu {
    Scanner sc;
    World world;
    private final MapValidator mapValidator;
    BufferedReader bufferedReader;
    private HeroValidator heroValidator;

    private static final Logger LOGGER = LoggerFactory.getLogger(MainMenuImpl.class);

    public MainMenuImpl(BufferedReader bufferedReader, MapValidator mapValidator) {

        this.bufferedReader = bufferedReader;
        this.mapValidator = mapValidator;
    }

    public void startMenu() throws InvalidInputException {
        LOGGER.info("*** WELCOME TO WUMPUS WORLD ! ***");
        askForUsername();
    }

    private void askForUsername() throws InvalidInputException {
        sc = new Scanner(System.in);
        try {
            String inputUsername = null;
            while (inputUsername == null) {
                LOGGER.info("Username: ");
                inputUsername = sc.next();
            }
            if (inputUsername.length() > 0) {
                LOGGER.info("Hello, " + inputUsername + "!\n");
                chooseMenu();
            }
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            sc.close();
        }

    }

    private void chooseMenu() throws InvalidInputException {
        LOGGER.info(
                "1 - PLAY\n" +
                        "2 - LOAD NEW FILE\n" +
                        "3 - LOAD SAVED FILE\n" +
                        "4 - SAVE\n" +
                        "5 - EXIT"
        );
        LOGGER.info("Choose an option from above: ");

        try {
            sc = new Scanner(System.in);
            int chosenMenuOption = sc.nextInt();
            switch (chosenMenuOption) {
                case 1 -> playGame(); // 1 - play
                case 2 -> uploadFromFile(); // 2 - load new file with MapReader
                case 3 -> loadGameFromDB(); // 3 - load saved file -- to be implemented later
                case 4 -> saveGameToDB(); // 4 - save -- to be implemented later
                case 5 -> exitGame(); // 5 - exit
                default -> {
                    LOGGER.info("Please choose a number between 1 and 5!");
                    chooseMenu();
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            sc.close();
        }
    }

    @Override
    public void uploadFromFile() throws InvalidInputException, InvalidSizeException, IOException, InvalidObjectAmountException, HeroException, InvalidPositionException {
        MapReaderImpl mapReaderImpl = new MapReaderImpl(mapValidator, bufferedReader, heroValidator);
        world = mapReaderImpl.readMap();
        if (world == null) {
            LOGGER.warn("An error occured. Please try again!");
        } else {
            LOGGER.info("Map loaded succesfully. Ready to play.");
        }
        chooseMenu();
    }

    @Override
    public void playGame() throws InvalidInputException {
        if (world != null) {
            //TODO IMPLEMENT GAME STARTER
        } else {
            LOGGER.info("Load a map first to play!");
            chooseMenu();
        }
    }

    @Override
    public void saveGameToDB() throws InvalidInputException {
        LOGGER.warn("This Function does not work yet.");
        chooseMenu();
    }

    @Override
    public void loadGameFromDB() throws InvalidInputException {
        LOGGER.warn("This Function does not work yet.");
        chooseMenu();
    }


    @Override
    public void exitGame() {
        LOGGER.info("*** GOOD BYE ! ***");
        System.exit(0);
    }
}

