package wumpus.menu;

import wumpus.exceptions.InvalidInputException;
import wumpus.service.MapReader;
import wumpus.world.World;

import java.util.Scanner;

public class MainMenu implements MenuOptions {
    Scanner sc = new Scanner(System.in);
    World playMap = null;

    public void startMenu() throws InvalidInputException {
        System.out.println("*** WELCOME TO WUMPUS WORLD ! ***");
        askForUsername();
    }

    public void askForUsername() throws InvalidInputException {
        String inputUsername = null;
        while (inputUsername == null) {
            System.out.print("Username: ");
            inputUsername = sc.next();
        }
        if (inputUsername.length() > 0) {
            System.out.println("Hello, " + inputUsername + "!\n");
            chooseMenu();
        }
    }

    public void chooseMenu() throws InvalidInputException {
        System.out.println(
                        "1 - PLAY\n" +
                                "2 - LOAD NEW FILE\n" +
                                "3 - LOAD SAVED FILE\n"+
                                "4 - SAVE\n" +
                                "5 - EXIT"
        );
        System.out.println("Choose an option from above: ");
        int chosenMenuOption = sc.nextInt();
        switch (chosenMenuOption) {
            case 1 -> playGame(); // 1 - play
            case 2 -> uploadFromFile(); // 2 - load new file with MapReader
            case 3 -> loadGameFromDB(); // 3 - load saved file -- to be implemented later
            case 4 -> saveGameToDB(); // 4 - save -- to be implemented later
            case 5 -> exitGame(); // 5 - exit
            default -> {
                System.out.println("Please choose a number between 1 and 5!");
                backToMenu();
            }
        }
    }

    @Override
    public void uploadFromFile() throws InvalidInputException {
        MapReader mapReader = new MapReader();
        playMap = mapReader.readMap();
        if(playMap == null){
            System.out.println("An error occured. Please try again!");
        } else {
            System.out.println("Map loaded succesfully. Ready to play.");
        }
        backToMenu();
    }

    @Override
    public void playGame() throws InvalidInputException {
        if(playMap!=null){
            // IMPLEMENT GAME STARTER
        }
        else {
            System.out.println("Load a map first to play!");
            backToMenu();
        }
    }

    @Override
    public void saveGameToDB() throws InvalidInputException {
        System.out.println("This Function does not work yet :( Come back later");
        backToMenu();
    }

    @Override
    public void loadGameFromDB() throws InvalidInputException {
        System.out.println("This Function does not work yet :( Come back later");
        backToMenu();
    }

    @Override
    public void backToMenu() throws InvalidInputException {
        chooseMenu();
    }

    @Override
    public void exitGame() {
        System.out.println("*** GOOD BYE ! ***");
        System.exit(0);
    }


}

