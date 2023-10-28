package wumpus.service.game.commands;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import wumpus.model.objects.Direction;
import wumpus.service.game.commands.impl.CommandsImpl;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class CommandTest {

    Commands commands;
    @BeforeEach
    public void setUp(){
        commands = new CommandsImpl();
    }

    @Test
    public void Commands_turnLeftTest_North(){
        Direction dir = commands.turnLeft(Direction.N);
        assertEquals(dir, Direction.W);
    }

    @Test
    public void Commands_turnLeftTest_West(){
        Direction dir = commands.turnLeft(Direction.W);
        assertEquals(dir, Direction.S);
    }
    @Test
    public void Commands_turnLeftTest_South(){
        Direction dir = commands.turnLeft(Direction.S);
        assertEquals(dir, Direction.E);
    }
    @Test
    public void Commands_turnLeftTest_East(){
        Direction dir = commands.turnLeft(Direction.E);
        assertEquals(dir, Direction.N);
    }

    @Test
    public void Commands_turnRightTest_North(){
        Direction dir = commands.turnRight(Direction.N);
        assertEquals(dir, Direction.E);
    }
    @Test
    public void Commands_turnRightTest_East(){
        Direction dir = commands.turnRight(Direction.E);
        assertEquals(dir, Direction.S);
    }

    @Test
    public void Commands_turnRightTest_West(){
        Direction dir = commands.turnRight(Direction.W);
        assertEquals(dir, Direction.N);
    }

    @Test
    public void Commands_turnRightTest_South(){
        Direction dir = commands.turnRight(Direction.S);
        assertEquals(dir, Direction.W);
    }
}
