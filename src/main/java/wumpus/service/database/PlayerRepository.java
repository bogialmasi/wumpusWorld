package wumpus.service.database;

import java.sql.SQLException;
import java.util.ArrayList;

public interface PlayerRepository {

    ArrayList<String> loadGame(String username) throws SQLException;

    void insertCurrentState() throws SQLException;
}
