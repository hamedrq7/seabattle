public class cell {
    private int x;
    private int y;
    private boolean collision;

    public cell (int x, int y) {
        this.x = x;
        this.y = y;
        this.collision = false;
    }



    public int getX() {
        return this.x;
    }
    public int getY() {
        return this.y;
    }

    public boolean getCollision() {return this.collision;}

    public void setCollision(boolean bool) {
        this.collision = bool;
    }

    public boolean equals(cell cell) {
        return this.x == cell.getX() && this.y == cell.getY();
    }

    //move:
    public void setCoordinates(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
