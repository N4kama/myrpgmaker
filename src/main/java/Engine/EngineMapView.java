package Engine;

import Engine.Character.Animation;
import Engine.Character.EngineObj;
import MapPannel.MapModel;
import MapPannel.MapView;
import Utils.SpriteTools;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

public class EngineMapView extends MapView {
    private BufferedImage spritePlayer;
    public ArrayList<BufferedImage> walkingLeft;
    public ArrayList<BufferedImage> walkingRight;
    public ArrayList<BufferedImage> walkingUp;
    public ArrayList<BufferedImage> walkingDown;
    public Animation walkLeft;
    public Animation walkRight;
    public Animation walkUp;
    public Animation walkDown;
    public Animation standLeft;
    public Animation standRight;
    public Animation standUp;
    public Animation standDown;
    public Animation curAnim;


    public EngineMapView(MapModel mapModel) {
        super(mapModel);
        this.walkingLeft = new ArrayList<>();
        this.walkingRight = new ArrayList<>();
        this.walkingUp = new ArrayList<>();
        this.walkingDown = new ArrayList<>();
        mapModel.addObserver(this);
        EngineObj player = mapModel.getPlayer(mapModel.getObjects());
        loadPlayerMoves(player);
        walkLeft = new Animation(walkingLeft, 10);
        walkRight = new Animation(walkingRight, 10);
        walkUp = new Animation(walkingUp, 10);
        walkDown = new Animation(walkingDown, 10);

        ArrayList<BufferedImage> tmp = new ArrayList<>();
        tmp.add(SpriteTools.getStandingSprite(spritePlayer, 1, 0));
        standDown = new Animation(tmp, 10);

        tmp.remove(0);
        tmp.add(SpriteTools.getStandingSprite(spritePlayer, 1, 3));
        standUp = new Animation(tmp, 10);

        tmp.remove(0);
        tmp.add(SpriteTools.getStandingSprite(spritePlayer, 1, 1));
        standLeft = new Animation(tmp, 10);

        tmp.remove(0);
        tmp.add(SpriteTools.getStandingSprite(spritePlayer, 1, 2));
        standRight = new Animation(tmp, 10);

        curAnim = standDown;
    }

    private void loadPlayerMoves(EngineObj p) {
        String path = p.getSprite_();
        spritePlayer = SpriteTools.openObject(path.substring(0, path.length() - 4));
        SpriteTools.setSpriteMove(spritePlayer, walkingLeft, "left");
        SpriteTools.setSpriteMove(spritePlayer, walkingRight, "right");
        SpriteTools.setSpriteMove(spritePlayer, walkingUp, "up");
        SpriteTools.setSpriteMove(spritePlayer, walkingDown, "down");
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
            if (!obj.getIs_player()) {
                BufferedImage img = SpriteTools.openObject(obj.getSprite_());
                g.drawImage(img, obj.get_x() - img.getHeight() / 2, obj.get_y() - img.getWidth() / 2, null);
            } else {
                g.drawImage(curAnim.getSprite(), obj.get_x() - curAnim.getSprite().getHeight() / 2, obj.get_y() - curAnim.getSprite().getWidth() / 2, null);
            }
        }
    }
}
