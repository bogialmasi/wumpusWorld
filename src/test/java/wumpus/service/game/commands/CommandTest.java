package wumpus.service.game.commands;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import wumpus.constants.Constants;
import wumpus.exceptions.InvalidInputException;
import wumpus.model.objects.*;
import wumpus.service.database.DataBaseContextService;
import wumpus.service.game.commands.impl.CommandsImpl;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class CommandTest {

    Commands commands;
    String[][] testmap;
    World world;

    @BeforeEach
    public void setUp() {
        commands = new CommandsImpl(new DataBaseContextService());
        testmap = new String[][]{
                {"_", "_", "_", "_"},
                {"_", "H", "_", "U"},
                {"W", "_", "_", "P"},
                {"_", "G", "_", "_"}};

        world = new World(4);
        world.map = testmap;
        world.gameObjects.add(new GameObject(new Point(0, 0), Constants.EMPTY));
        world.gameObjects.add(new GameObject(new Point(0, 1), Constants.EMPTY));
        world.gameObjects.add(new GameObject(new Point(0, 2), Constants.EMPTY));
        world.gameObjects.add(new GameObject(new Point(0, 3), Constants.EMPTY));
        world.gameObjects.add(new GameObject(new Point(1, 0), Constants.EMPTY));
        world.gameObjects.add(new Hero(new Point(1, 1), Direction.E));
        world.gameObjects.add(new GameObject(new Point(1, 2), Constants.EMPTY));
        world.gameObjects.add(new Wumpus(new Point(1, 3)));
        world.gameObjects.add(new Wall(new Point(2, 0)));
        world.gameObjects.add(new GameObject(new Point(2, 1), Constants.EMPTY));
        world.gameObjects.add(new GameObject(new Point(2, 2), Constants.EMPTY));
        world.gameObjects.add(new GameObject(new Point(2, 3), Constants.PIT));
        world.gameObjects.add(new GameObject(new Point(3, 0), Constants.EMPTY));
        world.gameObjects.add(new Gold(new Point(3, 1)));
        world.gameObjects.add(new GameObject(new Point(3, 2), Constants.EMPTY));
        world.gameObjects.add(new GameObject(new Point(3, 3), Constants.EMPTY));

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
        assertEquals(world.map[0][1], Constants.HERO);
    }

    @Test
    public void commands_goDown_toEmpty() {
        commands.goDown(world);
        assertEquals(world.map[2][1], Constants.HERO);
    }

    @Test
    public void commands_goLeft_toEmpty() {
        commands.goLeft(world);
        assertEquals(world.map[1][0], Constants.HERO);
    }

    @Test
    public void commands_goRight_toEmpty() {
        commands.goRight(world);
        assertEquals(world.map[1][2], Constants.HERO);
    }
    @Test
    public void commands_shoot(){
        commands.shoot(world);
        assertEquals(world.map[1][3], Constants.EMPTY);
    }

    @Test
    public void commands_pickUpGold(){
        commands.goDown(world);
        commands.goDown(world);

        commands.pickUpGold(world);
        assertTrue(world.getHero().hasGold());
        assertEquals(world.map[3][1], Constants.HERO);
    }

    @Test
    public void commands_pickUpGold_noGoldPickUp(){
        commands.pickUpGold(world);
        assertFalse(world.getHero().hasGold());
    }

}
