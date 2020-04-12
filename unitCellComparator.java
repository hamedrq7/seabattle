import java.util.Comparator;

class unitCellComparator implements Comparator<unitCell> {

    @Override
    public int compare(unitCell uc1, unitCell uc2) {
        // 0 for ==
        // 1 for o1>o2
        //-1 for o1<o2
        cell o1 = uc1.getCell();
        cell o2 = uc2.getCell();
        if(o1.getX() < o2.getX()) {
            return 1;
        }
        else if(o1.getX() > o2.getX()) {
            return -1;
        }
        else {
            if(o1.getY() < o2.getY()) {
                return 1;
            }
            else if(o1.getY() > o2.getY()) {
                return -1;
            }
            else {
                return 0;
            }
        }

    }
}
