package wumpus.service.mapreader;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import wumpus.Constants;
import wumpus.exceptions.*;
import wumpus.objects.Hero;
import wumpus.service.MapReaderImpl;
import wumpus.validator.HeroValidator;
import wumpus.validator.MapValidatorImpl;
import wumpus.world.World;

import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class MapReaderTest {
    MapValidatorImpl mapValidator;
    HeroValidator heroValidator;
    @Mock
    BufferedReader bufferedReaderMock;
    //system under test SUT
    MapReaderImpl mapReaderImpl;

    @BeforeEach
    public void setup() throws IOException, InvalidObjectAmountException, InvalidSizeException, InvalidPositionException {
        mapReaderImpl = new MapReaderImpl(mapValidator, bufferedReaderMock, heroValidator);
    }

    @Test()
    public void readMap_FirstRowInvalid() throws IOException, InvalidSizeException, InvalidObjectAmountException, HeroException, InvalidPositionException {
        // given
        given(bufferedReaderMock.readLine()).willReturn("6B5E");

        // when
        InvalidInputException ex = assertThrows(InvalidInputException.class, () -> mapReaderImpl.readMap());

        // then
        assertEquals(ex.getMessage(), "Could not read first line");
    }

    @Test()
    public void readMap_InvalidSecondRow() throws InvalidSizeException, IOException, InvalidObjectAmountException {
        // given
        String exMsg = "Column size is invalid!";
        given(bufferedReaderMock.readLine()).willReturn("6 B 5 E", "WWWWW");
        doThrow(new InvalidSizeException(exMsg)).when(mapValidator).validateColumnSize(any(Integer.class), any(Integer.class));

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
    public void readMap_HappyCase() throws IOException, InvalidSizeException, InvalidInputException, InvalidObjectAmountException, HeroException, InvalidPositionException {
        // given
        given(bufferedReaderMock.readLine()).willReturn("6 B 5 E", "WWWWWW", "W___PW", "WUGP_W", "W____W", "W__P_W", "WWWWWW", null);
        given(mapValidator.validateAmountOfWumpuses(any(World.class))).willReturn(1);
        doNothing().when(mapValidator).validateColumnSize(anyInt(), anyInt());
        doNothing().when(mapValidator).validateGold(any(World.class));
        doNothing().when(mapValidator).validateRowSize(any(Integer.class), any(Integer.class));
        doNothing().when(mapValidator).validateSizeOfMap(any(Integer.class), any(World.class));
        doNothing().when(mapValidator).validateWallsOnEdgesOfMap(any(World.class));

        // when
        World world = mapReaderImpl.readMap();

        // then
        assertEquals(world.getN(), 6);
        assertEquals(world.getWalls().size(), 20);
        assertEquals(world.getPits().size(), 3);
        assertEquals(world.getEmptyFields().size(), 11);
    }

    @Test
    public void readMap_HeroInInputFile() throws InvalidInputException, InvalidSizeException, InvalidObjectAmountException, HeroException, IOException, InvalidPositionException {
        // given
        given(bufferedReaderMock.readLine()).willReturn("6 B 5 E", "WWWWWW", "W___PW", "WUGP_W", "W___W", "WH_P_W", "WWWWWW", null);
        doNothing().when(mapValidator).validateHero(any(World.class));
        doNothing().when(mapValidator).isThereAnythingOnThisPosition(any(Point.class), any(World.class));
        doNothing().when(heroValidator).validateHeroPosition(any(Hero.class), any(World.class), anyInt(), anyInt());
        doNothing().when(heroValidator).validateHeroStartingPosition(any(Point.class), any(Point.class));
        given(mapValidator.validateAmountOfWumpuses(any(World.class))).willReturn(1);
        doNothing().when(mapValidator).validateColumnSize(anyInt(), anyInt());
        doNothing().when(mapValidator).validateGold(any(World.class));
        doNothing().when(mapValidator).validateRowSize(any(Integer.class), any(Integer.class));
        doNothing().when(mapValidator).validateSizeOfMap(any(Integer.class), any(World.class));
        doNothing().when(mapValidator).validateWallsOnEdgesOfMap(any(World.class));

        // when
        // SUT
        World world = mapReaderImpl.readMap();

        // then
        assertEquals(world.getN(), 6);
        assertEquals(world.getWalls().size(), 20);
        assertEquals(world.getPits().size(), 3);
        assertEquals(world.getEmptyFields().size(), 10);
        assertEquals(world.getHero().getType(), Constants.HERO);
        assertEquals(world.getGold().getType(), Constants.GOLD);
    }
}
