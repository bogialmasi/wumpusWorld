package wumpus.validator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.Mockito.*;
import wumpus.exceptions.*;
import wumpus.model.objects.*;
import wumpus.service.validator.impl.HeroValidatorImpl;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
public class HeroValidatorTest {

    HeroValidatorImpl heroValidatorImpl;
    World world;
    Hero hero;

    @BeforeEach
    void setUp(){
        heroValidatorImpl = new HeroValidatorImpl();
    }

    @Test
    void validateHeroPositionIsInsideMap_InvalidRow_Row0(){
        //given
        world = new World(7);
        hero = new Hero(new Point(0,2));

        //when
        HeroException exception = assertThrows(HeroException.class, () -> heroValidatorImpl.validateHeroPositionIsInsideMap(world, hero.getPos().x, hero.getPos().y));

        // then
        assertEquals("The Hero is standing in an invalid row", exception.getMessage());
    }

    @Test
    void validateHeroPositionIsInsideMap_InvalidRow_LastRow(){
        //given
        world = new World(7);
        hero = new Hero(new Point(6,2));

        //when
        HeroException exception = assertThrows(HeroException.class, () -> heroValidatorImpl.validateHeroPositionIsInsideMap(world, hero.getPos().x, hero.getPos().y));

        // then
        assertEquals("The Hero is standing in an invalid row", exception.getMessage());
    }

    @Test
    void validateHeroPositionIsInsideMap_InvalidCol_Col0(){
        //given
        world = new World(7);
        hero = new Hero(new Point(2,0));

        //when
        HeroException exception = assertThrows(HeroException.class, () -> heroValidatorImpl.validateHeroPositionIsInsideMap(world, hero.getPos().x, hero.getPos().y));

        // then
        assertEquals("The Hero is standing in an invalid column", exception.getMessage());
    }

    @Test
    void validateHeroPositionIsInsideMap_InvalidCol_LastCol(){
        //given
        world = new World(7);
        hero = new Hero(new Point(2,6));

        //when
        HeroException exception = assertThrows(HeroException.class, () -> heroValidatorImpl.validateHeroPositionIsInsideMap(world, hero.getPos().x, hero.getPos().y));

        // then
        assertEquals("The Hero is standing in an invalid column", exception.getMessage());
    }

    @Test
    void validateHeroPositionIsInsideMap_Valid() throws HeroException {
        //given
        world = new World(7);
        hero = new Hero(new Point(2,4));

        heroValidatorImpl.validateHeroPositionIsInsideMap(world, hero.getPos().x, hero.getPos().y);
    }

    @Test
    void validateHeroStartingPosition_Invalid() {
        Point startingPoint = new Point(3, 5);
        hero = new Hero(new Point(5, 3));
        //when
        InvalidPositionException exception = assertThrows(InvalidPositionException.class, () -> heroValidatorImpl.validateHeroStartingPosition(startingPoint, hero.getPos()));

        // then
        assertEquals("Hero is not on the given starting position on the map.", exception.getMessage());

    }
    @Test
    void validateHeroStartingPosition_Valid() throws InvalidPositionException {
        Point startingPoint = new Point(5,3);
        hero = new Hero(new Point(5, 3));

        heroValidatorImpl.validateHeroStartingPosition(startingPoint, hero.getPos());
    }


}
