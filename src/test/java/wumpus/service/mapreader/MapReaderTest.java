package wumpus.service.mapreader;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import wumpus.Constants;
import wumpus.exceptions.*;
import wumpus.objects.Direction;
import wumpus.objects.GameObject;
import wumpus.objects.Hero;
import wumpus.service.MapReaderImpl;
import wumpus.validator.HeroValidator;
import wumpus.validator.HeroValidatorImpl;
import wumpus.validator.MapContentValidatorImpl;
import wumpus.validator.MapValidatorImpl;
import wumpus.world.World;

import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
public class MapReaderTest {
    MapValidatorImpl mapValidator;
    HeroValidator heroValidator;
    @Mock
    BufferedReader bufferedReaderMock;
    //system under test SUT
    MapReaderImpl mapReaderImpl;

    @BeforeEach
    public void setup() {
        mapValidator = new MapValidatorImpl();
        heroValidator = new HeroValidatorImpl();
        mapReaderImpl = new MapReaderImpl(mapValidator, bufferedReaderMock, heroValidator);
    }

    @Test()
    public void readMap_FirstRowInvalid() throws IOException {
        // given
        given(bufferedReaderMock.readLine()).willReturn("6B5E");

        // when
        InvalidInputException ex = assertThrows(InvalidInputException.class, () -> mapReaderImpl.readMap());

        // then
        assertEquals(ex.getMessage(), "Could not read first line");
    }

    @Test()
    public void readMap_InvalidSecondRow() throws IOException {
        // given
        String exMsg = "Column size is invalid!";
        given(bufferedReaderMock.readLine()).willReturn("6 B 5 E", "WWWWW");

        // when
        InvalidSizeException ex = assertThrows(InvalidSizeException.class, () -> mapReaderImpl.readMap());

        // then
        assertEquals(ex.getMessage(), exMsg);
    }

    @Test()
    public void readMap_InvalidHeroDirection() throws IOException {
        // given
        given(bufferedReaderMock.readLine()).willReturn("6 B 5 K");

        // when
        InvalidPositionException ex = assertThrows(InvalidPositionException.class, () -> mapReaderImpl.readMap());

        // then
        assertEquals(ex.getMessage(), "Hero's direction is invalid");
    }

    @Test
    public void readMap_HappyCase_NoHeroOnMap() throws IOException, InvalidSizeException, InvalidInputException, InvalidObjectAmountException, HeroException, InvalidPositionException {
        // given
        given(bufferedReaderMock.readLine()).willReturn("6 B 5 E", "WWWWWW", "W___PW", "WUGP_W", "W____W", "W__P_W", "WWWWWW", null);

        // when
        World world = mapReaderImpl.readMap();

        // then
        assertEquals(world.getN(), 6);
        assertEquals(world.getWalls().size(), 20);
        assertEquals(world.getPits().size(), 3);
        assertEquals(world.getEmptyFields().size(), 11);
    }

    @Test
    public void readMap_HeroInInputFile_WrongPosition() throws IOException {
        // given
        given(bufferedReaderMock.readLine()).willReturn("6 B 5 E", "WWWWWW", "W___PW", "WUGP_W", "W____W", "W_HP_W", "WWWWWW", null);

        // when
        // SUT
        InvalidPositionException exception = assertThrows(InvalidPositionException.class, () -> mapReaderImpl.readMap());

        // then
        assertEquals("Hero is not on the given starting position on the map.", exception.getMessage());
    }

    @Test
    public void readMap_HappyCase_HeroOnMap() throws IOException, InvalidInputException, InvalidSizeException, InvalidObjectAmountException, HeroException, InvalidPositionException {
        // given
        given(bufferedReaderMock.readLine()).willReturn("6 B 5 E", "WWWWWW", "W___PW", "WUGP_W", "W____W", "WH_P_W", "WWWWWW", null);

        // when
        // SUT
        World world = mapReaderImpl.readMap();

        // then
        Point heroPosition = world.getHero().getPos();
        Point goldPosition = world.getGold().getPos();

        assertEquals(world.getN(), 6);
        assertEquals(world.getWalls().size(), 20);
        assertEquals(world.getPits().size(), 3);
        assertEquals(world.getEmptyFields().size(), 10);
        assertEquals(world.getHero().getType(), Constants.HERO);
        assertEquals(world.getGold().getType(), Constants.GOLD);
        AssertMap(world.map);
        AssertHero(world.getHero());
        AssertGold(world.getGold());
    }

    void AssertMap(String[][] map) {
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map.length; j++) {
                assertNotEquals(null, map[i][j]);
            }
        }
    }

    void AssertHero(GameObject object) {
        Hero hero = (Hero) object;
        assertNotNull(hero);
        assertEquals(1, hero.getArrows());
        assertEquals(new Point(4,1), hero.getPos());
        assertEquals(Direction.E, hero.getDir());
        assertFalse(hero.hasGold());
    }

    void AssertGold(GameObject object) {
        assertEquals(new Point(2,2), object.getPos());
    }
}
