package Engine;

public enum Direction {
    LEFT (0),
    RIGHT (1),
    UP (2),
    DOWN (3);

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