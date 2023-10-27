package wumpus.validator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import wumpus.exceptions.HeroException;
import wumpus.exceptions.InvalidObjectAmountException;
import wumpus.exceptions.InvalidPositionException;
import wumpus.exceptions.InvalidSizeException;
import wumpus.service.MapReaderImpl;
import wumpus.world.World;

import java.io.BufferedReader;
import java.io.IOException;

@ExtendWith(MockitoExtension.class)

public class MapContentValidatorTest {

    MapContentValidatorImpl mapContentValidatorImpl;

    @BeforeEach
    void setUp(){
        mapContentValidatorImpl = new MapContentValidatorImpl();
    }

    @Test
    void validateAmountOfWumpuses_one(){

    }

}
