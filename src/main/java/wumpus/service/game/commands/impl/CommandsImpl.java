package wumpus.service.game.commands.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import wumpus.constants.Constants;
import wumpus.model.objects.*;
import wumpus.service.game.commands.Commands;

import java.awt.*;

public class CommandsImpl implements Commands {
    private static final Logger LOGGER = LoggerFactory.getLogger(CommandsImpl.class);
    @Override
    public void goUp(World world) {
        Hero hero = world.getHero();
        int heroY = ((int) (world.getHero().getPos().getY()));
        int heroX = ((int) (world.getHero().getPos().getX()));
        int provisionalX = heroX - 1;
        int provisionalY = heroY;
        Point provisionalPoint = new Point(provisionalX, provisionalY);
        switch (world.map[provisionalX][provisionalY]) {
            case Constants.WALL -> LOGGER.info("Hero cannot go up");
            case Constants.PIT -> {
                handleHeroMove("w", world);
                heroLoseArrow(world);
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
    public void goDown(World world) {
        Hero hero = world.getHero();
        int heroY = ((int) (world.getHero().getPos().getY()));
        int heroX = ((int) (world.getHero().getPos().getX()));
        int provisionalX = heroX + 1;
        int provisionalY = heroY;
        Point provisionalPoint = new Point(provisionalX, provisionalY);
        switch (world.map[provisionalX][provisionalY]) {
            case Constants.WALL -> LOGGER.info("Hero cannot go down");
            case Constants.PIT -> {
                handleHeroMove("s", world);
                heroLoseArrow(world);
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
    public void goRight(World world) {
        Hero hero = world.getHero();
        int heroY = ((int) (world.getHero().getPos().getY()));
        int heroX = ((int) (world.getHero().getPos().getX()));
        int provisionalX = heroX;
        int provisionalY = heroY + 1;
        Point provisionalPoint = new Point(provisionalX, provisionalY);
        switch (world.map[provisionalX][provisionalY]) {
            case Constants.WALL -> LOGGER.info("Hero cannot go right");
            case Constants.PIT -> {
                handleHeroMove("d", world);
                heroLoseArrow(world);
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
    public void goLeft(World world) {
        Hero hero = world.getHero();
        int heroY = ((int) (world.getHero().getPos().getY()));
        int heroX = ((int) (world.getHero().getPos().getX()));
        int provisionalX = heroX;
        int provisionalY = heroY - 1;
        Point provisionalPoint = new Point(provisionalX, provisionalY);
        switch (world.map[provisionalX][provisionalY]) {
            case Constants.WALL -> LOGGER.info("Hero cannot go left");
            case Constants.PIT -> {
                handleHeroMove("a", world);
                heroLoseArrow(world);
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
    public void shoot(World world) {
        Hero hero = world.getHero();
        int arrowPosX = (int) hero.getPos().getX();
        int arrowPosY = (int) hero.getPos().getY();
        heroLoseArrow(world);
        switch (hero.getDir()) {
            case N:
                LOGGER.info("Hero shoots north.");
                // hero is looking upwards, check if wall is in the way, wumpus dies if in the way and not covered by wall
                while (arrowPosX > 0) {
                    handleArrowShoot(world, arrowPosX, arrowPosY);
                    arrowPosX--;
                }
                break;
            case E:
                LOGGER.info("Hero shoots east.");
                // hero is looking right
                while (arrowPosY < world.getN()) {
                    handleArrowShoot(world, arrowPosX, arrowPosY);
                    arrowPosY++;
                }
                break;
            case W:
                LOGGER.info("Hero shoots west.");
                // hero is looking left
                while (arrowPosY < 0) {
                    handleArrowShoot(world, arrowPosX, arrowPosY);
                    arrowPosY--;
                }
                break;
            case S:
                LOGGER.info("Hero shoots south.");
                // hero is looking down
                while (arrowPosX < world.getN()) {
                    handleArrowShoot(world, arrowPosX, arrowPosY);
                    arrowPosX++;
                }
                break;
        }
    }

    private void handleArrowShoot(World world, int arrowPosX, int arrowPosY) {
        switch (world.map[arrowPosX][arrowPosY]) {
            case Constants.WALL -> {
                LOGGER.info("Arrow shot into wall");
            }
            case Constants.WUMPUS -> {
                LOGGER.info("Arrow shot wumpus. Wumpus dies");
                world.killWumpus(arrowPosX, arrowPosY);
            }
        }
    }


    @Override
    public void pickUpGold(World world) {
        Hero hero = world.getHero();
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
        Hero hero = world.getHero();
        int heroY = ((int) (hero.getPos().getY()));
        int heroX = ((int) (hero.getPos().getX()));
        if (hero.getStartingPosition() == hero.getPos()) {
            world.map[heroX][heroY] = Constants.EMPTY;
            world.gameObjects.add(new GameObject(new Point(heroX, heroY), Constants.EMPTY));
        }
        isThisTheFirstStep = false;
    }

    void heroLoseArrow(World world) {
        Hero hero = world.getHero();
        if (hero.getArrows() > 0) {
            hero.setArrows(hero.getArrows() - 1);
            LOGGER.info("Hero lost an arrow. Current number of arrows = {}", hero.getArrows());
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
                if (!world.getHero().hasGold()) {
                    Gold gold = ((Gold) world.getGold());
                    int goldX = (int) gold.getPos().getX();
                    int goldY = (int) gold.getPos().getY();
                    world.map[goldX][goldY] = Constants.GOLD;
                }
            }
        }
    }
}
