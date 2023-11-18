package wumpus.service.map;

import wumpus.exceptions.*;
import wumpus.model.objects.World;

import java.io.IOException;
import java.util.ArrayList;

public interface MapReader {
    World readMap(ArrayList<String> lines) throws InvalidSizeException, IOException, InvalidObjectAmountException, HeroException, InvalidPositionException, InvalidInputException;
}
