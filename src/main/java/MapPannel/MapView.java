package MapPannel;

import Engine.Character.EngineObj;
import Game.Tile;
import Utils.SpriteTools;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
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
        if (SpriteTools.grid_display)
            drawGrid(g);
        drayObjects(g);
    }

    private void drawGrid(Graphics g) {
        Color savedColor = g.getColor();
        g.setColor(new Color(0, 0, 0, 200));
        for (int x = 0; x < mapModel.getHeight() * 16; x += 16) {
            g.drawLine(0, x, mapModel.getWidth() * 16, x);
        }
        for (int x = 0; x < mapModel.getWidth() * 16; x += 16) {
            g.drawLine(x, 0, x, mapModel.getHeight() * 16);
        }
        g.setColor(savedColor);
    }

    public void drayObjects(Graphics g) {
        for (EngineObj obj : mapModel.getObjects()) {
            BufferedImage img = SpriteTools.openObject(obj.getSprite_());
            g.drawImage(img, obj.get_y() * 16, obj.get_x() * 16, null);
        }
    }

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
    public void update(Observable o, Object arg) {
        if (arg.getClass() == Tile.class) {
            switch (SpriteTools.mousePointerState) {
            case MOVE:
                if (mapModel.is_moving)
                    paintComponent(this.getGraphics(), (Tile) arg);
                else
                    paintComponent(this.getGraphics(), (Tile) arg);
                break;
            default:
                paintComponent(this.getGraphics(), (Tile) arg);
            }
        } else if (arg.getClass() == EngineObj.class) {
            switch (SpriteTools.mousePointerState) {
            case DELETE:
                deleteEngineOBJ(this.getGraphics(), (EngineObj) arg);
                break;
            case MOVE:
                if (mapModel.is_moving)
                    deleteEngineOBJ(this.getGraphics(), (EngineObj) arg);
                else
                    paintComponent(this.getGraphics(), (EngineObj) arg);
                break;
            default:
                paintComponent(this.getGraphics(), (EngineObj) arg);
            }
        } else if (arg.getClass() == String.class && ((String) arg).equals("toggleGrid")) {
            if (SpriteTools.grid_display)
                drawGrid(getGraphics());
            else {
                revalidate();
                repaint();
            }
        }
    }

    private void paintTile(Graphics g, int x, int y, String tile_path) {
        BufferedImage img = SpriteTools.openTile(tile_path);
        g.drawImage(img, x * 16, y * 16, null);
    }

    private void deleteEngineOBJ(Graphics g, EngineObj obj) {
        BufferedImage img = SpriteTools.pathToImg.get(obj.getSprite_());
        int x_min = obj.get_x();
        int x_max = obj.get_x() + img.getWidth() / 16 - 1;
        int y_min = obj.get_x();
        int y_max = obj.get_x() + img.getHeight() / 16 - 1;
        for (int x = x_min; x <= x_max; x++) {
            for (int y = y_min; y <= y_max; y++) {
                Tile tile = mapModel.map.getGameTile(x, y);
                if (tile != null)
                    g.drawImage(SpriteTools.pathToImg.get(tile.get_path()), tile.get_x() * 16, tile.get_y() * 16, null);
            }
        }
    }

    public void paintComponent(Graphics g, Tile tile) {
        BufferedImage img = SpriteTools.openTile(tile.get_path());
        g.drawImage(img, tile.get_x() * 16, tile.get_y() * 16, null);
    }

    public void paintComponent(Graphics g, EngineObj obj) {
        BufferedImage img = SpriteTools.openObject(obj.getSprite_());
        if (obj.isAlive())
            img = img.getSubimage(32, 0, 32, 32);
        g.drawImage(img, obj.get_x() * 16, obj.get_y() * 16, null);
    }

}
