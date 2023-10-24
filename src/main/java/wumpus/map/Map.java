package wumpus.map;

import wumpus.service.MapLoader;

public class Map implements MapGenerator {

    private int size;
    MapLoader mapLoader = new MapLoader();

    public Map(int size) {
        this.size = size;
    }

    @Override
    public void generateMap() {
        mapLoader.loadMap();
        for (int i = 0; i < this.size; i++) {
            for (int j = 0; j < this.size; j++) {

            }
        }
    }
}
