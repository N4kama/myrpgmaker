package Game;

public class GameObject extends GameSprite{

    public GameObject(int x_pos, int y_pos, String path) {
        super(x_pos, y_pos, path);
    }

    @Override
    public boolean is_walkable() {
        return false;
    }
}
