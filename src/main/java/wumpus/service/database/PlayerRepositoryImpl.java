package wumpus.service.database;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

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
        String query_InsertCurrentState = String.format("INSERT INTO PLAYER (PLAYER_NAME, WORLD_MAP, NUMBER_OF_MOVES) VALUES ('%S','%s',%d);", playerName, worldMap, numberOfMoves);
        Statement statement = connection.createStatement();
        statement.executeUpdate(query_InsertCurrentState);
        LOGGER.info("Saved to database");
    }

    @Override
    public ArrayList<String> loadGame(String username) throws SQLException {
        String query_GetWorldMapThatMatchesUsername = String.format("SELECT world_map FROM PLAYER WHERE player_name='%s';", username.toUpperCase());
        Statement statement = connection.createStatement();
        ArrayList<String> lines = new ArrayList<>();
        ResultSet resultSet = statement.executeQuery(query_GetWorldMapThatMatchesUsername);
        if (resultSet != null) {
            while (resultSet.next()) {
                lines.add(resultSet.toString());
                /*
                * TODO result not correct, only one line is returned - correction needed
                *  */
            }
        } else {
            LOGGER.warn("Unable to load game from database");
        }
        return lines;
    }
}
