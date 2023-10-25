package wumpus.validator;

public class HeroMovesValidator implements IHeroMovesValidator {
    @Override
    public boolean canHeroGoThere(String[][] goToSquare) {
        return false;
    }

    @Override
    public int numOfArrows() {
        return 0;
    }

    @Override
    public boolean isHeroAlive() {
        return false;
    }

    @Override
    public boolean isHeroShootingWall() {
        return false;
    }

    @Override
    public boolean doesHeroHaveTheGold() {
        return false;
    }
}
