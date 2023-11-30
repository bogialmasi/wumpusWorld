package wumpus.service.game.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import wumpus.constants.Constants;
import wumpus.exceptions.InvalidInputException;
import wumpus.model.objects.Direction;
import wumpus.model.objects.Hero;
import wumpus.model.objects.World;
import wumpus.service.database.DataBaseContextService;
import wumpus.service.game.GamePlayer;
import wumpus.service.game.commands.Commands;
import wumpus.service.menu.MainMenu;

import java.util.Scanner;

public class GamePlayerImpl implements GamePlayer {

    private Scanner sc;
    private World world;
    private final MainMenu mainMenu;
    private static final Logger LOGGER = LoggerFactory.getLogger(GamePlayerImpl.class);
    private final Commands commands;
    DataBaseContextService dataBaseContextService;
    int commandCounter = 0;

    public GamePlayerImpl(World world, MainMenu mainMenu, Commands commands, Scanner sc, DataBaseContextService dataBaseContextService) {
        this.world = world;
        this.mainMenu = mainMenu;
        this.commands = commands;
        this.sc = sc;
        this.dataBaseContextService = dataBaseContextService;
    }

    @Override
    public void startGame() throws InvalidInputException {
        world.showMap();
        Hero hero = ((Hero) (world.getHero()));
        LOGGER.info("Current Hero Direction = {}", hero.getDir());
        sc = new Scanner(System.in);
        showCommands();
        String command = sc.next();
        while (!command.equals("z")) { // "z" goes back to main menu and lets player save or exit
            switch (command) {
                case "w":
                    commands.goUp(world);
                    break;
                case "s":
                    commands.goDown(world);
                    break;
                case "a":
                    commands.goLeft(world);
                    break;
                case "d":
                    commands.goRight(world);
                    break;
                case "x":
                    commands.shoot(world);
                    break;
                case "g":
                    commands.pickUpGold(world);
                    break;
                case "l":
                    hero.setDir(commands.turnLeft(hero.getDir()));
                    break;
                case "r":
                    hero.setDir(commands.turnRight(hero.getDir()));
                    break;
                default:
                    LOGGER.info("Invalid input in gameplay menu");
            }
            if (world.isGameOver()) {
                break;
            }
            if(world.isGameWon()){
                LOGGER.info("GAME WON!");
                break;
            }
            commandCounter++;
            LOGGER.info("Current number of moves = {}", commandCounter);
            LOGGER.info("Current Hero Direction = {}", hero.getDir());
            LOGGER.info("Number of arrows = {}", hero.getArrows());
            world.showMap();
            dataBaseContextService.setWorldMap(world.parseMapToString());
            dataBaseContextService.setNumberOfMoves(commandCounter);
            setHeroData(world);
            showCommands();
            command = sc.next();
        }
        mainMenu.chooseMenu();
    }

    private void showCommands() {
        System.out.println("\nw - up , s - down , a - left, d - right" +
                "\nx - shoot , g - pick up gold" +
                "\nl - turn left , r - turn right" +
                "\nz - quit level" +
                "\npick a command!:");
    }

    public void setHeroData(World world){
        StringBuilder builder = new StringBuilder();
        char heroCol = (char) (Constants.ASCII_STARTINGPOINT + world.getHero().getPos().y);
        int heroRow = world.getHero().getPos().x+1;
        Direction heroDir = world.getHero().getDir();
        builder.append(world.getN()).append(" ")
                .append(heroCol).append(" ")
                .append(heroRow).append(" ")
                .append(heroDir.toString());
        dataBaseContextService.setHeroData(builder.toString());
    }
}
