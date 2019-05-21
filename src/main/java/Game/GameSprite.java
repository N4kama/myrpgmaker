package Game;

public abstract class GameSprite {
    public int x_pos;
    public int y_pos;
    public String path;

    public GameSprite(int x_pos, int y_pos, String path) {
        this.x_pos = x_pos;
        this.y_pos = y_pos;
        this.path = path;
    }

    public abstract boolean is_walkable();
}
