package wumpus.menu;

import wumpus.exceptions.InvalidInputException;

import java.util.Scanner;

public class MainMenu implements MenuOptions {
    Scanner sc = new Scanner(System.in);

    public void startMenu() throws InvalidInputException {
        System.out.println("*** WELCOME TO WUMPUS WORLD! ***");
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
            case 1:
                break;
            case 2:
                break;
            case 3:
                break;
            case 4:
                break;
            case 5:
                break;
            default:
                throw new InvalidInputException("Please choose a number between 1 and 5!");
        }
    }

    @Override
    public void uploadFromFile() {

    }

    @Override
    public void playGame() {

    }

    @Override
    public void saveGameToDB() {
        System.out.println("This Function does not work yet :( Come back later");
    }

    @Override
    public void loadGameFromDB() {
        System.out.println("This Function does not work yet :( Come back later");
    }

    @Override
    public void backToMenu() {
    }
}

