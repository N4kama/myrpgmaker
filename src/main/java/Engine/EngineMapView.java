package Engine;

import Engine.Character.EngineObj;
import MapPannel.MapModel;
import MapPannel.MapView;
import Utils.SpriteTools;

import java.awt.*;
import java.awt.image.BufferedImage;

public class EngineMapView extends MapView {
    private BufferedImage spritePlayer;
    public EngineMapView(MapModel mapModel) {
        super(mapModel);
    }

    @Override
    public void drayObjects(Graphics g) {
        for (EngineObj obj : mapModel.getObjects()) {
            if (!obj.getIs_player()) {
                BufferedImage img = SpriteTools.openObject(obj.getSprite_());
                g.drawImage(img, obj.get_y() - img.getHeight() / 2, obj.get_x() - img.getWidth() / 2, null);
            } else {
                String path = obj.getSprite_();
                spritePlayer = SpriteTools.openObject(path.substring(0, path.length() - 4));
                SpriteTools.setSpriteMove(spritePlayer, walkingLeft, "left");
                SpriteTools.setSpriteMove(spritePlayer, walkingRight, "right");
                SpriteTools.setSpriteMove(spritePlayer, walkingUp, "up");
                SpriteTools.setSpriteMove(spritePlayer, walkingDown, "down");
                g.drawImage(walkingDown.get(1), obj.get_y() - walkingLeft.get(0).getHeight() / 2, obj.get_x() - walkingLeft.get(0).getWidth() / 2, null);
            }
        }
    }
}
