package wumpus.service.game.commands;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import wumpus.constants.Constants;
import wumpus.exceptions.InvalidInputException;
import wumpus.model.objects.Direction;
import wumpus.model.objects.GameObject;
import wumpus.model.objects.Hero;
import wumpus.model.objects.World;
import wumpus.service.game.commands.impl.CommandsImpl;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class CommandTest {

    Commands commands;
    String[][] testmap;
    World world;

    @BeforeEach
    public void setUp() {
        commands = new CommandsImpl();
        testmap = new String[][]{
                                {"_", "_", "_"},
                                {"_", "H", "_"},
                                {"_", "_", "_"}};

        world = new World(5);
        world.map = testmap;
        world.gameObjects.add(new GameObject(new Point(0,0), Constants.EMPTY));
        world.gameObjects.add(new GameObject(new Point(0,1), Constants.EMPTY));
        world.gameObjects.add(new GameObject(new Point(0,2), Constants.EMPTY));
        world.gameObjects.add(new GameObject(new Point(1,0), Constants.EMPTY));
        world.gameObjects.add(new Hero(new Point(1,1), Direction.N));
        world.gameObjects.add(new GameObject(new Point(1,2), Constants.EMPTY));
        world.gameObjects.add(new GameObject(new Point(2,0), Constants.EMPTY));
        world.gameObjects.add(new GameObject(new Point(2,1), Constants.EMPTY));
        world.gameObjects.add(new GameObject(new Point(1,1), Constants.EMPTY));
    }

    @Test
    public void commands_turnLeftTest_North() {
        Direction dir = commands.turnLeft(Direction.N);
        assertEquals(dir, Direction.W);
    }

    @Test
    public void commands_turnLeftTest_West() {
        Direction dir = commands.turnLeft(Direction.W);
        assertEquals(dir, Direction.S);
    }

    @Test
    public void commands_turnLeftTest_South() {
        Direction dir = commands.turnLeft(Direction.S);
        assertEquals(dir, Direction.E);
    }

    @Test
    public void commands_turnLeftTest_East() {
        Direction dir = commands.turnLeft(Direction.E);
        assertEquals(dir, Direction.N);
    }

    @Test
    public void commands_turnRightTest_North() {
        Direction dir = commands.turnRight(Direction.N);
        assertEquals(dir, Direction.E);
    }

    @Test
    public void commands_turnRightTest_East() {
        Direction dir = commands.turnRight(Direction.E);
        assertEquals(dir, Direction.S);
    }

    @Test
    public void commands_turnRightTest_West() {
        Direction dir = commands.turnRight(Direction.W);
        assertEquals(dir, Direction.N);
    }

    @Test
    public void commands_turnRightTest_South() {
        Direction dir = commands.turnRight(Direction.S);
        assertEquals(dir, Direction.W);
    }
    @Test
    public void commands_goUp_toEmpty() throws InvalidInputException {
        commands.goUp(world);
        assertEquals(testmap[0][1], Constants.HERO);
    }
    @Test
    public void commands_goDown_toEmpty() throws InvalidInputException {
        commands.goDown(world);
        assertEquals(testmap[1][2], Constants.HERO);
    }
}
