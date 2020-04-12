import java.util.ArrayList;

public class board {
    private int size;
    private ArrayList<cell> shots;
    private ArrayList<unitCell> unitCells;

    public board(int size) {
        this.size = size;
    }

    public void show(player player) {

        for(int i = 0; i < this.size; i++) {

            for(int j = 0; j < this.size; j++) {

                System.out.print("| ");

                boolean isFull = false;
                for(unit unit: player.getUnits()) {

                    for(unitCell cell: unit.unitCells) {

                        if(cell.getX() == i && cell.getY() == j) {

                            //print unit symbol
                            System.out.print(cell.getSymbol());
                            isFull = true;
                        }
                    }
                }

                if(!isFull) {

                    System.out.print(" ");

                }

                System.out.print(" ");

            }

            System.out.println();
        }
    }


    public void showStrikes(ArrayList<cell> strikes){

        for(int i = 0; i < this.size; i++) {

            for(int j = 0; j < this.size; j++) {

                System.out.print("| ");

                boolean isFull = false;
                for(cell cell : strikes) {

                    if(cell.getX() == i && cell.getY() == j) {
                        isFull = true;
                        if(cell.getCollision()) {
                            System.out.print("X");
                        }
                        else{
                            System.out.print("O");
                        }
                    }
                }

                if(!isFull) {

                    System.out.print(" ");

                }

                System.out.print(" ");

            }

            System.out.println();
        }
    }
}
