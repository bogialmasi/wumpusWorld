package wumpus.service;

import wumpus.exceptions.HeroAttributeException;
import wumpus.exceptions.HeroException;
import wumpus.exceptions.InvalidObjectAmountException;
import wumpus.exceptions.InvalidSizeException;
import wumpus.world.World;

import java.io.IOException;

public interface IMapReader {
    World readMap() throws InvalidSizeException, IOException, InvalidObjectAmountException, HeroAttributeException, HeroException;
}
