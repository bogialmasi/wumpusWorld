package wumpus.validator;
import wumpus.exceptions.HeroException;
import wumpus.exceptions.InvalidObjectAmountException;
import wumpus.world.World;

public interface IMapContentValidator {
    boolean validateAmountOfWumpuses(World world) throws InvalidObjectAmountException;
    void validateWallsOnEdgesOfMap(World world);
    void validateHero(World world) throws InvalidObjectAmountException, HeroException;
    void validateGold(World world) throws InvalidObjectAmountException;
    void validatePositionOfObjects(World world);
}
