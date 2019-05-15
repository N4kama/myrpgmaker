package Engine;

public enum Direction {
    LEFT (0),
    RIGHT (0),
    UP (0),
    DOWN (0);

    Direction(int dir)
    {
        setDir_(dir);
    }

    /**
     * @return the dir_
     */
    public int getDir_() {
        return dir_;
    }

    /**
     * @param dir_ the dir_ to set
     */
    public void setDir_(int dir_) {
        this.dir_ = dir_;
    }

    private int dir_;
}