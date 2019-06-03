package Engine.Event;

import Engine.Vars;

public class Compare implements GameEvents {

    public Compare(int type, Vars l, Vars r)
    {
        setComparator(type);
        left = l;
        right = r;
    }

    @Override
    public boolean run() {
        return comp(left, right);
    }

    private boolean comp(Vars l, Vars r) {
        if (comparator == CompType.LOWER)
            return l.getValue() < r.getValue();
        if (comparator == CompType.EQUAL)
            return l.getValue() == r.getValue();
        if (comparator == CompType.GREATER)
            return l.getValue() > r.getValue();
        return false;
    }

    private Vars left;
    private Vars right;
    private CompType comparator;

    /**
     * @return Vars return the left
     */
    public Vars getLeft() {
        return left;
    }

    /**
     * @param left the left to set
     */
    public void setLeft(Vars left) {
        this.left = left;
    }

    /**
     * @return Vars return the right
     */
    public Vars getRight() {
        return right;
    }

    /**
     * @param right the right to set
     */
    public void setRight(Vars right) {
        this.right = right;
    }

    /**
     * @return CompType return the comparator
     */
    public CompType getComparator() {
        return comparator;
    }

    /**
     * @param type the comparator to set
     */
    public void setComparator(int type) {
        this.comparator.setComp(type);
    }

}