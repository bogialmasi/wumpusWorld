package wumpus.service.database;

public interface PlayerRepository {

    // void createConnection();  //todo not needed
    void insertCurrentState();
    void loadCurrentState();
}
