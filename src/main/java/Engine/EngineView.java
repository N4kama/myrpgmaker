package Engine;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

public class EngineView extends JPanel implements Observer, ActionListener {
    private EngineModel model_;

    public EngineView(EngineModel m) {
        this.model_ = m;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {

    }

    @Override
    public void update(Observable observable, Object o) {

    }
}
