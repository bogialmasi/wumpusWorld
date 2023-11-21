package wumpus.service.game;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import wumpus.model.objects.Direction;
import wumpus.model.objects.Hero;
import wumpus.model.objects.World;
import wumpus.service.database.DataBaseContextService;
import wumpus.service.game.commands.Commands;
import wumpus.service.game.commands.impl.CommandsImpl;
import wumpus.service.game.impl.GamePlayerImpl;
import wumpus.service.menu.MainMenu;

import java.awt.*;
import java.util.Scanner;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class GamePlayerTest {
    Commands commands;
    GamePlayer gamePlayer;
    World world;
    @Mock
    MainMenu mainMenu;
    @Mock
    DataBaseContextService dataBaseContextService;
    Hero hero;
    @Mock
    Scanner sc;
    @BeforeEach
    void setUp(){
        commands = new CommandsImpl(dataBaseContextService);
        world = new World(6);
        gamePlayer = new GamePlayerImpl(world, mainMenu, commands, sc, dataBaseContextService);
        hero = new Hero(new Point(1, 1), Direction.N);
        world.gameObjects.add(hero);
    }
}
