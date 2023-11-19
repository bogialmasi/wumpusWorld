package wumpus.service.database;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PlayerRepositoryImpl implements PlayerRepository {

    private static final Logger LOGGER = LoggerFactory.getLogger(PlayerRepositoryImpl.class);

    private Connection connection;
    private DataBaseContextService dataBaseContextService;

    public PlayerRepositoryImpl(Connection connection, DataBaseContextService dataBaseContextService) {
        this.connection = connection;
        this.dataBaseContextService = dataBaseContextService;
    }

    /*
                        @Override
                        public void createConnection() { // todo nemkell createConn mert már a Main nyitja és zárja is. ide már csak aktuális DB művelet metódusok kellenek
                            try(Connection connection = DriverManager.getConnection("jdbc:h2:tcp://localhost/~/test", "sa", "")){ //todo delete
                                String query_ListAllPlayers = "SELECT * FROM PLAYER";
                                String query_Insert = "INSERT INTO PLAYER VALUES (\"aaa\",\"aaaaaa\",1);";
                                Statement statement = connection.createStatement();
                                ResultSet resultSet = statement.executeQuery(query_ListAllPlayers);
                                while (resultSet.next()){
                                    System.out.println(resultSet.getString("player_name"));
                                }
                                statement.close();
                                resultSet.close();
                            }catch (SQLException e){
                                LOGGER.warn("Unexpected SQL exception: {}", e.getMessage());
                            }catch (Exception e){
                                LOGGER.warn("Unexpected exception: {}", e.getMessage());
                            }
                        }
                    */
    @Override
    public void insertCurrentState() throws SQLException {
        String playerName = dataBaseContextService.getPlayerName();
        String heroData = dataBaseContextService.getHeroData();
        String map = dataBaseContextService.getWorldMap();
        String worldMap = heroData + "\r\n" + map;
        int numberOfMoves = dataBaseContextService.getNumberOfMoves();
        String query_InsertCurrentState = String.format("INSERT INTO PLAYER (PLAYER_NAME, WORLD_MAP, NUMBER_OF_MOVES) VALUES ('%s','%s',%d);", playerName, worldMap, numberOfMoves);
        Statement statement = connection.createStatement();
        statement.executeUpdate(query_InsertCurrentState);
        LOGGER.info("Saved to database");
    }

    @Override
    public ArrayList<String> loadGame(String username) throws SQLException {
        String query_GetWorldMapThatMatchesUsername = String.format("SELECT world_map FROM PLAYER WHERE player_name='%s';", username);
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(query_GetWorldMapThatMatchesUsername);
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

    private ArrayList<String> splitString(String stringArrayList){
        ArrayList<String> list = new ArrayList<>();
        list.addAll(Arrays.asList(stringArrayList.split("\r\n")));
        return list;
    }
}
