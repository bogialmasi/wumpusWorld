package wumpus.service.database;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willReturn;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class PlayerRepositoryTest {

    /**
     *         String query_InsertCurrentState = String.format("INSERT INTO PLAYER (PLAYER_NAME, WORLD_MAP, NUMBER_OF_MOVES) VALUES ('%s','%s',%d);", playerName, worldMap, numberOfMoves);
     *         Statement statement = connection.createStatement();
     *         statement.executeUpdate(query_InsertCurrentState);
     */
    @Mock
    Connection connection;
    @Mock
    Statement statement;
    @Mock
    DataBaseContextService dataBaseContextService;
    PlayerRepository playerRepository;
    @Mock
    ResultSet resultSet;
    @BeforeEach
    void setUp() {
        playerRepository = new PlayerRepositoryImpl(connection, dataBaseContextService);
    }

    @Test
    public void playerRepository_insertCurrentState() throws SQLException {

    }

    @Test
    public void playerRepository_loadGame() throws SQLException {
        // given
        String testMap = "6 C 4 E\r\nWWWWWW\r\nW___PW\r\nWUGP_W\r\nW_H__W\r\nW__P_W\r\nWWWWWW";
        ArrayList<String> testMapList = new ArrayList<>();
        testMapList.addAll(Arrays.asList(testMap.split("\r\n")));
        given(resultSet.getString(any(Integer.class))).willReturn(testMap);
        given(connection.createStatement()).willReturn(statement);
        given(resultSet.next()).willReturn(true);
        given(statement.executeQuery(any(String.class))).willReturn(resultSet);
        // when
        ArrayList<String> returnedMap = playerRepository.loadGame("BOGI");
        // then
        verify(resultSet).next();
        verify(resultSet).getString(any(Integer.class));
        verify(connection).createStatement();
        verify(statement).executeQuery(any(String.class));
        assertEquals(testMapList, returnedMap);
    }
}
