package wumpus.service.database;

import wumpus.model.objects.World;
import wumpus.service.game.impl.GamePlayerImpl;
import wumpus.service.map.impl.MapReaderImpl;
import wumpus.service.menu.impl.MainMenuImpl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public interface PlayerRepository {

    ArrayList<String> loadGame(String username) throws SQLException;
    void insertCurrentState() throws SQLException;
}
