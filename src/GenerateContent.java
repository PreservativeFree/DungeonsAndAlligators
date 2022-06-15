//You will need an object of this in the other classes

import java.util.Random;


public class GenerateContent {

    private int limitTraps, limitTreasure;
    private int TreasureRow, TreasureColumn;
    private int TrapRowPoison, TrapColumnPoison, TrapRowWalls, TrapColumnWalls, TreasureRowSilver, TreasureColumnSilver, TreasureRowGold, TreasureColumnGold, TreasureRowMap, TreasureColumnMap;
    private int exitSquareRow, exitSquareColumn;
    Random shift = new Random(4251);

    public GenerateContent (int poisonRow, int poisonColumn, int wallRow, int wallColumn, int silverCoinsRow, int silverCoinsColumn,
                            int goldBarRow, int goldBarColumn, int secretMapRow, int secretMapColumn, int exitRow, int exitColumn) {

        TrapRowPoison = poisonRow;
        TrapColumnPoison = poisonColumn;
        TrapRowWalls = wallRow;
        TrapColumnWalls = wallColumn;
        TreasureRowSilver = silverCoinsRow;
        TreasureColumnSilver = silverCoinsColumn;
        TreasureRowGold = goldBarRow;
        TreasureColumnGold = goldBarColumn;
        TreasureRowMap = secretMapRow;
        TreasureColumnMap = secretMapColumn;
        exitSquareRow = exitRow;
        exitSquareColumn = exitColumn;

    }

    //four gets
    public int getPoisonTrapRow() {return TrapRowPoison;}
    public int getPoisonTrapColumn() {return TrapColumnPoison;}
    public int getWallTrapRow() {return TrapRowWalls;}
    public int getWallTrapColumn() {return TrapColumnWalls;}

    public int getSilverRow() {return TreasureRowSilver;}
    public int getSilverColumn() {return TreasureColumnSilver;}
    public int getGoldRow(){return TreasureRowGold;}
    public int getGoldColumn(){return TreasureColumnGold;}
    public int getMapRow(){return TreasureRowMap;}
    public int getMapColumn(){return TreasureColumnMap;}

    public int getExitRow() {return exitSquareRow;}
    public int getExitColumn() {return exitSquareColumn;}

    public void setTraps(int poisonRow, int poisonColumn, int wallRow, int wallColumn) {
        boolean deathTrapSetPoison = false;
        boolean deathTrapSetWalls = false;
        for(;;) {

            if(deathTrapSetPoison) {
                limitTraps = 1 + shift.nextInt(2); //1 or 2
            }
            if(deathTrapSetWalls) {
                do {
                    limitTraps = shift.nextInt(3);
                }while(limitTraps == 1); //All RNG calls will be 0 or 2
            }
            if(deathTrapSetPoison && deathTrapSetWalls) {
                limitTraps = 3 + shift.nextInt(3);
            }
            if(!deathTrapSetPoison && !deathTrapSetWalls) {
                limitTraps = shift.nextInt(2);  //0 or 1
            }

            switch(limitTraps) {
                case 0:
                    deathTrapSetPoison = true;
                    do {
                        poisonRow = shift.nextInt(7); //Range is 0-6
                        TrapRowPoison = poisonRow;
                        poisonColumn = shift.nextInt(7);
                        TrapColumnPoison = poisonColumn;
                    } while((TrapRowPoison == 0 && TrapColumnPoison == 0) || (TrapRowPoison == TrapRowWalls && TrapColumnPoison == TrapColumnWalls));
                    break;
                case 1:
                    deathTrapSetWalls = true;
                    do {
                        wallRow = shift.nextInt(7);
                        TrapRowWalls = wallRow;
                        wallColumn = shift.nextInt(7);
                        TrapColumnWalls = wallColumn;
                    } while((TrapRowWalls == 0 && TrapColumnWalls == 0) || (TrapRowPoison == TrapRowWalls && TrapColumnPoison == TrapColumnWalls));
                    break;
                default:
                    break;
            }

            if(deathTrapSetWalls && deathTrapSetPoison)
                break;
        }
    } //end SetTraps!

