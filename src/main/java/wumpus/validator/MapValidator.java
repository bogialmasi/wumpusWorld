package wumpus.validator;

import wumpus.exceptions.InvalidSizeException;
import wumpus.world.World;

public class MapValidator implements IMapValidator {
    @Override
    public void validateSizeOfMap(int mapSize, World world) throws InvalidSizeException {
        if (world.getN() != mapSize || !(world.getN() >= 6 && world.getN()<= 20)) {
            throw new InvalidSizeException("The size of the map is invalid!");
        } else {
            System.out.println("Size of map is OK.");
        }
    }

    @Override
    public void validateRowSize(int mapSize, int rowLength) throws InvalidSizeException {
        if (mapSize != rowLength) {
            throw new InvalidSizeException("Row size is invalid!");
        } else {
            System.out.println("Size of rows are OK.");
        }
    }

    @Override
    public boolean validateColumnSize(int mapSize, int columnLength) throws InvalidSizeException {
        if (mapSize != columnLength) {
            throw new InvalidSizeException("Column size is invalid!");
        } else {
            System.out.println("Size of column is OK.");
        }
        return false;
    }

    @Override
    public void validateContentOfMap(World world) {
        int heroCounter = 0;
        int wumpusCounter = 0;
        int goldCounter = 0;

        //size of arrays check
        // check if first and last rows are all W and the end/beginning of rest is W
        // check if hero num and gold num correct

        // count how many of each objects exists within the map, the map is invalid if there's more objects than allowed

        /*for (int i = 0; i < worldToValidateContent.length; i++) {
            worldToValidateContent[0][i];
            worldToValidateContent[worldToValidateContent.length-1][i];
            worldToValidateContent[i][0];
            worldToValidateContent[i][worldToValidateContent.length-1];
        }*/

    }

    /*public boolean checkForWall(World world){
        if (world.get)
    }*/
    // check if the walls are correct


        /*
        * WUMPUSZ-ból N<=8 esetén 1 lehessen, 9<=N<=14 esetén 2, N>14 esetén 3, ez automatikusan állítódjon be N alapján
          ARANY-ból 1 legyen, a játék célja az, hogy a hős visszavigye a kiindulásához
          HŐSBŐL is 1 darab legyen, neki a kezdőirányát is meg kell adni, a nyilainak száma automatikusan számolódjon a wumpuszok számából, azzal egyenlően, az aranyat ne birtokolja kezdéskor
          * */
}

