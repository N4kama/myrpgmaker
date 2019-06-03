package Engine.Event;

public enum CompType {
    LOWER (0), EQUAL(1), GREATER (2);
    CompType(int dir)
    {
        setComp(dir);
    }

    public int getComp() {
        return comp;
    }

    public void setComp(int comp) {
        this.comp = comp;
    }

    private int comp;
}