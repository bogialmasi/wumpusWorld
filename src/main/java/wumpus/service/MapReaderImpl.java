package wumpus.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import wumpus.constants.Constants;
import wumpus.exceptions.*;
import wumpus.model.objects.*;
import wumpus.service.validator.HeroValidator;
import wumpus.service.validator.MapValidator;
import wumpus.model.objects.World;

import java.awt.*;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;

public class MapReaderImpl implements MapReader {
    private int mapSize = 0; //N
    private static final Logger LOGGER = LoggerFactory.getLogger(MapReaderImpl.class);

    private final MapValidator mapValidator;
    private final BufferedReader reader;
    private final HeroValidator heroValidator;
    private World world = null;


    public MapReaderImpl(MapValidator mapValidator, BufferedReader reader, HeroValidator heroValidator) {
        this.mapValidator = mapValidator;
        this.reader = reader;
        this.heroValidator = heroValidator;
    }

    private Direction parseHeroDir(String heroDir) throws InvalidPositionException {
        return switch (heroDir) {
            case Constants.NORTH -> Direction.N;
            case Constants.EAST -> Direction.E;
            case Constants.WEST -> Direction.W;
            case Constants.SOUTH -> Direction.S;
            default -> throw new InvalidPositionException("Hero's direction is invalid");
        };
    }


    public World readMap() throws InvalidSizeException, IOException, InvalidObjectAmountException, HeroException, InvalidPositionException, InvalidInputException {
        int row = 0;
        Hero hero;
        try {
            String line = reader.readLine();
            String[] worldData = line.split(" ");
            if(worldData.length != 4){
                throw new InvalidInputException("Could not read first line");
            }
            mapSize = Integer.parseInt(worldData[0]);
            int heroCol = (int) worldData[1].charAt(0) % Constants.ASCII_STARTINGPOINT;
            int heroRow = Integer.parseInt(worldData[2])-1;
            Direction heroDir = parseHeroDir(worldData[3]);
            LOGGER.trace("Position of Hero : column={} row={} direction={}", heroCol, heroRow, heroDir);
            world = new World(mapSize);

            hero = new Hero(new Point(heroRow, heroCol), heroDir);
            line = reader.readLine();

            while (line != null) {
                String[] elements = line.split("");
                mapValidator.validateColumnSize(mapSize, elements.length);
                for (int column = 0; column < elements.length; column++) {
                    switch (elements[column]) { // collects objects separated by class
                        case Constants.WALL -> {
                            Wall wall = new Wall(new Point(row, column));
                            //world.walls.add(wall);
                            world.gameObjects.add(wall);
                            world.map[row][column] = Constants.WALL;
                        }
                        case Constants.WUMPUS -> {
                            Wumpus wumpus = new Wumpus(new Point(row, column));
                            //world.wumpuses.add(wumpus);
                            world.gameObjects.add(wumpus);
                            world.map[row][column] = Constants.WUMPUS;
                        }
                        case Constants.GOLD -> {
                            mapValidator.validateGold(world);
                            Gold gold = new Gold(new Point(row, column));
                            //world.gold = gold;
                            world.gameObjects.add(new Gold(new Point(row, column)));
                            world.map[row][column] = Constants.GOLD;
                        }
                        case Constants.HERO -> {
                            mapValidator.validateHero(world);
                            mapValidator.isThereAnythingOnThisPosition(new Point(row, column), world);
                            heroValidator.validateHeroPosition(hero, world, row, column);
                            heroValidator.validateHeroStartingPosition(new Point(row, column), hero.getPos());
                            world.gameObjects.add(hero);
                            world.map[row][column] = Constants.HERO;
                        }
                        case Constants.PIT -> {
                            Pit pit = new Pit(new Point(row, column));
                            //world.pits.add(pit);
                            world.gameObjects.add(pit);
                            world.map[row][column] = Constants.PIT;
                        }
                        case Constants.EMPTY -> {
                            //world.emptyFields.add(new Point(row, column));
                            world.gameObjects.add(new GameObject(new Point(row, column), Constants.EMPTY));
                            world.map[row][column] = Constants.EMPTY;
                        }
                        default -> {
                        }
                    }
                }
                row++;
                line = reader.readLine();
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        finally {
            reader.close();
        }

        world.showMap();
        validateMap(row);

        //hero loadout
        hero.setArrows(world.getWumpuses().size());

        return world;
    }

    private void validateMap(int row) throws InvalidSizeException, InvalidObjectAmountException, InvalidPositionException {
        mapValidator.validateRowSize(mapSize, row);
        mapValidator.validateSizeOfMap(mapSize, world);
        mapValidator.validateAmountOfWumpuses(world);
        mapValidator.validateWallsOnEdgesOfMap(world);
    }

}

