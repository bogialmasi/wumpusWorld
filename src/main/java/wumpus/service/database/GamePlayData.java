package wumpus.service.database;

import java.util.Arrays;

public class GamePlayData {
    private int id;
    private String playerName;
    private String[][] worldMap;
    private int numberOfMoves;

    public GamePlayData(int id, String playerName, String[][] worldMap, int numberOfMoves) {
        this.id = id; // id
        this.playerName = playerName; // player_name
        this.worldMap = worldMap; // world_map
        this.numberOfMoves = numberOfMoves; //num_of_moves
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("GamePlayData{");
        sb.append("id=").append(id);
        sb.append(", playerName='").append(playerName).append('\'');
        sb.append(", worldMap=").append(worldMap == null ? "null" : Arrays.asList(worldMap).toString());
        sb.append(", numberOfMoves=").append(numberOfMoves);
        sb.append('}');
        return sb.toString();
    }
}
