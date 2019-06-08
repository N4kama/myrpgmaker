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

import Editor.EditorModel;

public class EngineMapView extends MapView {

    private EngineView ev;
    private int w;
    private int h;

    public EngineMapView(MapModel mapModel, EngineView ev) {
        super(mapModel);
        mapModel.addObserver(this);
        this.ev = ev;
        w = ev.getWidth();
        h = ev.getHeight();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        drawTiles(g);
        drayObjects(g);
    }

    @Override
    public void drayObjects(Graphics g) {
        w = ev.getWidth();
        h = ev.getHeight();
        int px = WorldTools.player.get_x();
        int py = WorldTools.player.get_y();
        Position p = new Position(px - w / 16, py - h / 16);
        for (EngineObj obj : mapModel.getObjects()) {
            if (obj.getPosition_().is_in(p, w / 8, h / 8))
                if (!obj.getAlive()) {
                    BufferedImage img = SpriteTools.openObject(obj.getSprite_());
                    g.drawImage(img, w / 2 - 8 + 16 * (obj.get_x() - px), h / 2 - 8 + 16 * (obj.get_y() - py), null);
                } else if (obj.isIs_player()) {
                    g.drawImage(obj.getEs().getCurAnim().getSprite(), w / 2 - 8, h / 2 - 12, null);
                } else {
                    g.drawImage(obj.getEs().getCurAnim().getSprite(), w / 2 - 8 + 16 * (obj.get_x() - px),
                            h / 2 - 8 + 16 * (obj.get_y() - py), null);
                }
        }
        return;
    }

    @Override
    public void drawTiles(Graphics g) {
        w = ev.getWidth();
        h = ev.getHeight();
        int px = WorldTools.player.get_x();
        int py = WorldTools.player.get_y();
        int i = px - w / 32 - 1 >= 0 ? px - w / 32 - 1 : 0;
        int ifinal = px + h / 16 + 1 < mapModel.getWidth() ? px + h / 16 + 1 : mapModel.getWidth();
        int j = py - h / 32 - 1 >= 0 ? py - h / 32 - 1 : 0;
        int jfinal = py + h / 16 + 1 < mapModel.getHeight() ? py + h / 16 + 1 : mapModel.getHeight();
        for (; j < jfinal; j++) {
            for (; i < ifinal; i++) {
                String path = mapModel.map.getPathTile(i, j);
                if (path != null) {
                    BufferedImage img = SpriteTools.openTile(path);
                    g.drawImage(img, 16 * (i - px) + w / 2 - 8, 16 * (j - py) + h / 2 - 4, null);
                }
            }
            i = px - w / 32 >= 0 ? px - w / 32 : 0;
        }
        return;
    }

    @Override
    public void paintComponent(Graphics g, Tile tile) {
        BufferedImage img = SpriteTools.openTile(tile.get_path());
        g.drawImage(img, tile.get_x() * 16, tile.get_y() * 16, null);
    }

}