package wumpus.service.database.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import wumpus.service.database.DataBaseContextService;
import wumpus.service.database.PlayerRepository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;

public class PlayerRepositoryImpl implements PlayerRepository {

    private static final Logger LOGGER = LoggerFactory.getLogger(PlayerRepositoryImpl.class);

    private final Connection connection;
    private final DataBaseContextService dataBaseContextService;

    public PlayerRepositoryImpl(Connection connection, DataBaseContextService dataBaseContextService) {
        this.connection = connection;
        this.dataBaseContextService = dataBaseContextService;
    }

    @Override
    public void insertCurrentState() throws SQLException {
        String playerName = dataBaseContextService.getPlayerName();
        String heroData = dataBaseContextService.getHeroData();
        String map = dataBaseContextService.getWorldMap();
        String worldMap = heroData + "\r\n" + map;
        int numberOfMoves = dataBaseContextService.getNumberOfMoves();
        String queryInsertCurrentState = String.format("INSERT INTO PLAYER (PLAYER_NAME, WORLD_MAP, NUMBER_OF_MOVES) VALUES ('%s','%s',%d);", playerName, worldMap, numberOfMoves);
        Statement statement = connection.createStatement();
        statement.executeUpdate(queryInsertCurrentState);
        LOGGER.info("Saved to database");
    }

    @Override
    public ArrayList<String> loadGame(String username) throws SQLException {
        String queryGetWorldMapThatMatchesUsername = String.format("SELECT world_map FROM PLAYER WHERE player_name='%s';", username);
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(queryGetWorldMapThatMatchesUsername);
        if (resultSet != null) {
            while (resultSet.next()) {
                String worldMapData = resultSet.getString(1);
                return splitString(worldMapData);
            }
        } else {
            LOGGER.warn("Unable to load game from database");
        }
        return null;
    }

    private ArrayList<String> splitString(String stringArrayList) {
        ArrayList<String> list = new ArrayList<>();
        list.addAll(Arrays.asList(stringArrayList.split("\r\n")));
        return list;
    }
}
