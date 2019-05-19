package Engine.Map;

import Engine.Position;

public class Tile {
    private Position p_;
    private TileType t_;
    private Boolean is_Walkable_;
    private String tile_img_;

    public Tile(int x, int y, TileType t, String img)
    {
        p_ = new Position(x, y);
        t_ = t;
        tile_img_ = img;
    }
    /**
     * @return the t_
     */
    public TileType getT_() {
        return t_;
    }

    /**
     * @return the p_
     */
    public Position getP_() {
        return p_;
    }

    public Boolean getIs_Walkable_() {
        return is_Walkable_;
    }

    public void setIs_Walkable_(Boolean is_Walkable_) {
        this.is_Walkable_ = is_Walkable_;
    }

    public String getForeGround_img_() {
        return tile_img_;
    }

    public void setForeGround_img_(String foreGround_img_) {
        this.tile_img_ = foreGround_img_;
    }

}