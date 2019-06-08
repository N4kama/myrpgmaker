package Engine.Event;

import java.awt.image.BufferedImage;

import Engine.Direction;
import Engine.Position;
import Engine.Character.EngineObj;
import Game.Map;
import Game.World;
import Utils.SpriteTools;

public class ChangeMapEvent implements GameEvents {

    public ChangeMapEvent(EngineObj c, int id_map, World m) {
        this.c = c;
        this.p = m.getMap(id_map);
        m.changeMap(id_map);
        this.m = m;
        BufferedImage img = SpriteTools.openObject(c.getSprite_());
        this.w = img.getWidth() / 16;
        this.h = img.getHeight() / 16;
    }

    @Override
    public boolean run() {
        System.out.println(c.get_x() + ":" + c.get_y());
        EngineObj e = m.player_;
        if (e.isIs_player() && e.getPosition_().is_in(c.getPosition_(), w, h)) {
            e.setChangedMap(true);
            return true;
        }
        return false;
    }

    private EngineObj c;
    private Map p;
    private World m;
    private int h;
    private int w;
}