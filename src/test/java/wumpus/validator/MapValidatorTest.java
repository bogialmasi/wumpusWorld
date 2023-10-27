package wumpus.validator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import wumpus.Constants;
import wumpus.exceptions.InvalidObjectAmountException;
import wumpus.exceptions.InvalidPositionException;
import wumpus.objects.GameObject;
import wumpus.objects.Wumpus;
import wumpus.world.World;

import java.awt.*;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)

public class MapValidatorTest {
    MapValidatorImpl mapValidatorImpl;
    World world;

    @BeforeEach
    void setUp() {
        mapValidatorImpl = new MapValidatorImpl();
    }

    @Test
    void validateAmountOfWumpuses_InvalidObjectAmountException_1Wumpus() throws InvalidObjectAmountException {
        world = new World(8);
        //given
        ArrayList<Point> wumpusPoints = new ArrayList<Point>() {{
            add(new Point(0, 0));
            add(new Point(0, 0));
        }};
        addWumpusesToWorld(wumpusPoints);

        //when
        InvalidObjectAmountException exception = assertThrows(InvalidObjectAmountException.class, () -> mapValidatorImpl.validateAmountOfWumpuses(world));

        //then
        assertEquals("Only one wumpus can exist on a 8 size map.", exception.getMessage());
    }

    @Test
    void validateAmountOfWumpuses_InvalidObjectAmountException_0Wumpus() throws InvalidObjectAmountException {
        world = new World(8);
        //given
        ArrayList<Point> wumpusPoints = new ArrayList<Point>() {{
        }};

        //when
        InvalidObjectAmountException exception = assertThrows(InvalidObjectAmountException.class, () -> mapValidatorImpl.validateAmountOfWumpuses(world));

        //then
        assertEquals("Only one wumpus can exist on a 8 size map.", exception.getMessage());
    }

    @Test
    void validateAmountOfWumpuses_InvalidObjectAmountException_2Wumpuses() throws InvalidObjectAmountException {
        //given
        world = new World(10);
        ArrayList<Point> wumpusPoints = new ArrayList<Point>() {{
            add(new Point(0, 0));
            add(new Point(0, 0));
            add(new Point(0, 0));
            add(new Point(0, 0));
        }};
        addWumpusesToWorld(wumpusPoints);

        //when
        InvalidObjectAmountException exception = assertThrows(InvalidObjectAmountException.class, () -> mapValidatorImpl.validateAmountOfWumpuses(world));

        //then
        assertEquals("Only two wumpuses can exist on a 10 size map.", exception.getMessage());
    }

    @Test
    void validateAmountOfWumpuses_InvalidObjectAmountException_3Wumpuses() throws InvalidObjectAmountException {
        //given
        world = new World(16);
        ArrayList<Point> wumpusPoints = new ArrayList<Point>() {{
            add(new Point(0, 0));
            add(new Point(0, 0));
            add(new Point(0, 0));
            add(new Point(0, 0));
        }};
        addWumpusesToWorld(wumpusPoints);

        //when
        InvalidObjectAmountException exception = assertThrows(InvalidObjectAmountException.class, () -> mapValidatorImpl.validateAmountOfWumpuses(world));

        //then
        assertEquals("Only three wumpuses can exist on a 16 size map.", exception.getMessage());
    }

    void addWumpusesToWorld(ArrayList<Point> wumpusesPoints) {
        for (Point pos :
                wumpusesPoints) {
            world.gameObjects.add(new Wumpus(new Point(pos.x, pos.y)));
        }

    }

    @Test
    void validateAmountOfWumpuses_Correct_1Wumpus() {
        // given
        world = new World(8);
        ArrayList<Point> wumpusPoints = new ArrayList<Point>() {{
            add(new Point(0, 0));
        }};
        addWumpusesToWorld(wumpusPoints);
        //then
        assertEquals(1, wumpusPoints.size());
    }

    @Test
    void validateAmountOfWumpuses_Correct_2Wumpuses() {
        // given
        world = new World(11);
        ArrayList<Point> wumpusPoints = new ArrayList<Point>() {{
            add(new Point(0, 0));
            add(new Point(0, 0));
        }};
        addWumpusesToWorld(wumpusPoints);
        //then
        assertEquals(2, wumpusPoints.size());
    }

    @Test
    void validateAmountOfWumpuses_Correct_3Wumpuses() {
        // given
        world = new World(17);
        ArrayList<Point> wumpusPoints = new ArrayList<Point>() {{
            add(new Point(0, 0));
            add(new Point(0, 0));
            add(new Point(0, 0));
        }};
        addWumpusesToWorld(wumpusPoints);
        //then
        assertEquals(3, wumpusPoints.size());
    }

    public World wallsOfWorld(){
        World wallWorld = new World(6);
        for (int i = 0; i < wallWorld.getN(); i++) {
            for (int j = 0; j < wallWorld.getN(); j++) {
                wallWorld.getWalls().add(new GameObject(new Point(i,j), Constants.WALL)); // all walls everywhere
            }
        }
        return wallWorld;
    }
    @Test
    void validateWallsOnEdgesOfMap_AllValid() throws InvalidPositionException {
        // given
        world = wallsOfWorld();
    }
}