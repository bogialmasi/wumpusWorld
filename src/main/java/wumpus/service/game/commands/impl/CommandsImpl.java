package wumpus.service.game.commands.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import wumpus.constants.Constants;
import wumpus.model.objects.*;
import wumpus.service.game.commands.Commands;

import java.awt.*;

public class CommandsImpl implements Commands {
    private static final Logger LOGGER = LoggerFactory.getLogger(CommandsImpl.class);

    /*
        // TODO tests for goUp, goDown, goLeft, goRight!
            */
    @Override
    public void goUp(Hero hero, World world) {
        int heroY = ((int) (world.getHero().getPos().getY()));
        int heroX = ((int) (world.getHero().getPos().getX()));
        int provisionalX = heroX - 1;
        int provisionalY = heroY;
        Point provisionalPoint = new Point(provisionalX, provisionalY);
        switch (world.map[provisionalX][provisionalY]) {
            case Constants.WALL -> LOGGER.info("Hero cannot go up");
            case Constants.PIT -> {
                handleHeroMove("w", world);
                heroStepOnPitLoseArrow(world, hero);
                hero.setPos(provisionalPoint);
            }
            case Constants.WUMPUS -> {
                LOGGER.info("Hero stepped on Wumpus and died. Game Over");
                world.setGameOver(true);
            }
            case Constants.GOLD -> {
                LOGGER.info("Hero is standing on gold. Press \"g\" to pick it up!");
                handleHeroMove("w", world);
                hero.setPos(provisionalPoint);
            }
            default -> {  // empty
                world.isGameWon(new Point(provisionalX, provisionalY));
                handleHeroMove("w", world);
                hero.setPos(provisionalPoint);
            }
        }
    }

    @Override
    public void goDown(Hero hero, World world) {
        int heroY = ((int) (world.getHero().getPos().getY()));
        int heroX = ((int) (world.getHero().getPos().getX()));
        int provisionalX = heroX + 1;
        int provisionalY = heroY;
        Point provisionalPoint = new Point(provisionalX, provisionalY);
        switch (world.map[provisionalX][provisionalY]) {
            case Constants.WALL -> LOGGER.info("Hero cannot go down");
            case Constants.PIT -> {
                handleHeroMove("s", world);
                heroStepOnPitLoseArrow(world, hero);
                hero.setPos(provisionalPoint);
            }
            case Constants.WUMPUS -> {
                LOGGER.info("Hero stepped on Wumpus and died. Game Over");
                world.setGameOver(true);
            }
            case Constants.GOLD -> {
                handleHeroMove("s", world);
                hero.setPos(provisionalPoint);
                LOGGER.info("Hero is standing on gold. Press \"g\" to pick it up!");
            }
            default -> {  // empty
                world.isGameWon(provisionalPoint);
                handleHeroMove("s", world);
                hero.setPos(provisionalPoint);
            }
        }
    }

    @Override
    public void goRight(Hero hero, World world) {
        int heroY = ((int) (world.getHero().getPos().getY()));
        int heroX = ((int) (world.getHero().getPos().getX()));
        int provisionalX = heroX;
        int provisionalY = heroY + 1;
        Point provisionalPoint = new Point(provisionalX, provisionalY);
        switch (world.map[provisionalX][provisionalY]) {
            case Constants.WALL -> LOGGER.info("Hero cannot go right");
            case Constants.PIT -> {
                handleHeroMove("d", world);
                heroStepOnPitLoseArrow(world, hero);
                hero.setPos(provisionalPoint);
            }
            case Constants.WUMPUS -> {
                LOGGER.info("Hero stepped on Wumpus and died. Game Over");
                world.setGameOver(true);
            }
            case Constants.GOLD -> {
                handleHeroMove("d", world);
                hero.setPos(provisionalPoint);
                LOGGER.info("Hero is standing on gold. Press \"g\" to pick it up!");
            }
            default -> {  // empty
                world.isGameWon(provisionalPoint);
                handleHeroMove("d", world);
                hero.setPos(provisionalPoint);
            }
        }
    }


    @Override
    public void goLeft(Hero hero, World world) {
        int heroY = ((int) (world.getHero().getPos().getY()));
        int heroX = ((int) (world.getHero().getPos().getX()));
        int provisionalX = heroX;
        int provisionalY = heroY - 1;
        Point provisionalPoint = new Point(provisionalX, provisionalY);
        switch (world.map[provisionalX][provisionalY]) {
            case Constants.WALL -> LOGGER.info("Hero cannot go left");
            case Constants.PIT -> {
                handleHeroMove("a", world);
                heroStepOnPitLoseArrow(world, hero);
                hero.setPos(provisionalPoint);
            }
            case Constants.WUMPUS -> {
                LOGGER.info("Hero stepped on Wumpus and died. Game Over");
                world.setGameOver(true);
            }
            case Constants.GOLD -> {
                handleHeroMove("a", world);
                hero.setPos(provisionalPoint);
                LOGGER.info("Hero is standing on gold. Press \"g\" to pick it up!");
            }
            default -> {  // empty
                world.isGameWon(provisionalPoint);
                handleHeroMove("a", world);
                hero.setPos(provisionalPoint);
            }
        }
    }

