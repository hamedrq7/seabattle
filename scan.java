import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;

final class scan {
    public static Scanner scanner = new Scanner(System.in);

    //about game prep:
    public static soldier scanSoldier(int index, player player) {
        boolean isValid = true;
        ArrayList<cell> cells;
        soldier tempSoldier = new soldier(index);
        do {
            cells = new ArrayList<>();
            for(int j = 0; j < gameConstants.soldierSize; j++) {
                System.out.println("Soldier " + j + "....\t(X Y)");
                cells.add(scanCell(tempSoldier, player));
                isValid = cellsAreBinding(cells, gameConstants.soldierSize);
            }
        } while(!isValid);

        for(cell cell : cells) {
            unitCell tempUnitCell = new unitCell(cell, tempSoldier);
            tempSoldier.unitCells.add(tempUnitCell);
        }
        return tempSoldier;
    }
    public static cavalry scanCavalry(int index, player player) {
        boolean isValid = true;
        ArrayList<cell> cells;
        cavalry tempCavalry = new cavalry(index);
        do {
            cells = new ArrayList<>();
            for(int j = 0; j < gameConstants.cavalrySize; j++) {
                System.out.println("Cavalry " + j + "....\t(X Y)");
                cells.add(scanCell(tempCavalry, player));
                isValid = cellsAreBinding(cells, gameConstants.cavalrySize);
            }
        } while(!isValid);

        for(cell cell : cells) {
            unitCell tempUnitCell = new unitCell(cell, tempCavalry);
            tempCavalry.unitCells.add(tempUnitCell);
        }
        return tempCavalry;
    }
    public static castle scanCastle(int index, player player) {
        boolean isValid = true;
        ArrayList<cell> cells;
        castle tempCastle = new castle(index);
        do {
            cells = new ArrayList<>();
            for(int j = 0; j < gameConstants.castleSize; j++) {
                System.out.println("Castle " + j + "....\t(X Y)");
                cells.add(scanCell(tempCastle, player));
                isValid = cellsAreBinding(cells, gameConstants.castleSize);
            }
        } while(!isValid);

        for(cell cell : cells) {
            unitCell tempUnitCell = new unitCell(cell, tempCastle);
            tempCastle.unitCells.add(tempUnitCell);
        }
        return tempCastle;
    }
    public static hq scanHq(int index, player player) {
        boolean isValid = true;
        ArrayList<cell> cells;
        hq tempHq = new hq(index);
        do {
            cells = new ArrayList<>();
            for(int j = 0; j < gameConstants.hqSize; j++) {
                System.out.println("Headquarter " + j + "....\t(X Y)");
                cells.add(scanCell(tempHq, player));
                isValid = cellsAreBinding(cells, gameConstants.hqSize);
            }
        } while(!isValid);

        for(cell cell : cells) {
            unitCell tempUnitCell = new unitCell(cell, tempHq);
            tempHq.unitCells.add(tempUnitCell);
        }
        return tempHq;
    }

    //hamaro yeki kon:
    public static void scanUnit(unit unit, player player) {
        boolean isValid = true;
        ArrayList<cell> cells;

        do {
            cells = new ArrayList<>();
            for(int j = 0; j < unit.size; j++) {
                System.out.println(unit.type + j + "....\t(X Y)");
                cells.add(scanCell(unit, player));
                isValid = cellsAreBinding(cells, unit.size);
            }
        } while(!isValid);

        for(cell cell : cells) {
            unitCell tempUnitCell = new unitCell(cell, unit);
            unit.unitCells.add(tempUnitCell);
        }
        //return unit;
    }



    private static cell scanCell(unit tempUnit, player player) {
        int x, y;
        boolean flag;
        do {
            flag = true;
            x = scanner.nextInt();
            y = scanner.nextInt();
            scanner.nextLine();
            if(!(x >= 0 && x < player.getBoardSize() && y >= 0 && y < player.getBoardSize())) {
                System.out.println("Cell coordinates are out of boundary, enter again");
                flag = false;
            }
            if(isRepetitive(x, y, tempUnit, player)) {
                System.out.println("This cell is full!, enter again");
                flag = false;
            }
        } while (!flag);
        return new cell(x, y);
    }
    private static boolean isRepetitive(int x, int y, unit tempUnit, player player) {
        for(unit currUnit : player.getUnits()) {
            for(unitCell currUnitCell : currUnit.unitCells) {
                if(currUnitCell.hasEqualCell(new cell(x, y))) {
                    return true;
                }
            }
        }
        for(unitCell currUnitCell : tempUnit.unitCells) {
            if(currUnitCell.hasEqualCell(new cell(x, y))) {
                return true;
            }
        }
        return false;
    }

