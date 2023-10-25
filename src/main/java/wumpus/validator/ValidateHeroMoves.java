package wumpus.validator;

import wumpus.objects.Hero;

public class ValidateHeroMoves implements HeroMovesValidator {
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
