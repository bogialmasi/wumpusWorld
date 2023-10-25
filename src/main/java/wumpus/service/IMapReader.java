package wumpus.service;

import wumpus.exceptions.InvalidSizeException;
import wumpus.world.World;

import java.io.IOException;

public interface IMapReader {
    World readMap() throws InvalidSizeException, IOException;
}
