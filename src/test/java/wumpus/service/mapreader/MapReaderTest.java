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
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class MapReaderTest {
    @Mock
    MapValidatorImpl mapValidatorMock;
    @Mock
    BufferedReader bufferedReaderMock;
    @Mock
    HeroValidator heroValidatorMock;
    MapReaderImpl mapReaderImpl;

    @BeforeEach
    public void setup() throws IOException, InvalidObjectAmountException, InvalidSizeException, InvalidPositionException {
        given(mapValidatorMock.validateAmountOfWumpuses(any(World.class))).willReturn(1);
        doNothing().when(mapValidatorMock).validateColumnSize(anyInt(), anyInt());
        doNothing().when(mapValidatorMock).validateGold(any(World.class));
        doNothing().when(mapValidatorMock).validateRowSize(any(Integer.class), any(Integer.class));
        doNothing().when(mapValidatorMock).validateSizeOfMap(any(Integer.class), any(World.class));
        doNothing().when(mapValidatorMock).validateWallsOnEdgesOfMap(any(World.class));
        mapReaderImpl = new MapReaderImpl(mapValidatorMock, bufferedReaderMock, heroValidatorMock);
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
        given(bufferedReaderMock.readLine()).willReturn("6 B 5 E", "WWWWW");
        // when
        InvalidSizeException ex = assertThrows(InvalidSizeException.class, () -> mapReaderImpl.readMap());
        // then
        assertEquals(ex.getMessage(), "Column size is invalid!");
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
        given(bufferedReaderMock.readLine()).willReturn("6 B 5 E", "WWWWWW", "W___PW", "WUGP_W", "W_H__W", "W__P_W", "WWWWWW", null);
        doNothing().when(mapValidatorMock).validateHero(any(World.class));
        doNothing().when(mapValidatorMock).isThereAnythingOnThisPosition(any(Point.class), any(World.class));
        doNothing().when(heroValidatorMock).validateHeroPosition(any(Hero.class), any(World.class), anyInt(), anyInt());
        doNothing().when(heroValidatorMock).validateHeroStartingPosition(any(Point.class), any(Point.class));

        // when
        World world = mapReaderImpl.readMap();

        // then
        assertEquals(world.getN(), 6);
        assertEquals(world.getWalls().size(), 20);
        assertEquals(world.getPits().size(), 3);
        assertEquals(world.getEmptyFields().size(), 10);
        assertEquals(world.getHero().getType(), Constants.HERO);
    }
}
