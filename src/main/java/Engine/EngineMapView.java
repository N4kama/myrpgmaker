package Engine;

import Engine.Character.Animation;
import Engine.Character.EngineObj;
import MapPannel.MapModel;
import MapPannel.MapView;
import Utils.SpriteTools;
import Utils.WorldTools;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

public class EngineMapView extends MapView {
    
    public EngineMapView(MapModel mapModel) {
        super(mapModel);
        mapModel.addObserver(this);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        drawTiles(g);
        drayObjects(g);
    }

    @Override
    public void drayObjects(Graphics g) {
        for (EngineObj obj : mapModel.getObjects()) {
            if (!obj.getAlive()) {
                BufferedImage img = SpriteTools.openObject(obj.getSprite_());
                g.drawImage(img, 16 * (obj.get_x()), 16 * (obj.get_y()), null);
            } else {
                g.drawImage(obj.getEs().getCurAnim().getSprite(), 16 * (obj.get_x()), 16 * (obj.get_y()), null);
            }
        }
    }

}