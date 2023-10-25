package wumpus.service;

import wumpus.world.World;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class MapReader {

    private final String MapFilePath = "src/main/resources/wumpuszinput.txt";
    private final String VALID_SIZE_REGEX = "[0-9]+";

    private int mapSize = 0;

    public World readMap() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(MapFilePath));
            World newMap = null;

            String line = reader.readLine();


            while (line != null) { // first line in wumpuszinput contains these data
                if (line.startsWith(VALID_SIZE_REGEX)) {
                    String[] worldData = line.split(" ");
                    int mapSize = Integer.parseInt(worldData[0]);
                    String heroCol = worldData[1];
                    int heroRow = Integer.parseInt(worldData[2]);
                    String heroDir = worldData[3];
                    line = reader.readLine();
                } else {
                    String rowData[] = line.split("");
                    mapSize++;
                    line = reader.readLine();
                }
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return new World(mapSize);

    }

}