    @Override
    public void shoot(Hero hero, World world) {
        /// todo implement
    }

    @Override
    public void pickUpGold(Hero hero, World world) {
        // TODO solve: hero jumps two places after moving off, "H" stays on gold's previous pos after going there again
        if (hero.getPos().equals(world.getGold().getPos())) {
            hero.setHasGold(true);
            int goldX = world.getGold().getPos().x;
            int goldY = world.getGold().getPos().y;
            world.removeGold();
            world.gameObjects.add(new GameObject(new Point(goldX, goldY), Constants.EMPTY));
            LOGGER.info("Hero has the gold now. Go back to the starting position: {}", hero.getStartingPosition());
        } else {
            LOGGER.info("Hero can only pick up gold when standing on it.");
        }

    }

    @Override
    public Direction turnLeft(Direction direction) {
        switch (direction) {
            case N:
                return Direction.W;
            case S:
                return Direction.E;
            case W:
                return Direction.S;
            default: // E
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
            default: // W
                return Direction.N;
        }
    }

    boolean isThisTheFirstStep = true;

    @Override
    public void handleHeroMove(String command, World world) {
        int heroY = ((int) (world.getHero().getPos().getY()));
        int heroX = ((int) (world.getHero().getPos().getX()));
        resetObjectPositions(world, Constants.PIT);
        resetObjectPositions(world, Constants.EMPTY);
        resetObjectPositions(world, Constants.GOLD);
        if (isThisTheFirstStep) resetHeroStartingPositionToEmpty(world);
        switch (command) {
            case "w" -> world.map[heroX - 1][heroY] = Constants.HERO;
            case "a" -> world.map[heroX][heroY - 1] = Constants.HERO;
            case "s" -> world.map[heroX + 1][heroY] = Constants.HERO;
            case "d" -> world.map[heroX][heroY + 1] = Constants.HERO;
        }
    }

    private void resetHeroStartingPositionToEmpty(World world) {
        Hero hero = ((Hero) world.getHero());
        int heroY = ((int) (hero.getPos().getY()));
        int heroX = ((int) (hero.getPos().getX()));
        if (hero.getStartingPosition() == hero.getPos()) {
            world.map[heroX][heroY] = Constants.EMPTY;
            world.gameObjects.add(new GameObject(new Point(heroX, heroY), Constants.EMPTY));
        }
        isThisTheFirstStep = false;
    }

    @Override
    public boolean gameOver(World world, Hero hero) {
        int heroY = ((int) (world.getHero().getPos().getY()));
        int heroX = ((int) (world.getHero().getPos().getX()));
        if (world.map[heroX][heroY].equals(Constants.WUMPUS)) {
            LOGGER.info("**GAME OVER**");
            world.getHero().setPos(new Point(0, 0));
            return true;
        }
        return false;
    }

    void heroStepOnPitLoseArrow(World world, Hero hero) {
        if (hero.getArrows() > 0) {
            hero.setArrows(hero.getArrows() - 1);
            LOGGER.info("Hero stepped on a pit and lost an arrow. Current number of arrows: {}", hero.getArrows());
        } else {
            LOGGER.info("Hero has no more arrows!");
        }
    }

    void resetObjectPositions(World world, String constant) {
        switch (constant) {
            case Constants.PIT -> {
                for (GameObject pit : world.getPits()) {
                    int pitX = (int) pit.getPos().getX();
                    int pitY = (int) pit.getPos().getY();
                    world.map[pitX][pitY] = Constants.PIT;
                }
            }
            case Constants.EMPTY -> {
                for (GameObject empty : world.getEmptyFields()) {
                    int emptyX = (int) empty.getPos().getX();
                    int emptyY = (int) empty.getPos().getY();
                    world.map[emptyX][emptyY] = Constants.EMPTY;
                }
            }
            case Constants.GOLD -> {
                if (!((Hero) world.getHero()).hasGold()) {
                    Gold gold = ((Gold) world.getGold());
                    int goldX = (int) gold.getPos().getX();
                    int goldY = (int) gold.getPos().getY();
                    world.map[goldX][goldY] = Constants.GOLD;
                }
            }
        }
    }
}
