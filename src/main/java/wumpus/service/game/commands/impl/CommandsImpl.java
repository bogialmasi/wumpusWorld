package wumpus.service.game.commands.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import wumpus.constants.Constants;
import wumpus.exceptions.InvalidInputException;
import wumpus.model.objects.Direction;
import wumpus.model.objects.Hero;
import wumpus.model.objects.World;
import wumpus.service.game.GamePlayer;
import wumpus.service.game.commands.Commands;
import wumpus.service.game.impl.GamePlayerImpl;
import wumpus.service.validator.MapValidator;
import wumpus.service.validator.impl.MapValidatorImpl;

import java.awt.*;

public class CommandsImpl implements Commands {
    //shoot();
    //pickUpGold();
    private static final Logger LOGGER = LoggerFactory.getLogger(CommandsImpl.class);

    @Override
    public void goUp(Hero hero, World world) {
        int heroY = ((int)(world.getHero().getPos().getY()));
        int heroX = ((int)(world.getHero().getPos().getX()));
        if(world.map[heroX-1][heroY].equals(Constants.EMPTY) || world.map[heroX-1][heroY].equals(Constants.GOLD)){
            world.map[heroX][heroY] = Constants.EMPTY;
            world.map[heroX-1][heroY] = Constants.HERO;

            // setting heroPos to new position
            hero.setPos(new Point(heroX-1, heroY));
        }
        else {
            LOGGER.info("Hero cannot go up");
        }
    }

    @Override
    public void goDown(Hero hero, World world) {
        int heroY = ((int)(world.getHero().getPos().getY()));
        int heroX = ((int)(world.getHero().getPos().getX()));
        if(world.map[heroX+1][heroY].equals(Constants.EMPTY) || world.map[heroX-1][heroY].equals(Constants.GOLD)){
            world.map[heroX][heroY] = Constants.EMPTY;
            world.map[heroX+1][heroY] = Constants.HERO;

            hero.setPos(new Point(heroX+1, heroY));
        }
        else {
            LOGGER.info("Hero cannot go down");
        }
    }

    @Override
    public void goRight(Hero hero, World world) {
        int heroY = ((int)(world.getHero().getPos().getY()));
        int heroX = ((int)(world.getHero().getPos().getX()));
        if(world.map[heroX][heroY+1].equals(Constants.EMPTY) || world.map[heroX-1][heroY].equals(Constants.GOLD)){
            world.map[heroX][heroY] = Constants.EMPTY;
            world.map[heroX][heroY+1] = Constants.HERO;

            hero.setPos(new Point(heroX, heroY+1));
        }
        else {
            LOGGER.info("Hero cannot go right");
        }
    }

    @Override
    public void goLeft(Hero hero, World world) {
        int heroY = ((int)(world.getHero().getPos().getY()));
        int heroX = ((int)(world.getHero().getPos().getX()));
        if(world.map[heroX][heroY-1].equals(Constants.EMPTY) || world.map[heroX-1][heroY].equals(Constants.GOLD)){
            world.map[heroX][heroY] = Constants.EMPTY;
            world.map[heroX][heroY+-1] = Constants.HERO;

            hero.setPos(new Point(heroX, heroY-1));
        }
        else {
            LOGGER.info("Hero cannot go left");
        }
    }


    @Override
    public Direction turnLeft(Direction direction) {
        switch (direction) {
            case N:
                return Direction.W;
            case E:
                return Direction.N; // TODO duplicate problem
            case S:
                return Direction.E;
            case W:
                return Direction.S;
            default:
                return Direction.N;
        }
    }

    @Override
    public Direction turnRight(Direction direction) {
        switch (direction) {
            case N:
                return Direction.E;
            case E:
                return Direction.S;
            case S:
                return Direction.W;
            case W:
                return Direction.N; // TODO duplicate problem
            default:
                return Direction.N;
        }
    }
}
