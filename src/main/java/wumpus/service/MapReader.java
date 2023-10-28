package wumpus.service;

import wumpus.exceptions.*;
import wumpus.model.objects.World;

import java.io.IOException;

public interface MapReader {
    World readMap() throws InvalidSizeException, IOException, InvalidObjectAmountException, HeroException, InvalidPositionException, InvalidInputException;
}
