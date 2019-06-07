package Engine;

import Engine.Character.Animation;
import Engine.Character.EngineObj;
import Game.Tile;
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
                g.drawImage(obj.getEs().getCurAnim().getSprite(), 16 * (obj.get_x()), 16 * (obj.get_y()) - 8, null);
            }
        }
    }

    @Override
    public void drawTiles(Graphics g) {
        int x = 0, y = 0;
        for (int i = 0; i < mapModel.getWidth(); i++) {
            for (int j = 0; j < mapModel.getHeight(); j++) {
                String path = mapModel.map.getPathTile(i, j);
                BufferedImage img = SpriteTools.openTile(path);
                g.drawImage(img, y, x, null);
                x += 16;
            }
            x = 0;
            y += 16;
        }
    }

    @Override
    public void paintComponent(Graphics g, Tile tile) {
        BufferedImage img = SpriteTools.openTile(tile.get_path());
        g.drawImage(img, tile.get_x() * 16, tile.get_y() * 16, null);
    }
}