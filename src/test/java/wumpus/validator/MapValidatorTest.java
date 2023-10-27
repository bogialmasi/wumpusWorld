package wumpus.validator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import wumpus.exceptions.InvalidSizeException;
import wumpus.service.MapReaderImpl;
import wumpus.validator.MapValidatorImpl;
import wumpus.world.World;

import java.io.BufferedReader;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
public class MapValidatorTest {
    @Mock
    World worldMock;
   /* @Mock
    MapValidatorImpl mapValidatorMock= new MapValidatorImpl();
    @BeforeEach
    public void setUp(){
        worldMock = new World();
    }

    @Test
    public void testValidateSizeOfMap() throws InvalidSizeException {
        //given
        //when
        //then
    }*/
}

