package Game;

import Engine.Position;

import java.util.List;

public class Object {
    private String object_img_;
    private Position p_;

    public Object(String object_img_, Position p_) {
        this.object_img_ = object_img_;
        this.p_ = p_;
    }

    public String getObject_img_() {
        return object_img_;
    }

    public void setObject_img_(String object_img_) {
        this.object_img_ = object_img_;
    }

    public Position getP_() {
        return p_;
    }

    public void setP_(Position p_) {
        this.p_ = p_;
    }
}
