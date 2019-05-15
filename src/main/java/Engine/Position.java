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

    private void moveLeft() {
        setX(x_ - 1);
        ;
    }

    private void moveRight() {
        setX(x_ + 1);
        ;
    }

    private void moveUp() {
        setY(y_ - 1);
        ;
    }

    private void moveDown() {
        setY(y_ + 1);
        ;
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