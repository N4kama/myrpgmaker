package SpritePannel;

import Utils.SpriteTools;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;

public class SpriteController {

    private SpriteView view;
    private SpriteModel model;


    public SpriteController(SpriteModel backgroundSpriteModel, SpriteView backgroundSpriteView) {
        this.model = backgroundSpriteModel;
        this.view = backgroundSpriteView;
    }

    public void start() {
        view.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Thread myThread = new Thread(() -> {
                    String path = detectSprite(e.getX(), e.getY());
                    if (path == null)
                        return;
                    if (SpriteTools.selectedSprite != null && SpriteTools.selectedSprite.equals(path)) {
                        SpriteTools.selectedSprite = null;
                    } else {
                        SpriteTools.is_background = model.is_background();
                        SpriteTools.playerModelSelected = model.isPlayer();
                        SpriteTools.selectedSprite = path;
                    }
                });
                myThread.start();
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
    }

    public String detectSprite(int x, int y) {
        int tmp_x = view.pad_x, tmp_y = view.pad_y;
        int max_y = 0;
        for (BufferedImage img : model.sprites) {
            if ((tmp_x < x && x < (tmp_x + img.getWidth()) && (tmp_y < y && y < (tmp_y + img.getHeight())))) {
                return SpriteTools.imgToPath.get(img);
            }
            tmp_x += img.getWidth() + view.pad_x;
            max_y = Math.max(max_y, img.getHeight() + view.pad_y);
            if (tmp_x >= view.getWidth()) {
                tmp_x = 0;
                tmp_y = max_y;
            }
        }
        return null;
    }

}