    //about start:
    //functions haye marboot be scan kardan: (to doooooooooooo : bebin shayad shabih ham peyda shod va toonesti hazf kone)
    public static unit scanUnitId(ArrayList<unit> units) {
        System.out.println("Enter id of unit...");
        //Check if input is valid:
        boolean isValid = false; int id;
        do {
            id = scanner.nextInt();
            scanner.nextLine();
            for(unit currUnit : units) {
                if (currUnit.id == id) {
                    isValid = true;
                    break;
                }
            }
            if(!isValid) { System.out.println("inValid id!"); }
        } while(!isValid);
        return getUnitById(units, id);
    }
    public static ArrayList<cell> scanTargetCells(unit unit, int boardSize) {
        boolean isValid;
        ArrayList<cell> result;
        do {
            isValid = true;
            result = new ArrayList<>();
            for(int i = 0; i < unit.range * unit.range; i++) {
                int x, y;
                do {
                    x = scanner.nextInt();
                    y = scanner.nextInt();
                    scanner.nextLine();
                    isValid = checkCellBoundary(x, y, boardSize);
                    //we dont check if we have stroke this x,y before, because of "move" feature
                } while(!isValid);
                result.add(new cell(x, y));
            }
            isValid = cellsAreBinding(result, unit.range);
        } while (!isValid);

        return result;
    }

    //scans about move:
    public static move scanMoveId(ArrayList<move> moves) {
        System.out.println("Enter id of move...");
        //Check if input is valid:
        boolean isValid = false; int id;
        do {
            id = scanner.nextInt();
            scanner.nextLine();
            for(move currMove : moves) {
                if (currMove.getId() == id) {
                    return currMove;
                }
            }
            System.out.println("inValid id!  enter again");
        } while(true);
    }

    //functions haye marboot be check kardan: (to doooooooooooo : bebin shayad shabih ham peyda shod va toonesti hazf kone)
    private static boolean checkCellBoundary(int x, int y, int boardSize) {
        if(!(x >= 0 && x < boardSize && y >= 0 && y < boardSize)) {
            System.out.println("Cell coordinates are out of boundary, enter again");
            return false;
        }
        return true;
    }
    private static boolean cellsAreBinding(ArrayList<cell> cells, int Y_axis) {
        cells.sort(new cellComparator());
        Y_axis = (int) Math.sqrt(Y_axis);
        for(cell cell : cells) {
            System.out.print(cell.getX() + ", " + cell.getY() + " || ");
        }
        System.out.println();

        for(int i = 0; i < cells.size()-1; i++) {
            if(cells.get(i).getX() == cells.get(i+1).getX()) {
                if(!(cells.get(i).getY() == cells.get(i+1).getY() - 1)) {
                    System.out.println("NOT BINDING!!!, enter again...");
                    return false;
                }
            }
            else if(cells.get(i).getY() == cells.get(i+1).getY() + Y_axis-1) {
                if(!(cells.get(i).getX() == cells.get(i+1).getX() - 1)) {
                    System.out.println("NOT BINDING!!!, enter again...");
                    return false;
                }
            }
            else {
                System.out.println("NOT BINDING!!!, enter again...");
                return false;
            }
        }
        return true;
    }


    ///function haye kolli:
    private static unit getUnitById(player player, int id) {
        for(unit currUnit : player.getUnits()) {
            if(currUnit.id == id) {
                return currUnit;
            }
        }
        System.out.println("inValid id Input! (in function getUnitById!");
        return null;
    }
    private static unit getUnitById(ArrayList<unit> units, int id) {
        for(unit currUnit : units) {
            if(currUnit.id == id) {
                return currUnit;
            }
        }
        System.out.println("inValid id Input! (in function getUnitById!");
        return null;
    }
    public static int stringUnSimilarity(String a, String b) {
        a = a.toLowerCase();
        b = b.toLowerCase();
        int result = 0;
        if(a.length() > b.length()) {
            String temp = b;
            b = a;
            a = temp;
        }
        for(int i = 0; i < a.length(); i++) {
            if(a.charAt(i) != b.charAt(i)) {
                result++;
            }
        }
        result += (b.length() - a.length());

        return result;
    }
}
