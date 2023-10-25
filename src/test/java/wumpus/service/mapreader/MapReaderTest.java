package wumpus.service.mapreader;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import wumpus.exceptions.InvalidSizeException;
import wumpus.service.MapReader;
import wumpus.validator.MapValidator;

import java.io.BufferedReader;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
public class MapReaderTest {
    //@Mock
    MapValidator mapValidatorMock = new MapValidator();

    @Mock
    BufferedReader bufferedReaderMock;

    MapReader mapReader;
    @BeforeEach
    public void Setup() {
        mapReader = new MapReader(mapValidatorMock, bufferedReaderMock);
    }

    @Test
    public void ReadMap_InvalidSecondRow() throws InvalidSizeException, IOException {
        //given
        given(bufferedReaderMock.readLine()).willReturn("6 B 5 E", "WWWWW");

        //when
        InvalidSizeException ex = assertThrows(InvalidSizeException.class, () -> mapReader.readMap());

        //then
        assertEquals(ex.getMessage(), "Column size is invalid!");
    }
}
