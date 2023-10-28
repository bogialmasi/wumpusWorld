package wumpus.validator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import wumpus.constants.Constants;
import wumpus.exceptions.InvalidInputException;
import wumpus.exceptions.InvalidObjectAmountException;
import wumpus.exceptions.InvalidPositionException;
import wumpus.exceptions.InvalidSizeException;
import wumpus.model.objects.GameObject;
import wumpus.model.objects.Pit;
import wumpus.model.objects.Wall;
import wumpus.model.objects.Wumpus;
import wumpus.service.validator.impl.MapValidatorImpl;
import wumpus.model.objects.World;

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
        addWumpusesToWorld(wumpusPoints, world);

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
        addWumpusesToWorld(wumpusPoints, world);

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
        addWumpusesToWorld(wumpusPoints, world);

        //when
        InvalidObjectAmountException exception = assertThrows(InvalidObjectAmountException.class, () -> mapValidatorImpl.validateAmountOfWumpuses(world));

        //then
        assertEquals("Only three wumpuses can exist on a 16 size map.", exception.getMessage());
    }

    void addWumpusesToWorld(ArrayList<Point> wumpusesPoints, World world) {
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
        addWumpusesToWorld(wumpusPoints, world);
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
        addWumpusesToWorld(wumpusPoints, world);
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
        addWumpusesToWorld(wumpusPoints, world);
        //then
        assertEquals(3, wumpusPoints.size());
    }

    public World addWallsToWorld_OnlyWalls(){
        World world = new World(6);
        for (int i = 0; i < world.getN(); i++) {
            for (int j = 0; j < world.getN(); j++) {
                world.gameObjects.add(new Wall(new Point(i,j))); // all walls everywhere
            }
        }
        return world;
    }

    @Test
    void validateWallsOnEdgesOfMap_AllValid() throws InvalidPositionException {
        // given
        world = addWallsToWorld_OnlyWalls();
        // when
        mapValidatorImpl.validateWallsOnEdgesOfMap(world);
    }

    public World addWallsToWorld_OnlyFirstColumn(){
        World world = new World(6);
        for (int i = 0; i < world.getN(); i++) {
            for (int j = 0; j < world.getN(); j++) {
                if(j==0){
                    world.gameObjects.add(new Wall(new Point(i,j)));
                } else {
                    world.gameObjects.add(new GameObject(new Point(i,j), Constants.EMPTY));
                }
            }
        }
        return world;
    }
    @Test
    void validateWallsOnEdgesOfMap_InvalidMap_FirstCol() throws InvalidPositionException {
        // given
        world = addWallsToWorld_OnlyFirstColumn();
        // when
        InvalidPositionException exception = assertThrows(InvalidPositionException.class, () -> mapValidatorImpl.validateWallsOnEdgesOfMap(world));
        // then
        assertEquals("The edges of the world must be walls", exception.getMessage());
    }

    public World addWallsToWorld_OnlyLastCol(){
        World world = new World(6);
        for (int i = 0; i < world.getN(); i++) {
            for (int j = 0; j < world.getN(); j++) {
                if(j==world.getN()){
                    world.gameObjects.add(new Wall(new Point(i,j)));
                } else {
                    world.gameObjects.add(new GameObject(new Point(i,j), Constants.EMPTY));
                }
            }
        }
        return world;
    }
    @Test
    void validateWallsOnEdgesOfMap_InvalidMap_LastCol() throws InvalidPositionException {
        // given
        world = addWallsToWorld_OnlyLastCol();
        // when
        InvalidPositionException exception = assertThrows(InvalidPositionException.class, () -> mapValidatorImpl.validateWallsOnEdgesOfMap(world));
        // then
        assertEquals("The edges of the world must be walls", exception.getMessage());
    }

    public World addWallsToWorld_OnlyFirstRow(){
        World world = new World(6);
        for (int i = 0; i < world.getN(); i++) {
            for (int j = 0; j < world.getN(); j++) {
                if(i==0){
                    world.gameObjects.add(new Wall(new Point(i,j)));
                } else {
                    world.gameObjects.add(new GameObject(new Point(i,j), Constants.EMPTY));
                }
            }
        }
        return world;
    }

    @Test
    void validateWallsOnEdgesOfMap_InvalidMap_FirstRow() throws InvalidPositionException {
        // given
        world = addWallsToWorld_OnlyFirstRow();
        // when
        InvalidPositionException exception = assertThrows(InvalidPositionException.class, () -> mapValidatorImpl.validateWallsOnEdgesOfMap(world));
        // then
        assertEquals("The edges of the world must be walls", exception.getMessage());
    }

    public World addWallsToWorld_OnlyLastRow(){
        World world = new World(6);
        for (int i = 0; i < world.getN(); i++) {
            for (int j = 0; j < world.getN(); j++) {
                if(i==world.getN()){
                    world.gameObjects.add(new Wall(new Point(i,j)));
                } else {
                    world.gameObjects.add(new GameObject(new Point(i,j), Constants.EMPTY));
                }
            }
        }
        return world;
    }
    @Test
    void validateWallsOnEdgesOfMap_InvalidMap_LastRow() throws InvalidPositionException {
        // given
        world = addWallsToWorld_OnlyLastRow();
        // when
        InvalidPositionException exception = assertThrows(InvalidPositionException.class, () -> mapValidatorImpl.validateWallsOnEdgesOfMap(world));
        // then
        assertEquals("The edges of the world must be walls", exception.getMessage());
    }

    @Test
    void validateSizeOfMap_ValidSize(){
        //given
        int mapSize = 10;
        world = new World(mapSize);
        // then
        assertEquals(mapSize, world.getN());
    }

    @Test
    void validateSizeOfMap_InvalidSize_NonEqualValues(){
        //given
        int mapSize = 15;
        world = new World(2);
        // when
        InvalidSizeException exception = assertThrows(InvalidSizeException.class, () -> mapValidatorImpl.validateSizeOfMap(mapSize, world));
        // then
        assertEquals("The size of the map is invalid!", exception.getMessage());
    }

    @Test
    void validateSizeOfMap_InvalidSize_TooBig(){
        //given
        int mapSize = 200;
        world = new World(200);
        // when
        InvalidSizeException exception = assertThrows(InvalidSizeException.class, () -> mapValidatorImpl.validateSizeOfMap(mapSize, world));
        // then
        assertEquals("The size of the map is invalid!", exception.getMessage());
    }

    @Test
    void isThisPositionEmpty_Empty() throws InvalidInputException {
        Point pos = new Point(5,5);
        world = new World(10);
        world.gameObjects.add(new GameObject(new Point(pos), Constants.EMPTY));

        mapValidatorImpl.isThisPositionEmpty(pos, world);
    }
    @Test
    void isThisPositionEmpty_NotEmpty() throws InvalidInputException {
        Point pos = new Point(5,5);
        world = new World(10);
        world.gameObjects.add(new GameObject(new Point(pos), Constants.PIT));
        //when
        InvalidInputException exception = assertThrows(InvalidInputException.class, ()-> mapValidatorImpl.isThisPositionEmpty(pos,world));
        //then
        assertEquals("Hero cannot be placed on filled spot.", exception.getMessage());
    }
}