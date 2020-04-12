import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Scanner;

abstract class unit {
    protected double price;
    protected double loot;
    protected int moveLimit;
    protected int hp;
    protected int range;
    protected int coolDownTime;
    protected int id;
    protected int size;
    protected String symbol;
    protected String type;
    //protected boolean isInCoolDown;
    protected int remainingCoolDownTime;
    //area
    protected ArrayList<unitCell> unitCells;

    //constructor:
    public unit (int id) {
        this.id = id;
        this.remainingCoolDownTime = 0;
        unitCells = new ArrayList<>();
    }

    public void setId(int id) {this.id = id;}

    //attack:
    public boolean isInCoolDown() {
        if(this.remainingCoolDownTime == 0) {
            return false;
        }
        else if(this.remainingCoolDownTime > 0) {
            return true;
        }
        else {
            System.out.println("ERROR IN \"isInCoolDown function in unit class\"");
            return true;
        }
    }
    public boolean unitIsAlive() {
        for(unitCell currUnitCell : this.unitCells) {
            if(currUnitCell.getUnTouched()) {
                return true;
            }
        }
        return false;
    }
    public void setToCoolDown() {
        this.remainingCoolDownTime = this.coolDownTime;
    }
    public ArrayList<cell> attack(int boardSize) {
        System.out.println(this.type + "||Enter Coordinates you wanna attack...\t\t(" + this.range*this.range +"x cells)");

        return scan.scanTargetCells(this, boardSize);
    }
    public void updateCoolDown() {
        if(this.remainingCoolDownTime > 0) {
            remainingCoolDownTime--;
        }
    }




    public boolean equals(unit unit) {
        return this.id == unit.id;
    }




    //functions about MOVE:
    public ArrayList<move> getValidMoves(player player) {
        ArrayList<move> results = new ArrayList<>();

        ArrayList<move> allMoves = this.getAllPossibleMoves();
        //now we check for validation:
        for(move currMove : allMoves) {
            if(moveIsValid(currMove, player)) {
               results.add(currMove);
            }
        }
        return results;
    }
    public ArrayList<move> getAllPossibleMoves() {
        //making All possible moves for this unit:
        ArrayList<move> results = new ArrayList<>();
        int id = 0;
        for(int n = 1; n <= this.moveLimit; n++) {
            results.add(new move(0, +n, id++));
            results.add(new move(0, -n, id++));
            results.add(new move(+n, 0, id++));
            results.add(new move(-n, 0, id++));
        }
        return results;
    }
    public boolean moveIsValid(move move, player player) {
        //System.out.println();
        //System.out.println("Checking move("+move.getX_axis_scale()+", "+move.getY_axis_scale()+") for "+this.type+" with id----> " + this.id);

        //checking if move interferes with the BoardSIZE:
        for(unitCell curCell : this.unitCells) {
            if(curCell.getX() + move.getX_axis_scale() < 0 || curCell.getX() + move.getX_axis_scale() >= player.getBoardSize()) {
                //System.out.println("Out of X boundary!");
                return false;
            }
            if(curCell.getY() + move.getY_axis_scale() < 0 || curCell.getY() + move.getY_axis_scale() >= player.getBoardSize()) {
                //System.out.println("Out of Y boundary!");
                return false;
            }
        }

        ////checking if move interferes with the otherUnits:
        //here we want to check like this:
        //if X_axis = 0 and Y_axis = -3 : we want to check (0, -1) and (0, -2) and (0, -3)

        ///if move includes jump over other units, then you dont have to check (0, -1) and (0, -2) for move(0, -3)
        for(unitCell currCell : this.unitCells) {

            for(int n = 1; n <= java.lang.Math.abs(move.getX_axis_scale() + move.getY_axis_scale()); n++) {
                int x = n, y = n;

                if(move.getX_axis_scale()<0) { x = n * -1;}
                else if(move.getX_axis_scale()==0) { x = 0;}

                if(move.getY_axis_scale()<0) { y = n * -1;}
                else if(move.getY_axis_scale()==0) { y = 0;}

                for(unit currUnit : player.getUnits()) {
                    if(currUnit.id == this.id) {continue;}

                    for(unitCell currOtherUnitCell : currUnit.unitCells) {
                        if(currOtherUnitCell.getX() == currCell.getX()+x && currOtherUnitCell.getY() == currCell.getY()+y) {
                            //System.out.println("interfere in (" + currOtherUnitCell.getX() + ", " + currOtherUnitCell.getY() + ")");
                            //System.out.println("\t move is (" + x + ", " + y + ")");
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }

    public void showAllPossibleMoves(player player) {
        System.out.println("Move List:");
        for(move currMove : this.getValidMoves(player)) {
            System.out.println(currMove.getId()+") " + "X += " + currMove.getX_axis_scale() + ", Y += " + currMove.getY_axis_scale());
        }
    }

    public void move(move move) {
        for(unitCell currUnitCell : this.unitCells) {
            currUnitCell.getCell().setCoordinates(currUnitCell.getX()+move.getX_axis_scale(), currUnitCell.getY()+move.getY_axis_scale());
        }
    }


    //checks based on "isHitted" attribute in unitCell class
    public boolean isHitted() {
        for(unitCell currUnitCell : this.unitCells) {
            if(!currUnitCell.getUnTouched()) {
                return true;
            }
        }
        return false;
    }






    public void show() {
        System.out.println("id: " + this.id);
        System.out.println("type: " + this.type);
        System.out.println("Coordinates: ");
        System.out.print("\t");
        for(unitCell currCell : this.unitCells) {
            System.out.print("(" + currCell.getX() + ", " + currCell.getY() + ")  ");
        }
        System.out.println("\n");
    }
}


