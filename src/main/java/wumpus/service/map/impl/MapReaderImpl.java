package wumpus.service.map.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import wumpus.constants.Constants;
import wumpus.exceptions.*;
import wumpus.model.objects.*;
import wumpus.service.database.DataBaseContextService;
import wumpus.service.map.MapReader;
import wumpus.service.validator.HeroValidator;
import wumpus.service.validator.MapValidator;

import java.awt.*;
import java.util.ArrayList;

public class MapReaderImpl implements MapReader {
    private static final Logger LOGGER = LoggerFactory.getLogger(MapReaderImpl.class);
    private final MapValidator mapValidator;
    private final HeroValidator heroValidator;
    private World world = null;
    private DataBaseContextService dataBaseContextService;

    public MapReaderImpl(MapValidator mapValidator, HeroValidator heroValidator) {
        this.mapValidator = mapValidator;
        this.heroValidator = heroValidator;
    }

    private Direction parseHeroDir(String heroDir) throws InvalidPositionException {
        switch (heroDir) {
            case Constants.NORTH:
                return Direction.N;
            case Constants.EAST:
                return Direction.E;
            case Constants.WEST:
                return Direction.W;
            case Constants.SOUTH:
                return Direction.S;
            default:
                LOGGER.error("Hero's direction is invalid. Exception = ");
                throw new InvalidPositionException("Hero's direction is invalid");
        }
    }

    public World readMap(ArrayList<String> lines) throws InvalidSizeException, InvalidObjectAmountException, HeroException, InvalidPositionException, InvalidInputException {
        int row = 0;
        Hero hero;
        WorldData worldData;
        String line = lines.get(0);
        String[] header = line.split(" ");
        validateHeaderData(header);
        worldData = parseHeaderData(header);
        world = new World(worldData.getMapSize());

        hero = new Hero(new Point(worldData.getHeroRow(), worldData.getHeroCol()), worldData.getHeroDirection());
        world.gameObjects.add(hero);
        world.map[worldData.getHeroRow()][worldData.getHeroCol()] = Constants.HERO;
        for (int i = 1; i < lines.size(); i++) {
            line = lines.get(i);
            String[] elements = line.split("");
            mapValidator.validateColumnSize(worldData.getMapSize(), elements.length);
            for (int column = 0; column < elements.length; column++) {
                Point actualPosition = new Point(row, column);
                switch (elements[column]) { // collects objects separated by class
                    case Constants.WALL -> {
                        if (mapValidator.validateHeroIsNotOnThisPosition(hero.getPos(), actualPosition)) {
                            Wall wall = new Wall(actualPosition);
                            world.gameObjects.add(wall);
                            world.map[row][column] = Constants.WALL;
                        } else {
                            throw new InvalidInputException("In wall");
                        }
                    }
                    case Constants.WUMPUS -> {
                        if (mapValidator.validateHeroIsNotOnThisPosition(hero.getPos(), actualPosition)) {
                            Wumpus wumpus = new Wumpus(actualPosition);
                            world.gameObjects.add(wumpus);
                            world.map[row][column] = Constants.WUMPUS;
                        } else {
                            throw new InvalidInputException("In wumpus");
                        }
                    }
                    case Constants.GOLD -> {
                        if (mapValidator.validateHeroIsNotOnThisPosition(hero.getPos(), actualPosition)) {
                            mapValidator.validateOnlyOneGoldExists(world);
                            Gold gold = new Gold(actualPosition);
                            world.gameObjects.add(gold);
                            world.map[row][column] = Constants.GOLD;
                        } else {
                            throw new InvalidInputException("In gold");
                        }
                    }
                    case Constants.HERO -> {
                        heroValidator.validateHeroPositionIsInsideMap(world, row, column);
                        heroValidator.validateHeroStartingPosition(actualPosition, hero.getPos());
                    }
                    case Constants.PIT -> {
                        if (mapValidator.validateHeroIsNotOnThisPosition(hero.getPos(), actualPosition)) {
                            Pit pit = new Pit(actualPosition);
                            world.gameObjects.add(pit);
                            world.map[row][column] = Constants.PIT;
                        } else {
                            throw new InvalidInputException("In pit");
                        }
                    }
                    case Constants.EMPTY -> {
                        if (mapValidator.validateHeroIsNotOnThisPosition(hero.getPos(), actualPosition)) {
                            world.gameObjects.add(new GameObject(actualPosition, Constants.EMPTY));
                            world.map[row][column] = Constants.EMPTY;
                        } // else = gero gets put on the map where empty is. no need for exception
                    }
                    default -> {
                    }
                }
            }
            row++;
        }

        world.showMap();
        validateMap(row, worldData);

        hero.setArrows(world.getWumpuses().size());

        return world;
    }

    private WorldData parseHeaderData(String[] header) throws InvalidPositionException {
        int mapSize = Integer.parseInt(header[0]);
        int heroCol = (int) header[1].charAt(0) % Constants.ASCII_STARTINGPOINT;
        int heroRow = Integer.parseInt(header[2]) - 1;
        Direction heroDir = parseHeroDir(header[3]);
        LOGGER.trace("Position of Hero : column={} row={} direction={}", header[1].charAt(0), heroRow + 1, heroDir);

        return new WorldData(mapSize, heroRow, heroCol, heroDir);
    }

    private void validateHeaderData(String[] worldData) throws InvalidInputException {
        if (worldData.length != 4) {
            throw new InvalidInputException("Could not read first line");
        }
    }

    private void validateMap(int row, WorldData worldData) throws InvalidSizeException, InvalidObjectAmountException, InvalidPositionException, HeroException {
        mapValidator.validateRowSize(worldData.getMapSize(), row);
        mapValidator.validateSizeOfMap(worldData.getMapSize(), world);
        mapValidator.validateAmountOfWumpuses(world);
        mapValidator.validateWallsOnEdgesOfMap(world);
    }


}

