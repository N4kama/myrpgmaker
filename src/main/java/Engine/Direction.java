package Engine;

public enum Direction {
    DOWN (0),
    LEFT (1),
    RIGHT (2),
    UP (3);

    Direction(int dir)
    {
        setDir_(dir);
    }

    public int getDir_() {
        return dir_;
    }

    public void setDir_(int dir_) {
        this.dir_ = dir_;
    }

    private int dir_;
}