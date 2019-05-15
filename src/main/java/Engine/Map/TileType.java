package Engine.Map;

public enum TileType {
    WATER (0),
    GROUND (1);

    TileType(int type)
    {
        setType_(type_);
    }

    private int type_;

    public int getType_() {
        return type_;
    }

    public void setType_(int type_) {
        this.type_ = type_;
    }

}