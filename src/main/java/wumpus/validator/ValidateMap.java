package wumpus.validator;

import wumpus.exceptions.InvalidSizeException;
import wumpus.service.MapReader;
import wumpus.world.World;

public class ValidateMap implements MapValidator {
    MapReader mapReader = new MapReader();
    World worldToValidate = new World(mapReader.readMap().getSize());

    @Override
    public void validateSizeOfMap(World world) throws InvalidSizeException {
        if (worldToValidate.getCol() != worldToValidate.getRow()) {
            throw new InvalidSizeException("The size of the map is invalid!");
        } else {
            validateContentOfMap(worldToValidate);
        }
    }

    @Override
    public void validateContentOfMap(World world) {
        String[][] worldToValidateContent = world.getWorld();
        int heroCounter = 0;
        int wumpusCounter = 0;
        int goldCounter = 0;
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

