package Engine;

public class Position {
    public Position(int x, int y) {
        setX(x);
        setY(y);
    }

    private Integer x_;
    private Integer y_;

    public void move(Direction dir) {
        switch (dir) {
        case UP:
            moveUp();
            break;
        case DOWN:
            moveDown();
            break;
        case LEFT:
            moveLeft();
            break;
        case RIGHT:
            moveRight();
            break;
        default:
            break;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }

        if (!(o instanceof Position)) {
            return false;
        }

        Position c = (Position) o;
        return c.getX() == x_ && c.getY() == y_;
    }

    public Position tempPos(Direction dir) {
        Position p = new Position(getX(), getY());
        p.move(dir);
        return p;
    }

    private void moveLeft() {
        setX(x_ - 1);
    }

    private void moveRight() {
        setX(x_ + 1);
    }

    private void moveUp() {
        setY(y_ - 1);
    }

    private void moveDown() {
        setY(y_ + 1);
    }

    public Integer getX() {
        return x_;
    }

    public void setX(Integer x_) {
        this.x_ = x_;
    }

    public Integer getY() {
        return y_;
    }

    public void setY(Integer y_) {
        this.y_ = y_;
    }

}