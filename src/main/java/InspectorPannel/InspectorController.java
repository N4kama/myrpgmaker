package InspectorPannel;

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
    }

    private ActionListener get_new_dialog() {
        return actionEvent -> {
            String data = view.dialog.getText();
            if (InspectorModel.obj != null)
                InspectorModel.obj.setDialog(data);
        };
    }
}
