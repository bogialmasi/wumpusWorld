package wumpus.validator;

import wumpus.exceptions.InvalidSizeException;
import wumpus.world.World;

public interface MapValidator {
    void validateSizeOfMap(World world) throws InvalidSizeException;
    void validateContentOfMap(World world);
}
