import java.util.Random;

public class DungeonFill {

    String Position, DeathTrapPoison, DeathTrapWalls, TreasureSilver, TreasureGold, TreasureMap, NoTrap, NoTreasure, ExitPosition, ExitTrap, ExitTreasure;

    String[][][] DungeonFloor;

    private GenerateContent _ret;

    Random text = new Random();

    public DungeonFill(String Dungeon[][][], GenerateContent _send) {
        DungeonFloor = Dungeon;
        _ret = _send;
    }
    /**/
    public void setTrapsTreasuresExit() {
        _ret.setTraps(0, 0, 0, 0);
        _ret.setTreasures(0, 0, 0, 0, 0, 0);
        _ret.setExit(0, 0);

    }

    public void constructDungeon () {
        DeathTrapPoison = "You stepped into the room filled with Neurotoxic Gas! You are dead!\n";
        DeathTrapWalls = "This room's walls are moving and closing in on you.\nYou've been crushed to a pulp! Game over!\n";
        TreasureSilver = "You ran into a bag full of Silver Coins!\n";
        TreasureGold = "You found a 100 kg Gold Bar! Good Work!\n";
        TreasureMap = "You found a Map showing you the location of the secret exit!\n";
        ExitPosition = "You are in Room " + _ret.getExitRow() + " inside Hallway " + _ret.getExitColumn() + ".\n";
        ExitTrap = "There isn't any trap here and that's because\n";
        ExitTreasure = "You did it! You found the exit!\n";

        //Both classes should know about the RNG? Only Generate Content. Any other RNG is independent of the event placement.

        //System.out.println("The Poison Trap is located in Room " + _ret.getPoisonTrapRow() + " in Hallway " + _ret.getPoisonTrapColumn() );	//Get position? Get String? Get Boolean?
        //System.out.println("The Wall Death trap is located in Room " + _ret.getWallTrapRow() + " in Hallway " + _ret.getWallTrapColumn());
        //System.out.println("The Silver coins are located in Room " + _ret.getSilverRow() + " in Hallway " + _ret.getSilverColumn());
        //System.out.println("The Gold Bar is located in Room " + _ret.getGoldRow() + " in Hallway " + _ret.getGoldColumn());
        //System.out.println("The Secret Map is located in Room " + _ret.getMapRow() + " in Hallway " + _ret.getMapColumn());
        //System.out.println("The Exit is located in Room " + _ret.getExitRow() + " in Hallway " + _ret.getExitColumn());
        System.out.println();
        for(int row = 0; row < DungeonFloor.length; row++)
            for(int column = 0; column < DungeonFloor.length; column++) {
                if(_ret.getPoisonTrapRow() == row && _ret.getPoisonTrapColumn() == column) {
                    Position = "You are in Room " + row + " inside Hallway " + column + ".\n";
                    DungeonFloor[row][column][0] = Position;
                    DungeonFloor[row][column][1] = DeathTrapPoison;
                    DungeonFloor[row][column][2] = "You dropped any of the treasures you collected!\n";
                } else if(_ret.getWallTrapRow() == row && _ret.getWallTrapColumn() == column) {
                    Position = "You are in Room " + row + " inside Hallway " + column + ".\n";
                    DungeonFloor[row][column][0] = Position;
                    DungeonFloor[row][column][1] = DeathTrapWalls;
                    DungeonFloor[row][column][2] = "You dropped any of the treasures you collected! Tough Break!\n";
                } else if (_ret.getSilverRow() == row && _ret.getSilverColumn() == column) { //Treasure Row Block
                    Position = "You are in Room " + row + " inside Hallway " + column + ".\n";
                    DungeonFloor[row][column][0] = Position;
                    DungeonFloor[row][column][1] = "Nice catch!\n";
                    DungeonFloor[row][column][2] = TreasureSilver;
                } else if (_ret.getGoldRow() == row && _ret.getGoldColumn() == column) {
                    Position = "You are in Room " + row + " inside Hallway " + column + ".\n";
                    DungeonFloor[row][column][0] = Position;
                    DungeonFloor[row][column][1] = "Look at that!\n";
                    DungeonFloor[row][column][2] = TreasureGold;
                } else if(_ret.getMapRow() == row && _ret.getMapColumn() == column) {
                    Position = "You are in Room " + row + " inside Hallway " + column + ".\n";
                    DungeonFloor[row][column][0] = Position;
                    DungeonFloor[row][column][1] = "There's an old parchment over there!\n";
                    DungeonFloor[row][column][2] = TreasureMap;
                } else if(_ret.getExitRow() == row && _ret.getExitColumn() == column) {
                    Position = "You are in Room " + row + " inside Hallway " + column + ".\n";
                    DungeonFloor[row][column][0] = ExitPosition;
                    DungeonFloor[row][column][1] = ExitTrap;
                    DungeonFloor[row][column][2] = ExitTreasure;
                } else {

                    Position = "You are in Room " + row + " inside Hallway " + column + ".\n";

                    switch(1 + text.nextInt(2)) {
                        case 1:
                            NoTrap = "The room is empty from traps. You can move on.\n";
                            break;
                        case 2:
                            NoTrap = "It looks safe here, but there are traps in other rooms!\n";
                            break;
                        default:
                            NoTrap = "This room is safe, no traps here!\n";
                            break;

                    }
                    switch(1 + text.nextInt(2)) {
                        case 1:
                            NoTreasure = "No treasure here. Maybe the next room will have it?\n";
                            break;
                        case 2:
                            NoTreasure = "This room has no treasure, but another room should have it!\n";
                            break;
                        default:
                            NoTreasure = "There is nothing of value here, try another room.\n";
                            break;
                    }

                    DungeonFloor[row][column][0] = Position;
                    DungeonFloor[row][column][1] = NoTrap;
                    DungeonFloor[row][column][2] = NoTreasure;
                }
            }
    } //End ConstructDungeon Method
}// End Dungeon Fill
