package wumpus.service.database;

import wumpus.model.objects.World;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public interface PlayerRepository {

    ArrayList<String> loadGame(String username) throws SQLException;
    void insertCurrentState();
}
