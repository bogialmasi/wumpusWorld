package wumpus.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class MapLoader {

    public String[][] loadedMap;


    public void loadMap() {
        try {
            File mapFile = new File("wumpusinput.txt");
            Scanner sc = new Scanner(mapFile);
            String[] heroData = sc.nextLine().split(" ");
            int mapSize = Integer.parseInt(heroData[0]);
            String heroCol = heroData[1];
            int heroRow = Integer.parseInt(heroData[2]);
            String heroDir = heroData[3];
            while (sc.hasNextLine()) {
                String[] rowElements = sc.nextLine().split("");
                System.out.println(rowElements);
                String[][] newLoadedMap = new String[rowElements.length][rowElements.length];
                for (int i = 0; i < rowElements.length; i++) {
                    for (int j = 0; j < rowElements.length; j++) {
                        newLoadedMap[i][j] =rowElements[i];
                    }
                }
                loadedMap = newLoadedMap;
            }
            sc.close();

        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}

