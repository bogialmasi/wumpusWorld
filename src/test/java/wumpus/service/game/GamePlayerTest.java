package wumpus.service.game;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import wumpus.model.objects.Hero;
import wumpus.model.objects.World;
import wumpus.service.game.commands.Commands;
import wumpus.service.menu.MainMenu;

import java.util.Scanner;

@ExtendWith(MockitoExtension.class)
public class GamePlayerTest {
    Commands commands;
    GamePlayer gamePlayer;
    World world;
    @Mock
    MainMenu mainMenu;
    Hero hero;

    @Mock
    Scanner sc;
    /*
    @BeforeEach
    void setUp(){
        commands = new CommandsImpl();
        world = new World(6);
        gamePlayer = new GamePlayerImpl(world, mainMenu, commands, sc);
        hero = new Hero(new Point(1, 1), Direction.N);
        world.gameObjects.add(hero);
    }

    @Test
    void gamePlayer_turnLeftTest(){
        // TODO ScannerMock?
        given(sc.next()).willReturn("l", "z");
        gamePlayer.startGame();
        assertEquals(((Hero) (world.getHero())), Direction.W);
    }*/
}
