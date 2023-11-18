package wumpus;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import wumpus.service.database.PlayerRepository;
import wumpus.service.database.PlayerRepositoryImpl;
import wumpus.service.game.commands.impl.CommandsImpl;
import wumpus.service.menu.impl.MainMenuImpl;
import wumpus.service.validator.HeroValidator;
import wumpus.service.validator.MapValidator;
import wumpus.service.validator.impl.HeroValidatorImpl;
import wumpus.service.validator.impl.MapValidatorImpl;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Main {
    private static final String MAP_FILE_PATH = "src/main/resources/wumpuszinput.txt";
    private static final Logger LOGGER = LoggerFactory.getLogger(Main.class);
    public static void main(String[] args) throws FileNotFoundException, SQLException {
        BufferedReader reader = new BufferedReader(new FileReader(MAP_FILE_PATH));
        MapValidator mapValidator = new MapValidatorImpl();
        HeroValidator heroValidator = new HeroValidatorImpl();

        try (Connection connection = DriverManager.getConnection("jdbc:h2:tcp://localhost/~/test", "sa", "")) {
            PlayerRepository playerRepository = new PlayerRepositoryImpl();
            MainMenuImpl mainMenuImpl = new MainMenuImpl(reader, mapValidator, heroValidator, playerRepository);
            mainMenuImpl.startMenu();
        } catch (SQLException ex) {
            LOGGER.warn("Unexpected SQL exception: {}", ex.getMessage());
        }
    }
}
