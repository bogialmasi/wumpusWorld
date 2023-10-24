package wumpus.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class LoadMap {
    public void loadMap() {
        try {
            File myMap = new File("wumpusinput.txt");
            Scanner myReader = new Scanner(myMap);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                System.out.println(data);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}

