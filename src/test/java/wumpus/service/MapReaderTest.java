package wumpus.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import wumpus.constants.Constants;
import wumpus.exceptions.*;
import wumpus.model.objects.Direction;
import wumpus.model.objects.GameObject;
import wumpus.model.objects.Hero;
import wumpus.model.objects.World;
import wumpus.service.map.impl.MapReaderImpl;
import wumpus.service.validator.HeroValidator;
import wumpus.service.validator.impl.HeroValidatorImpl;
import wumpus.service.validator.impl.MapValidatorImpl;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class MapReaderTest {
    MapValidatorImpl mapValidator;
    HeroValidator heroValidator;
    MapReaderImpl mapReaderImpl;
    ArrayList<String> lines;

    @BeforeEach
    public void setup() {
        lines = new ArrayList<>();
        mapValidator = new MapValidatorImpl();
        heroValidator = new HeroValidatorImpl();
        mapReaderImpl = new MapReaderImpl(mapValidator, heroValidator);
    }

    @Test()
    public void readMap_FirstRowInvalid() {
        // given
        lines.add("6B5E");

        // when
        InvalidInputException ex = assertThrows(InvalidInputException.class, () -> mapReaderImpl.readMap(lines));

        // then
        assertEquals(ex.getMessage(), "Could not read first line");
    }

    @Test()
    public void readMap_InvalidSecondRow() {
        // given
        String exMsg = "Column size is invalid!";
        lines.add("6 B 5 E");
        lines.add("WWWWW");

        // when
        InvalidSizeException ex = assertThrows(InvalidSizeException.class, () -> mapReaderImpl.readMap(lines));

        // then
        assertEquals(ex.getMessage(), exMsg);
    }

    @Test()
    public void readMap_InvalidHeroDirection() {
        // given
        lines.add("6 B 5 K");

        // when
        InvalidPositionException ex = assertThrows(InvalidPositionException.class, () -> mapReaderImpl.readMap(lines));

        // then
        assertEquals(ex.getMessage(), "Hero's direction is invalid");
    }

    @Test
    public void readMap_HappyCase_NoHeroOnMap() throws InvalidSizeException, InvalidInputException, InvalidObjectAmountException, HeroException, InvalidPositionException {
        // given
        lines.addAll(Arrays.asList("6 B 5 E","WWWWWW", "W___PW", "WUGP_W", "W____W", "W__P_W", "WWWWWW"));

        // when
        World world = mapReaderImpl.readMap(lines);

        // then
        assertEquals(world.getN(), 6);
        assertEquals(world.getWalls().size(), 20);
        assertEquals(world.getPits().size(), 3);
        assertEquals(world.getEmptyFields().size(), 10);
        assertEquals(world.getGold().getType(), Constants.GOLD);
        AssertMap(world.map);
        AssertGold(world.getGold());
    }

    @Test
    public void readMap_HeroInInputFile_WrongPosition() throws IOException {
        // given
        lines.addAll(Arrays.asList("6 B 5 E", "WWWWWW", "W___PW", "WUGP_W", "W____W", "W_HP_W", "WWWWWW"));

        // when
        InvalidPositionException exception = assertThrows(InvalidPositionException.class, () -> mapReaderImpl.readMap(lines));

        // then
        assertEquals("Hero is not on the given starting position on the map.", exception.getMessage());
    }

    @Test
    public void readMap_HappyCase_HeroOnMap() throws IOException, InvalidInputException, InvalidSizeException, InvalidObjectAmountException, HeroException, InvalidPositionException {
        // given
        lines.addAll(Arrays.asList("6 B 5 E", "WWWWWW", "W___PW", "WUGP_W", "W____W", "WH_P_W", "WWWWWW"));

        // when
        World world = mapReaderImpl.readMap(lines);

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
