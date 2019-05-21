package Game;

import Engine.Position;

public class Tile {
    private Position pos_;
    private Boolean is_Walkable_;
    private String tile_img_;

    public Tile(int x, int y, String img)
    {
        pos_ = new Position(x, y);
        tile_img_ = img;
    }

    /**
     * @return the p_
     */
    public Position getPos() {
        return pos_;
    }

    public Boolean getIs_Walkable() {
        return is_Walkable_;
    }

    public void setIs_Walkable(Boolean is_Walkable_) {
        this.is_Walkable_ = is_Walkable_;
    }

    public String getForeGround_img() {
        return tile_img_;
    }

    public void setForeGround_img(String foreGround_img_) {
        this.tile_img_ = foreGround_img_;
    }

}