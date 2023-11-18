package wumpus.service.database;

public class DataBaseContextService {
    private String playerName;
    private String heroData;
    private String worldMap;
    private int numberOfMoves;

    public DataBaseContextService() {
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public String getHeroData() {
        return heroData;
    }

    public void setHeroData(String heroData) {
        this.heroData = heroData;
    }

    public String getWorldMap() {
        return worldMap;
    }

    public void setWorldMap(String worldMap) {
        this.worldMap = worldMap;
    }

    public int getNumberOfMoves() {
        return numberOfMoves;
    }

    public void setNumberOfMoves(int numberOfMoves) {
        this.numberOfMoves = numberOfMoves;
    }
}
