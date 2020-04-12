public class move {
    private int X_axis_scale;
    private int Y_axis_scale;
    private int id;

    public int getId() {return this.id;}
    public int getX_axis_scale() {return this.X_axis_scale;}
    public int getY_axis_scale() {return this.Y_axis_scale;}

    public move(int x, int y, int id) {
        this.X_axis_scale = x;
        this.Y_axis_scale = y;
        this.id = id;
    }
}
