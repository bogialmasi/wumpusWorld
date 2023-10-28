package wumpus.service.menu.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import wumpus.exceptions.*;
import wumpus.model.objects.World;
import wumpus.service.game.commands.impl.CommandsImpl;
import wumpus.service.game.impl.GamePlayerImpl;
import wumpus.service.map.impl.MapReaderImpl;
import wumpus.service.menu.MainMenu;
import wumpus.service.validator.HeroValidator;
import wumpus.service.validator.MapValidator;

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

    public MainMenuImpl(BufferedReader bufferedReader, MapValidator mapValidator, HeroValidator heroValidator) {
        this.bufferedReader = bufferedReader;
        this.mapValidator = mapValidator;
        this.heroValidator = heroValidator;
    }

    public void startMenu() {
        LOGGER.info("\n*** WELCOME TO WUMPUS WORLD ! ***");
        askForUsername();
    }

    private void askForUsername() {
        sc = new Scanner(System.in);
        try {
            String inputUsername = null;
            while (inputUsername == null) {
                LOGGER.info("\nUsername: ");
                inputUsername = sc.next();
            }
            if (inputUsername.length() > 0) {
                LOGGER.info("\nHello, " + inputUsername + "!\n");
                chooseMenu();
            }
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            sc.close();
        }

    }

    public void chooseMenu() {
        LOGGER.info(
                "\n1 - PLAY\n" +
                        "2 - LOAD NEW FILE\n" +
                        "3 - LOAD SAVED FILE\n" +
                        "4 - SAVE\n" +
                        "5 - EXIT"
        );
        LOGGER.info("\nChoose an option from above: ");

        try {
            sc = new Scanner(System.in);
            int chosenMenuOption = sc.nextInt();
            switch (chosenMenuOption) {
                case 1 -> {
                    playGame(); // 1 - play
                    }
                case 2 -> {
                    uploadFromFile(); // 2 - load new file with MapReader
                }
                case 3 -> loadGameFromDB(); // 3 - load saved file -- to be implemented later
                case 4 -> saveGameToDB(); // 4 - save -- to be implemented later
                case 5 -> exitGame(); // 5 - exit
                default -> {
                    LOGGER.info("\nPlease choose a number between 1 and 5!");
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
            LOGGER.info("\nMap loaded succesfully. Ready to play.");
        }
        chooseMenu();
    }

    @Override
    public void playGame() throws InvalidInputException {
        if (world != null) {
            GamePlayerImpl gameStarter = new GamePlayerImpl(world, this, new CommandsImpl(), new Scanner(System.in));
            gameStarter.startGame();
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
        LOGGER.info("\n*** GOOD BYE ! ***");
        System.exit(0);
    }
}

