import java.util.Comparator;

public class cellComparator implements Comparator<cell>{

    @Override
    public int compare(cell o1, cell o2) {
        // 0 for ==
        // 1 for o1>o2
        //-1 for o1<o2
        if(o1.getX() < o2.getX()) {
            return -1;
        }
        else if(o1.getX() > o2.getX()) {
            return +1;
        }
        else {
            if(o1.getY() < o2.getY()) {
                return -1;
            }
            else if(o1.getY() > o2.getY()) {
                return +1;
            }
            else {
                return 0;
            }
        }

    }
}
