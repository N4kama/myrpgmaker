package InspectorPannel;

import Engine.Direction;
import Engine.Position;
import Engine.Event.ChangeMapEvent;
import Engine.Event.MoveEvent;
import Engine.Event.TeleportEvent;

import java.awt.event.ActionListener;

import Editor.EditorModel;

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
        view.namebtn.addActionListener(get_new_name());
    }

    private ActionListener get_new_name() {
        return actionEvent -> {
            String data = view.dialog.getText();
            if (model.obj != null)
                model.obj.setName_(data);
        };
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
            int mapid = EditorModel.singleton.gameWorld.gameWorld_.indexOf(EditorModel.singleton.gameWorld.getCurMap());
            if (view.get_telx.getText().equals("") || view.get_tely.getText().equals(""))
                data = null;
            else {
                data = new Position(Integer.parseInt((view.get_telx.getText())),
                        Integer.parseInt((view.get_tely.getText())));
                        
                try {
                    mapid = Integer.parseInt(view.get_mapid.getText());
                } catch (Exception e) {
                    //TODO: handle exception
                }
            }
            if (model.obj != null) {
                model.obj.add_event(new ChangeMapEvent(model.obj, mapid, data, EditorModel.singleton.gameWorld));
            }

        };
    }

    private ActionListener get_new_move() {
        return actionEvent -> {
            String data = view.moveList.getSelectedItem().toString();
            System.out.println(data);
            if (data == null)
                return;
            Direction d = Direction.DOWN;
            switch (data) {
            case "Up":
                d = Direction.UP;
                break;
            case "Left":
                d = Direction.LEFT;
                break;
            case "Right":
                d = Direction.RIGHT;
                break;
            case "Down":
                d = Direction.DOWN;
                break;
            case "Random":
                d = null;
                break;
            default:
                return;
            }
            model.obj.add_event(new MoveEvent(model.obj, d, EditorModel.singleton.gameWorld));
        };
    }
}
