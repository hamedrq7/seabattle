import java.util.ArrayList;

public class unitCell {
    private cell cell;
    private String symbol;
    public String getSymbol() {return this.symbol;}
    private boolean unTouched; // true--> this unitCell have not got hit
                               // false--> this unitCell have got hit

    public void setUnTouched(boolean bool) {
        this.unTouched = bool;
        this.symbol = "D";
    }
    public boolean getUnTouched() {return this.unTouched;}

    public unitCell(cell cell, unit unit) {
        this.cell = cell;
        this.symbol = unit.symbol;
        this.unTouched = true;
    }

    public cell getCell() {
        return this.cell;
    }

    public boolean hasEqualCell(cell cell) {
        return this.cell.equals(cell);
    }

    public int getX() {return this.cell.getX();}
    public int getY() {return this.cell.getY();}


}
