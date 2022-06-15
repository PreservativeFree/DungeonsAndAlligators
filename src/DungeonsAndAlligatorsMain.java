//This is a text based game.

import java.util.Scanner;

public class DungeonsAndAlligatorsMain {

    public static void main (String args[]) {
        int moveRow = 0, moveColumn = 0;
        int pathRow = 0, pathColumn = 0;
        int stepCounter = 0, deathCounter = 0;
        int AIRow = 4, AIColumn = 4;
        Scanner input = new Scanner(System.in);
        char choice;
        int SilverFound = 0, GoldFound = 0, MapSecretFound = 0;
        boolean REDOTHEDUNGEON = false;
        String DungeonFloor[][][];
        DungeonFloor = new String [8][8][3];

        GenerateContent _send = new GenerateContent(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
        DungeonFill load = new DungeonFill(DungeonFloor, _send);


        load.setTrapsTreasuresExit();
        load.constructDungeon();
        PrimitiveAlligator _Alligator = new PrimitiveAlligator(DungeonFloor, _send, AIRow, AIColumn);
        _Alligator.placeAlligator(AIRow, AIColumn);



        for(int r = 0; r < 3; r++)
            System.out.printf("%s", DungeonFloor[pathRow][pathColumn][r]);

        for(;;) {

            for(;;) {
                System.out.print("\n\nWhich direction will you go?\n>");
                choice = input.next().charAt(0);
                System.out.println("You chose " + choice);
                System.out.println();
                stepCounter++;

                switch(choice) {
                    case 'n':
                    case 'N':
                        moveRow = -1;
                        moveColumn = 0;
                        break;
                    case 'e':
                    case 'E':
                        moveRow = 0;
                        moveColumn = 1;
                        break;
                    case 'w':
                    case 'W':
                        moveRow = 0;
                        moveColumn = -1;
                        break;
                    case 's':
                    case 'S':
                        moveRow = 1;
                        moveColumn = 0;
                        break;
                    default:
                        moveRow = 0;
                        moveColumn = 0;
                        break;
                }
                pathRow += moveRow;
                pathColumn += moveColumn;

                if(pathRow == -1) {
                    System.out.print("\n\nOUCH! This wall is lined with sharp metallic spikes!!\nCareful where you want to go!\n\n");
                    pathRow++;
                    deathCounter++;
                } else if(pathRow == 8) {
                    System.out.print("\n\nEek! This wall's force field will not let you pass here!\nTry another Route!\n\n");
                    pathRow--;
                    deathCounter++;
                } else if(pathColumn == -1) {
                    System.out.print("\n\nThis Dungeon's Wall is coated in Corrosive Acid!\n\nTry another Route!\n\n");
                    pathColumn++;
                    deathCounter++;
                } else if(pathColumn == 8) {
                    System.out.print("\n\nSTOP! If you go East any farther, you will enter the Void!\nThere's no escape there! Try another route!\n\n");
                    pathColumn--;
                    deathCounter++;
                } else {
                    break;
                }
            }

            if(choice == 'Q' || choice == 'q' || deathCounter > 3)
                break;

            for(int r = 0; r < 3; r++)
                System.out.printf("%s", DungeonFloor[pathRow][pathColumn][r]);

            _Alligator.move();
            System.out.println("\nThe Alligator is in Room " + _Alligator.getAlligatorRow() + " inside Hallway " + _Alligator.getAlligatorColumn() + "!!\n");

            if(DungeonFloor[pathRow][pathColumn] == DungeonFloor[_Alligator.getAlligatorRow()][_Alligator.getAlligatorColumn()]) { //Redo the dungeon, reset yourself, alligator
                System.out.print("Oh No! The Alligator found you! It's lunch time for someone today!\n");
                System.out.print("\nOops! It looks like you're Dead!\nYour spirit has been sent back to the Beginning!\n\nGo South or east only\n");
                pathRow = 0;
                pathColumn = 0;
                REDOTHEDUNGEON = true;
            }


            if(DungeonFloor[pathRow][pathColumn] == DungeonFloor[_send.getPoisonTrapRow()][_send.getPoisonTrapColumn()]) {
                System.out.print("\n\nOops! It looks like you're Dead!\nYour spirit has been sent back to the Beginning!\n\nGo South or east only\n");
                pathRow = 0;
                pathColumn = 0;
                REDOTHEDUNGEON = true;
            }

            if(DungeonFloor[pathRow][pathColumn] == DungeonFloor[_send.getWallTrapRow()][_send.getWallTrapColumn()]) {
                System.out.print("\n\nWhat's this!? The walls crushed you into bits, so it looks like you're Dead!\nYour spirit has been sent back to the Beginning!\n\nGo South or east only\n");
                pathRow = 0;
                pathColumn = 0;
                REDOTHEDUNGEON = true;
            }
            if(DungeonFloor[pathRow][pathColumn] == DungeonFloor[_send.getSilverRow()][_send.getSilverColumn()]) {
                if(SilverFound == 1) {
                    System.out.println("\n\nYou visited this treasure room already so it's empty now.\n");
                    SilverFound = 1;
                } else {
                    SilverFound = 1;
                }
            }
            if(DungeonFloor[pathRow][pathColumn] == DungeonFloor[_send.getGoldRow()][_send.getGoldColumn()]) {
                if(GoldFound == 1) {
                    System.out.println("\n\nYou visited this treasure room already so it's empty now.\n");
                    GoldFound = 1;
                } else {
                    GoldFound = 1;
                }
            }

            if(DungeonFloor[pathRow][pathColumn] == DungeonFloor[_send.getMapRow()][_send.getMapColumn()]) {
                MapSecretFound = 1;
                System.out.print("\n\nFinding the exit is hard! After looking at the Map,\nyou see that you can get out of here if " +
                        "you can get to\n\nRoom " + _send.getExitRow() + " in Hallway " + _send.getExitColumn() + "!\n");
            }
            if(DungeonFloor[pathRow][pathColumn] == DungeonFloor[_send.getExitRow()][_send.getExitColumn()]) {
                System.out.print("\n\nGreat Run! At least you didn't run into the Alligator...\n\n");
                break;
            }

            if(REDOTHEDUNGEON) {
                load.setTrapsTreasuresExit();
                load.constructDungeon();
                _Alligator = new PrimitiveAlligator(DungeonFloor, _send, AIRow, AIColumn);
                _Alligator.placeAlligator(AIRow, AIColumn);
                REDOTHEDUNGEON = false;
            }
            else {
                continue;
            }

        } //end inner infinite for

        switch(SilverFound + GoldFound + MapSecretFound) {
            case 1:
                System.out.println("\nCool you found one of the treasures. You missed 2 two though.\n");
                break;
            case 2:
                System.out.println("\nNice! You found two of the treasures, although you missed one.\n");
                break;
            case 3:
                System.out.println("\nYou found all of the Dungeon's treasures! You're pretty good!\n");
                break;
            default:
                System.out.println("\nThat's too bad you didn't even find one treasure!\n");
                break;
        }

        if(deathCounter > 3)
            System.out.println("\n\nYou hit the walls way too many times! Game Over!\nIt took you " + stepCounter + " steps to die with no prizes!");
        else if(choice == 'Q' || choice == 'q')
            System.out.println("\nYou quit the game early. It took you " + stepCounter + " steps before you called it quits.");
        else if(stepCounter > 200)
            System.out.println("It took you " + stepCounter + " steps to reach the exit! Not good enough.\n\n");
        else if(stepCounter > 120)
            System.out.println("It took you " + stepCounter + " steps to reach the exit! Great job, but your treasure finding skills need work!\n\n");
        else if(stepCounter > 60)
            System.out.println("It took you " + stepCounter + " steps to reach the exit! Fantastic display of Navigation!\n\n");
        else
            System.out.println("Either you are a God at video games or you lucked out and\nit only took you " + stepCounter + " steps to exit the Dungeon. Excellent work!\n\n");

    } //end main
}