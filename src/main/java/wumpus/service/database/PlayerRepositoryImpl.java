package wumpus.service.database;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import wumpus.model.objects.World;
import wumpus.service.map.MapReader;
import wumpus.service.map.impl.MapReaderImpl;

import javax.swing.plaf.nimbus.State;
import java.sql.*;
import java.util.ArrayList;

public class PlayerRepositoryImpl implements PlayerRepository {

    private static final Logger LOGGER = LoggerFactory.getLogger(PlayerRepositoryImpl.class);

    private Connection connection;

    public PlayerRepositoryImpl(Connection connection) {
        this.connection = connection;
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
    public void insertCurrentState() {
        /*
        * INSERT INTO PLAYER VALUES (username, map written into a string, number of moves)
        * number of moves comes from GamePlayerImpl.getCommandCounter!!! */
    }

    @Override
    public ArrayList<String> loadGame(String username) throws SQLException {
        String query_GetWorldMapThatMatchesUsername = "SELECT world_map FROM PLAYER WHERE player_name=\"" + username + "\";";
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(query_GetWorldMapThatMatchesUsername);
        ArrayList<String> lines = new ArrayList<>();
        while (resultSet.next()){
            lines.add(resultSet.toString());
        }
        return lines;
    }

}
