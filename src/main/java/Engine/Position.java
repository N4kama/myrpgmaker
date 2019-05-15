package Engine;

public class Position {
    public Position(int x, int y) {
        setX_(x);
        setY_(y);
    }

    private Integer x_;
    private Integer y_;

    /**
     * @return Integer return the x_
     */
    public Integer getX_() {
        return x_;
    }

    /**
     * @param x_ the x_ to set
     */
    public void setX_(Integer x_) {
        this.x_ = x_;
    }

    /**
     * @return Integer return the y_
     */
    public Integer getY_() {
        return y_;
    }

    /**
     * @param y_ the y_ to set
     */
    public void setY_(Integer y_) {
        this.y_ = y_;
    }

}