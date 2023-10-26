package wumpus.validator;
import wumpus.exceptions.HeroException;
import wumpus.exceptions.InvalidObjectAmountException;
import wumpus.exceptions.InvalidSizeException;
import wumpus.world.World;

public interface IMapValidator {
    void validateSizeOfMap(int mapSize, World world) throws InvalidSizeException;
    void validateRowSize(int mapSize, int rowLength) throws InvalidSizeException;
    boolean validateColumnSize(int mapSize, int columnLength) throws InvalidSizeException;
    void validateContentOfMap(World world) throws InvalidObjectAmountException, HeroException, HeroException;
}
