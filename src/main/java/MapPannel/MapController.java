package MapPannel;

import Engine.Character.EngineObj;
import Utils.SpriteTools;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MapController {
    private MapModel model;
    private MapView view;

    public MapController(MapModel mapModel, MapView mapView) {
        this.model = mapModel;
        this.view = mapView;
    }

    public void start() {
        view.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
            }

            @Override
            public void mousePressed(MouseEvent e) {
                Thread myThread = new Thread(() -> executeUserAction(e));
                myThread.start();
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
        view.addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                super.mouseDragged(e);
                Thread myThread = new Thread(() -> executeUserAction(e));
                myThread.start();
            }
        });

    }
    public void executeUserAction(MouseEvent e) {
        switch (SpriteTools.mousePointerState) {
            case PLACE:
                model.modifySprite(e.getX(), e.getY());
                break;
            case MOVE:
                model.moveSpite(e.getX(), e.getY());
                break;
            case SELECT:
                System.out.println("SELECT MODE");
                break;
            case DELETE:
                model.deleteSprite(e.getX(), e.getY());
                break;
            case SET_WALKABLE_OR_NOT:
                model.setWalkable(e.getX(), e.getY());
        }
    }
}
