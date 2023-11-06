package wumpus.service.game.commands.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import wumpus.constants.Constants;
import wumpus.model.objects.Direction;
import wumpus.model.objects.GameObject;
import wumpus.model.objects.Hero;
import wumpus.model.objects.World;
import wumpus.service.game.commands.Commands;

import java.awt.*;

public class CommandsImpl implements Commands {
    private static final Logger LOGGER = LoggerFactory.getLogger(CommandsImpl.class);

    /*
        // TODO tests for goUp, goDown, goLeft, goRight!
            GameOver method in GamePlayer!
            Hero stays in GOLD after leaving the position
            Hero does not move off gold after stepping on it
            */
    @Override
    public void goUp(Hero hero, World world) {
        int heroY = ((int) (world.getHero().getPos().getY()));
        int heroX = ((int) (world.getHero().getPos().getX()));
        switch (world.map[heroX - 1][heroY]) {
            case Constants.WALL -> LOGGER.info("Hero cannot go up");
            case Constants.PIT -> {
                handleHeroMove("w", world);
                heroStepOnPitLoseArrow(world, hero);
//                hero.setArrows(hero.getArrows() - 1);
//                LOGGER.info("Hero stepped on a pit and lost an arrow. Current number of arrows: {}", hero.getArrows());
                hero.setPos(new Point(heroX - 1, heroY));
            }
            case Constants.WUMPUS -> {
                handleHeroMove("w", world);
                gameOver(world);
                LOGGER.info("Hero stepped on Wumpus and died. Game Over");
                hero.setPos(new Point(heroX - 1, heroY));
            }
            case Constants.GOLD -> {
                handleHeroMove("w", world);
                LOGGER.info("Hero is standing on gold. Pres \"g\" to pick it up!");
            }
            default -> {  // empty
                handleHeroMove("w", world);
                hero.setPos(new Point(heroX - 1, heroY));
            }
        }
    }

    @Override
    public void goDown(Hero hero, World world) {
        int heroY = ((int) (world.getHero().getPos().getY()));
        int heroX = ((int) (world.getHero().getPos().getX()));
        switch (world.map[heroX + 1][heroY]) {
            case Constants.WALL -> LOGGER.info("Hero cannot go down");
            case Constants.PIT -> {
                handleHeroMove("s", world);
                heroStepOnPitLoseArrow(world, hero);
//                hero.setArrows(hero.getArrows() - 1);
//                LOGGER.info("Hero stepped on a pit and lost an arrow. Current number of arrows: {}", hero.getArrows());
                hero.setPos(new Point(heroX + 1, heroY));
            }
            case Constants.WUMPUS -> {
                handleHeroMove("s", world);
                gameOver(world);
                LOGGER.info("Hero stepped on Wumpus and died. Game Over");
                hero.setPos(new Point(heroX + 1, heroY));
            }
            case Constants.GOLD -> {
                handleHeroMove("s", world);
                LOGGER.info("Hero is standing on gold. Pres \"g\" to pick it up!");
            }
            default -> {  // empty
                handleHeroMove("s", world);
                hero.setPos(new Point(heroX + 1, heroY));
            }
        }
    }

    @Override
    public void goRight(Hero hero, World world) {
        int heroY = ((int) (world.getHero().getPos().getY()));
        int heroX = ((int) (world.getHero().getPos().getX()));
        switch (world.map[heroX][heroY + 1]) {
            case Constants.WALL -> LOGGER.info("Hero cannot go right");
            case Constants.PIT -> {
                handleHeroMove("d", world);
                heroStepOnPitLoseArrow(world, hero);
//                hero.setArrows(hero.getArrows() - 1);
//                LOGGER.info("Hero stepped on a pit and lost an arrow. Current number of arrows: {}", hero.getArrows());
                hero.setPos(new Point(heroX, heroY + 1));
            }
            case Constants.WUMPUS -> {
                handleHeroMove("d", world);
                gameOver(world);
                LOGGER.info("Hero stepped on Wumpus and died. Game Over");
                hero.setPos(new Point(heroX, heroY + 1));
            }
            case Constants.GOLD -> {
                handleHeroMove("d", world);
                LOGGER.info("Hero is standing on gold. Pres \"g\" to pick it up!");
            }
            default -> {  // empty
                handleHeroMove("d", world);
                hero.setPos(new Point(heroX, heroY + 1));
            }
        }
    }

    @Override
    public void goLeft(Hero hero, World world) {
        int heroY = ((int) (world.getHero().getPos().getY()));
        int heroX = ((int) (world.getHero().getPos().getX()));
        switch (world.map[heroX][heroY - 1]) {
            case Constants.WALL -> LOGGER.info("Hero cannot go right");
            case Constants.PIT -> {
                handleHeroMove("a", world);
                heroStepOnPitLoseArrow(world, hero);
//                hero.setArrows(hero.getArrows() - 1);
//                LOGGER.info("Hero stepped on a pit and lost an arrow. Current number of arrows: {}", hero.getArrows());
                hero.setPos(new Point(heroX, heroY - 1));
            }
            case Constants.WUMPUS -> {
                handleHeroMove("a", world);
                gameOver(world);
                LOGGER.info("Hero stepped on Wumpus and died. Game Over");
                hero.setPos(new Point(heroX, heroY - 1));
            }
            case Constants.GOLD -> {
                handleHeroMove("a", world);
                LOGGER.info("Hero is standing on gold. Pres \"g\" to pick it up!");
            }
            default -> {  // empty
                handleHeroMove("a", world);
                hero.setPos(new Point(heroX, heroY - 1));
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
            hero.setHasGold(true);
            world.map[world.getGold().getPos().x][world.getGold().getPos().y] = Constants.EMPTY;
            world.getEmptyFields().add(new GameObject(new Point(world.getGold().getPos().x, world.getGold().getPos().y), Constants.EMPTY));
            LOGGER.info("Hero has the gold now. Go back to the starting position: {}", hero.getStartingPosition());
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

    @Override
    public void handleHeroMove(String command, World world) {
        int heroY = ((int) (world.getHero().getPos().getY()));
        int heroX = ((int) (world.getHero().getPos().getX()));
        resetObjectPositions(world, Constants.PIT);
        resetObjectPositions(world, Constants.EMPTY);
        switch (command) {
            case "w" -> {
                //world.map[heroX][heroY] = Constants.EMPTY;
                world.map[heroX - 1][heroY] = Constants.HERO;
            }
            case "a" -> {
                //world.map[heroX][heroY] = Constants.EMPTY;
                world.map[heroX][heroY - 1] = Constants.HERO;
            }
            case "s" -> {
                //world.map[heroX][heroY] = Constants.EMPTY;
                world.map[heroX + 1][heroY] = Constants.HERO;
            }
            case "d" -> {
                //world.map[heroX][heroY] = Constants.EMPTY;
                world.map[heroX][heroY + 1] = Constants.HERO;
            }
        }
    }

    @Override
    public void gameOver(World world) {
        LOGGER.info("GAME OVER");
        world.getHero().setPos(null);
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
        }
    }
}
