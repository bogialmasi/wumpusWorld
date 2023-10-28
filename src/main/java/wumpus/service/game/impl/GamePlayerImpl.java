package wumpus.service.game.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import wumpus.model.objects.Hero;
import wumpus.model.objects.World;
import wumpus.service.game.GamePlayer;
import wumpus.service.game.commands.Commands;
import wumpus.service.menu.MainMenu;

import java.util.Scanner;

public class GamePlayerImpl implements GamePlayer {

    private Scanner sc;
    private World world;
    private final MainMenu mainMenu;
    private static final Logger LOGGER = LoggerFactory.getLogger(GamePlayerImpl.class);
    private Commands commands;

    public GamePlayerImpl(World world, MainMenu mainMenu, Commands commands, Scanner sc) {
        this.world = world;
        this.mainMenu = mainMenu;
        this.commands = commands;
        this.sc = sc;
    }

    @Override
    public void startGame() {
        world.showMap();
        Hero hero = ((Hero) (world.getHero()));
        LOGGER.info("Current Hero Direction = {}", ((Hero) (world.getHero())).getDir());
        sc = new Scanner(System.in);
        showCommands();
        String command = sc.next();
        while (!command.equals("z")) {
            switch (command) {
                case "w":
                    //goUp();
                    break;
                case "s":
                    //goDown();
                    break;
                case "a":
                    //goLeft();
                    break;
                case "d":
                    //goRight();
                    break;
                case "x":
                    //shoot();
                    break;
                case "g":
                    //pickUpGold();
                    break;
                case "l":
                    hero.setDir(commands.turnLeft(hero.getDir()));
                    break;
                case "r":
                    hero.setDir(commands.turnRight(hero.getDir()));
                    break;
            }
            LOGGER.info("Current Hero Direction = {}", hero.getDir());
            world.showMap();
            showCommands();
            command = sc.next();
        }
    }

    private void showCommands() {
        System.out.println("\nw - up , s - down , a - left, d - right" +
                "\nx - shoot , g - pick up gold" +
                "\nl - turn left , r - turn right" +
                "\nz - quit level" +
                "\npick a command!:");
    }

    public void backToMainMenu() {
        mainMenu.chooseMenu();
    }
}
