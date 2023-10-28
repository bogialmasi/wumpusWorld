package wumpus.model.objects;

public class WorldData {
    private int mapSize;
    private int heroRow;
    private int heroCol;
    Direction heroDirection;

    public WorldData(int mapSize, int heroRow, int heroCol, Direction heroDirection) {
        this.mapSize = mapSize;
        this.heroRow = heroRow;
        this.heroCol = heroCol;
        this.heroDirection = heroDirection;
    }

    public int getMapSize() {
        return mapSize;
    }
    public int getHeroRow() {
        return heroRow;
    }

    public int getHeroCol() {
        return heroCol;
    }

    public Direction getHeroDirection() {
        return heroDirection;
    }

}
