package wumpus.validator;

import wumpus.exceptions.HeroException;
import wumpus.exceptions.InvalidObjectAmountException;
import wumpus.world.World;

public class MapContentValidator implements IMapContentValidator {

    @Override
    public boolean validateAmountOfWumpuses(World world) throws InvalidObjectAmountException {
        if (world.getN() <= 8) {
            if (world.getWumpuses().size() != 1) {
                throw new InvalidObjectAmountException("Only one wumpus can exist on a " + world.getN() + "size map.");
            }
        } else if (world.getN() >= 9 && world.getN() <= 14) {
            if (world.getWumpuses().size() != 2) {
                throw new InvalidObjectAmountException("Only two wumpuses can exist on a " + world.getN() + "size map.");
            }
        } else {
            if (world.getWumpuses().size() != 3) {
                throw new InvalidObjectAmountException("Only three wumpuses can exist on a " + world.getN() + "size map.");
            }
        }
        return true;
    }

    @Override
    public void validateWallsOnEdgesOfMap(World world) {
        // check that all edges of map are walls
        // check if every object with position in first and last row and col are all walls only
    }

    @Override
    public void validateHero(World world) throws InvalidObjectAmountException, HeroException {
        if (world.getHero() == null) {
            throw new InvalidObjectAmountException("There's no Hero on the map.");
        }
        if (world.getHero().getArrows() != world.getWumpuses().size() && !(validateAmountOfWumpuses(world))) {
            // checks if wumpus amount is correct and the hero has the same amount of arrows as there is wumpuses
            throw new HeroException("The Hero has incorrect amount of arrows.");
        }
        // check that hero stands on correct position, not on walls or on wumpus, starting pos is not on gold or pit either
    }

    @Override
    public void validateGold(World world) throws InvalidObjectAmountException {
        if (world.getGold() == null) {
            throw new InvalidObjectAmountException("There's no Gold on the map.");
        }
    }

    @Override
    public void validatePositionOfObjects(World world) {
        // check that only one object (wall, pit, gold, wumpus) stands on one position
    }
}
