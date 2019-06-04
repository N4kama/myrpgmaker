package MapPannel;

import Engine.Character.EngineObj;
import Game.Tile;
import Utils.SpriteTools;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Observable;
import java.util.Observer;

public class MapView extends JPanel implements Observer {

    public MapModel mapModel;

    public MapView(MapModel mapModel) {
        this.mapModel = mapModel;
        this.mapModel.addObserver(this);
        setBackground(Color.black);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        drawTiles(g);
        drayObjects(g);
    }

    private void drayObjects(Graphics g) {
        for (EngineObj obj : mapModel.getObjects()) {
            BufferedImage img = SpriteTools.openObject(obj.getSprite_());
            g.drawImage(img, obj.get_y() - img.getHeight() / 2, obj.get_x() - img.getWidth() / 2, null);
        }
    }

    private void drawTiles(Graphics g) {
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
    public void update(Observable o, Object arg) {
        if (arg.getClass() == Tile.class)
            paintComponent(this.getGraphics(), (Tile) arg);
        else {
            switch (SpriteTools.mousePointerState) {
                case DELETE:
                    deleteEngineOBJ(this.getGraphics(), (EngineObj)arg); break;
                default:
                    paintComponent(this.getGraphics(), (EngineObj) arg);
            }
        }
    }

    private void deleteEngineOBJ(Graphics g, EngineObj obj) {
        BufferedImage img = SpriteTools.pathToImg.get(obj.getSprite_());
        int x_min = (obj.get_x() - img.getWidth()) / 16;
        int x_max = (obj.get_x() + img.getWidth()) / 16;
        int y_min = (obj.get_x() - img.getHeight()) / 16;
        int y_max = (obj.get_x() + img.getHeight()) / 16;
        for (; x_min <= x_max; x_min += 16) {
            for (; y_min <= y_max; y_min += 16) {
                Tile tile = mapModel.map.getGameTile(x_min, y_min);
                if (tile != null)
                    g.drawImage(img, tile.get_x() * 16, tile.get_y() * 16, null);
            }
        }
    }

    public void paintComponent(Graphics g, Tile tile) {
        BufferedImage img = SpriteTools.openTile(tile.get_path());
        g.drawImage(img, tile.get_x() * 16, tile.get_y() * 16, null);
    }

    public void paintComponent(Graphics g, EngineObj obj) {
        BufferedImage img = SpriteTools.openObject(obj.getSprite_());
        g.drawImage(img, obj.get_x() - img.getWidth() / 2, obj.get_y() - img.getHeight() / 2, null);
    }
}