    public void setTreasures(int silverCoinsRow, int silverCoinsColumn, int goldBarRow, int goldBarColumn, int secretMapRow, int secretMapColumn) {

        boolean TreasureFoundSilver = false; 					//A bag of Silver Coins
        boolean TreasureFoundGold = false; 						//A 100 kg gold bar
        boolean TreasureFoundMap = false; 						//Map that tells you the secret exit

        for(;;) {

            if(TreasureFoundSilver) {
                limitTreasure = 11 + shift.nextInt(3); //I don't want this rolling a 10 anymore, so it's going to roll 11,12,13 only
            }
            if(TreasureFoundGold) {
                do {
                    limitTreasure = 10 + shift.nextInt(4); //I don't want this rolling an 11 anymore, if I find Gold first The number will never roll 11 but I could still roll 12
                } while(limitTreasure == 11);
            }
            if (TreasureFoundMap) { //I don't want this rolling a 12 anymore
                do {
                    limitTreasure = 10 + shift.nextInt(4);
                } while(limitTreasure == 12);
            }

            if(TreasureFoundSilver && TreasureFoundGold && !TreasureFoundMap) { //No map found yet
                do {
                    limitTreasure = 10 + shift.nextInt(4);
                } while(limitTreasure == 10 || limitTreasure == 11);
            }
            if(TreasureFoundSilver && !TreasureFoundGold && TreasureFoundMap) { //No gold found yet
                do {
                    limitTreasure = 10 + shift.nextInt(4);
                } while(limitTreasure == 10 || limitTreasure == 12);
            }
            if(!TreasureFoundSilver && TreasureFoundGold && TreasureFoundMap) { //No Silver found yet
                do {
                    limitTreasure = 10 + shift.nextInt(4);
                } while(limitTreasure == 11 || limitTreasure == 12);
            }

            if(TreasureFoundSilver && TreasureFoundGold && TreasureFoundMap)
                limitTreasure = 13 + shift.nextInt(4);

            if(!TreasureFoundSilver && !TreasureFoundGold && !TreasureFoundMap)
                limitTreasure = 10 + shift.nextInt(4);

            switch(limitTreasure) { //TreasureRowSilver TreasureColumnSilver TreasureSilver TreasureRowGold TreasureColumnGold TreasureGold TreasureRowMap TreasureColumnMap TreasureMap
                case 10:
                    TreasureFoundSilver = true;
                    do {

                        silverCoinsRow = shift.nextInt(8);
                        TreasureRowSilver = silverCoinsRow;
                        silverCoinsColumn = shift.nextInt(8);
                        TreasureColumnSilver = silverCoinsColumn;

                    } while( (TreasureRowSilver == 0 && TreasureColumnSilver == 0) || (TreasureRowSilver == TreasureRowGold && TreasureColumnSilver == TreasureColumnGold) ||
                            (TreasureRowSilver == TreasureRowMap && TreasureColumnSilver == TreasureColumnMap) || (TreasureRowSilver == TrapRowPoison && TreasureColumnSilver == TrapColumnPoison) ||
                            (TreasureRowSilver == TrapRowWalls && TreasureColumnSilver == TrapColumnWalls));
                case 11:
                    TreasureFoundGold = true;
                    do {
                        goldBarRow = shift.nextInt(8);
                        TreasureRowGold = goldBarRow;
                        goldBarColumn = shift.nextInt(8);
                        TreasureColumnGold = goldBarColumn;

                    } while ((TreasureRowGold == 0 && TreasureColumnGold == 0) || (TreasureRowGold == TreasureRowSilver && TreasureColumnGold == TreasureColumnSilver) ||
                            (TreasureRowGold == TreasureRowMap && TreasureColumnGold == TreasureColumnMap) || (TreasureRowGold == TrapRowPoison && TreasureColumnGold == TrapColumnPoison) ||
                            (TreasureRowGold == TrapRowWalls && TreasureColumnGold == TrapColumnWalls));
                case 12:
                    TreasureFoundMap = true;
                    do {
                        secretMapRow = shift.nextInt(8);
                        TreasureRowMap = secretMapRow;
                        secretMapColumn = shift.nextInt(8);
                        TreasureColumnMap = secretMapColumn;

                    } while ( (TreasureRowMap == 0 && TreasureColumnMap == 0) || (TreasureRowMap == TreasureRowSilver && TreasureColumnMap == TreasureColumnSilver) ||
                            (TreasureRowMap == TreasureRowGold && TreasureColumnMap == TreasureColumnGold) || (TreasureRowMap == TrapRowPoison && TreasureColumnMap == TrapColumnPoison) ||
                            (TreasureRowMap == TrapRowWalls && TreasureColumnMap == TrapColumnWalls));
                default:
                    break;
            }
            if(TreasureFoundSilver && TreasureFoundGold && TreasureFoundMap)
                break;
        }
    } //setTreasures

    public void setExit(int exitRow, int exitColumn) {
        do {
            exitRow = shift.nextInt(8);
            exitSquareRow = exitRow;
            exitColumn = shift.nextInt(8);
            exitSquareColumn = exitColumn;
        } while((exitSquareRow == 0 && exitSquareColumn == 0) || (exitSquareRow == TreasureRowSilver && exitSquareColumn == TreasureColumnSilver) ||
                (exitSquareRow == TreasureRowGold && exitSquareColumn == TreasureColumnGold) || (exitSquareRow == TrapRowPoison && exitSquareColumn == TrapColumnPoison) ||
                (exitSquareRow == TrapRowWalls && exitSquareColumn == TrapColumnWalls) || (exitSquareRow == TreasureRowMap && exitSquareColumn == TreasureColumnMap));
    } //SetExit

} //end GenerateContent
