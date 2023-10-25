package wumpus.validator;

public interface IHeroMovesValidator {
    public boolean canHeroGoThere(String[][] goToSquare);
    public int numOfArrows();
    public boolean isHeroAlive();
    public boolean isHeroShootingWall(); // if not, hero shoots WUMPUS
    boolean doesHeroHaveTheGold(); 
}
