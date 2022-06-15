import java.util.Random;

public class PrimitiveAlligator {

    private GenerateContent _go;
    private int row;
    private int column;
    Random _goAl = new Random();
    String[][][] DungeonFloor;

    public PrimitiveAlligator (String Dungeon[][][], GenerateContent _send, int startAIRow, int startAIColumn) {
        DungeonFloor = Dungeon;
        _go = _send;
        row = startAIRow;
        column = startAIColumn;
    }

    public void placeAlligator(int startAIRow, int startAIColumn) { //setAlligator's starting point
        row = startAIRow;
        column = startAIColumn;
    }


    public int getAlligatorRow() {
        return row;
    }

    public int getAlligatorColumn() { //I can retrieve trap info, dungeon parameters
        return column;
    }

    public void move() { //Alligator could access Generate's set methods...

        for(;;) {
            switch(1 + Math.abs(_goAl.nextInt()%4)) { //Alligator isn't so bright once it hits a wall...
                case 1:
                    row = this.getAlligatorRow() - 1;
                    column = this.getAlligatorColumn();
                    break;
                case 2:
                    row = this.getAlligatorRow();
                    column = this.getAlligatorColumn() + 1;
                    break;
                case 3:
                    row = this.getAlligatorRow();
                    column = this.getAlligatorColumn() - 1;
                case 4:
                    row = this.getAlligatorRow() + 1;
                    column = this.getAlligatorColumn();
                    break;
                default:
                    row = this.getAlligatorRow();
                    column = this.getAlligatorColumn();
                    break;
            }

            if(row == -1) {							//Take care of the out of bounds first. The alligator avoids the traps and the exit
                row = this.getAlligatorRow() + 1;   //Could rewrite this so it's just if statements, it would get rid of the continue statements
                continue;
            } else if(row == 8) {
                row = this.getAlligatorRow() - 1;
                continue;
            } else if(column == -1) {
                column = this.getAlligatorColumn() + 1;
                continue;
            } else if(column == 8) {
                column = this.getAlligatorColumn() - 1;
                continue;
            } else if(DungeonFloor[this.getAlligatorRow()][this.getAlligatorColumn()] == DungeonFloor[_go.getPoisonTrapRow()][_go.getPoisonTrapColumn()]) {
                continue;
            } else if(DungeonFloor[this.getAlligatorRow()][this.getAlligatorColumn()] == DungeonFloor[_go.getWallTrapRow()][_go.getWallTrapColumn()]) {
                continue;
            } else if(DungeonFloor[this.getAlligatorRow()][this.getAlligatorColumn()] == DungeonFloor[_go.getExitRow()][_go.getExitColumn()]) {
                continue;
            } else {
                break;
            }
        } //end FOR  Remember Alligator can use its own member variables
    }
} //End Primitive Alligator
