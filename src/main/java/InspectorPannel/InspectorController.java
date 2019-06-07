package InspectorPannel;

import Engine.Position;

import java.awt.event.ActionListener;

public class InspectorController {
    private InspectorModel model;
    private InspectorView view;

    public InspectorController(InspectorModel model, InspectorView view)
    {
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
            if (InspectorModel.obj != null)
                InspectorModel.obj.setDialog(data);
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
            if (InspectorModel.obj != null)
                InspectorModel.obj.setTeleportedPos(data);

        };
    }

    private ActionListener get_new_move() {
        return actionEvent -> {
            String data = view.moveList.getSelectedItem().toString();
            System.out.println(data);
        };
    }
}
