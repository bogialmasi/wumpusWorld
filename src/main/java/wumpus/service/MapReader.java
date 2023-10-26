package wumpus.service;

import wumpus.exceptions.HeroException;
import wumpus.exceptions.InvalidObjectAmountException;
import wumpus.exceptions.InvalidSizeException;
import wumpus.objects.*;
import wumpus.validator.IMapValidator;
import wumpus.world.World;

import java.awt.*;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;

public class MapReader implements IMapReader {
    private int mapSize = 0; //N

    IMapValidator mapValidator;
    BufferedReader reader;

    public MapReader(IMapValidator mapValidator, BufferedReader reader) {
        this.mapValidator = mapValidator;
        this.reader = reader;
    }

    public World readMap() throws InvalidSizeException, IOException, InvalidObjectAmountException, HeroException, HeroException {
        World world = null;
        int row = 0;
        try {
            String line = reader.readLine();
            String[] worldData = line.split(" ");
            mapSize = Integer.parseInt(worldData[0]);
            String heroCol = worldData[1];
            int heroRow = Integer.parseInt(worldData[2]);
            String heroDir = worldData[3];

            world = new World();

            line = reader.readLine();

            while (line != null) { // first line in wumpuszinput contains these data
                String[] elements = line.split("");
                mapValidator.validateColumnSize(mapSize,elements.length);

                for (int column = 0; column < elements.length; column++) {
                    switch (elements[column]) {
                        case "W":
                            world.walls.add(new Wall(new Point(row, column)));
                            break;
                        case "U":
                            world.wumpuses.add(new Wumpus(new Point(row, column)));
                            break;
                        case "G":
                            world.gold = new Gold(new Point(row, column));
                            break;
                        case "H": // hero
                            world.hero = new Hero(new Point(row, column));
                            break;
                        case "P": // pit
                            world.pits.add(new Pit(new Point(row, column)));
                            break;
                        case "_": // empty
                            world.emptyFields.add(new Point(row, column));
                            break;
                        default:
                            break;
                    }
                }

                row++;
                line = reader.readLine();
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            reader.close();
        }

        mapValidator.validateRowSize(mapSize, row);
        mapValidator.validateSizeOfMap(mapSize, world);
        mapValidator.validateContentOfMap(world);

        return world;

    }

}

