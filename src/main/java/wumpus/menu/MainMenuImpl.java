package wumpus.menu;

import wumpus.exceptions.*;
import wumpus.service.MapReaderImpl;
import wumpus.validator.HeroValidator;
import wumpus.validator.MapContentValidator;
import wumpus.validator.MapValidator;
import wumpus.validator.MapValidatorImpl;
import wumpus.world.World;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Scanner;

public class MainMenuImpl implements MainMenu {
    Scanner sc;
    World world;
    private MapValidator mapValidator;
    BufferedReader bufferedReader;
    private HeroValidator heroValidator;

    public MainMenuImpl(BufferedReader bufferedReader, MapValidator mapValidator) {

        this.bufferedReader = bufferedReader;
        this.mapValidator = mapValidator;
    }

    public void startMenu() throws InvalidInputException {
        System.out.println("*** WELCOME TO WUMPUS WORLD ! ***");
        askForUsername();
    }

    public void askForUsername() throws InvalidInputException {
        sc = new Scanner(System.in);
        try {
            String inputUsername = null;
            while (inputUsername == null) {
                System.out.print("Username: ");
                inputUsername = sc.next();
            }
            if (inputUsername.length() > 0) {
                System.out.println("Hello, " + inputUsername + "!\n");
                chooseMenu();
            }
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            sc.close();
        }

    }

    public void chooseMenu() throws InvalidInputException {
        System.out.println(
                "1 - PLAY\n" +
                        "2 - LOAD NEW FILE\n" +
                        "3 - LOAD SAVED FILE\n" +
                        "4 - SAVE\n" +
                        "5 - EXIT"
        );
        System.out.println("Choose an option from above: ");

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
                    System.out.println("Please choose a number between 1 and 5!");
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
    public void uploadFromFile() throws InvalidInputException, InvalidSizeException, IOException, InvalidObjectAmountException, HeroException, HeroException, InvalidPositionException {
        MapReaderImpl mapReaderImpl = new MapReaderImpl(mapValidator, bufferedReader, heroValidator);
        world = mapReaderImpl.readMap();
        if (world == null) {
            System.out.println("An error occured. Please try again!");
        } else {
            System.out.println("Map loaded succesfully. Ready to play.");
        }

        chooseMenu();
    }

    @Override
    public void playGame() throws InvalidInputException {
        if (world != null) {
            //TODO IMPLEMENT GAME STARTER
        } else {
            System.out.println("Load a map first to play!");
            chooseMenu();
        }
    }

    @Override
    public void saveGameToDB() throws InvalidInputException {
        System.out.println("This Function does not work yet.");
        chooseMenu();
    }

    @Override
    public void loadGameFromDB() throws InvalidInputException {
        System.out.println("This Function does not work yet.");
        chooseMenu();
    }


    @Override
    public void exitGame() {
        System.out.println("*** GOOD BYE ! ***");
        System.exit(0);
    }
}

