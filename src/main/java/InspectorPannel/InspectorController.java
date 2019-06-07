package InspectorPannel;

import Engine.Direction;
import Engine.Position;
import Engine.Event.MoveEvent;

import java.awt.event.ActionListener;

public class InspectorController {
    private InspectorModel model;
    private InspectorView view;

    public InspectorController(InspectorModel model, InspectorView view) {
        this.model = model;
        this.view = view;
        set_controls();
    }

    public void set_controls() {
        view.calbtn.addActionListener(get_new_dialog());
        view.telbtn.addActionListener(get_new_teleport());
        view.moveList.addActionListener(get_new_move());
    }

    private ActionListener get_new_dialog() {
        return actionEvent -> {
            String data = view.dialog.getText();
            if (model.obj != null)
                model.obj.setDialog(data);
        };
    }

    private ActionListener get_new_teleport() {
        return actionEvent -> {
            Position data;
            if (view.get_telx.getText().equals("") || view.get_tely.getText().equals(""))
                data = null;
            else {
                data = new Position(Integer.parseInt((view.get_telx.getText())),
                        Integer.parseInt((view.get_tely.getText())));
            }
            if (model.obj != null) {
                model.obj.setTeleportePos(data);
            }

        };
    }

    private ActionListener get_new_move() {
        return actionEvent -> {
            String data = view.moveList.getSelectedItem().toString();
            if (data == null)
                return;
            Direction d = Direction.DOWN;
            switch (data) {
            case "up":
                d = Direction.UP;
                break;
            case "left":
                d = Direction.LEFT;
                break;
            case "right":
                d = Direction.RIGHT;
                break;
            case "down":
                d = Direction.DOWN;
                break;
            case "random":
                d = null;
                break;
            default:
                return;
            }
            model.obj.add_event(new MoveEvent(model.obj, d, model.w));
        };
    }
}
