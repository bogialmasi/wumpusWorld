package wumpus.service.game.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import wumpus.model.objects.Hero;
import wumpus.model.objects.World;
import wumpus.service.game.GamePlayer;
import wumpus.service.game.commands.Commands;
import wumpus.service.game.commands.impl.CommandsImpl;
import wumpus.service.menu.MainMenu;

import java.util.Scanner;

public class GamePlayerImpl implements GamePlayer {

    private Scanner sc;
    private World world;
    private final MainMenu mainMenu;
    private static final Logger LOGGER = LoggerFactory.getLogger(GamePlayerImpl.class);
    private Commands commands;

    public GamePlayerImpl(World world, MainMenu mainMenu, Commands commands) {
        this.world = world;
        this.mainMenu = mainMenu;
        this.commands = commands;
    }

    @Override
    public void startGame() {
        world.showMap();
        sc = new Scanner(System.in);
        String command = sc.next();
        while (!command.equals("z")) {
            //kiirja a commandokat
            System.out.println("\nw - up , s - down , a - left, d - right" +
                    "\n x - shoot , g - pick up gold" +
                    "\n l - turn left , r - turn right");
            command = sc.next();

            switch (command){
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
                    commands.turnLeft(((Hero)world.getHero()).getDir());
                    break;
                case "r":
                    commands.turnRight(((Hero)world.getHero()).getDir());
                    break;
            }
        }


        //command inputot vár (scan string)
        //inputra method call
    }

    public void BackToMainMenu() {
        mainMenu.chooseMenu();
    }
}
