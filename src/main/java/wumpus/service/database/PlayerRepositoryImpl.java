package wumpus.service.database;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;

public class PlayerRepositoryImpl implements PlayerRepository {

    private static final Logger LOGGER = LoggerFactory.getLogger(PlayerRepositoryImpl.class);
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

    }

    @Override
    public void loadCurrentState() {

    }
}
