package InspectorPannel;

import Engine.Character.EngineObj;

import javax.swing.*;
import java.awt.*;
import java.util.Observable;
import java.util.Observer;


public class InspectorView extends JPanel  implements Observer {
    JLabel namet;
    JLabel typet;
    JLabel post;
    JLabel old;
    JTextField dialog;
    JButton calbtn = new JButton("Save new dialog !");
    public InspectorModel model;

    public InspectorView(InspectorModel model) {
        this.model = model;
        namet = new JLabel(model.namet);
        typet = new JLabel(model.typet);
        post = new JLabel(model.post);
        old = new JLabel(model.old);

        JLabel label = new JLabel("Set the dialog parameters:", SwingConstants.LEFT);
        dialog = new JTextField(30);

        add(namet);
        add(Box.createRigidArea(new Dimension(1, 5)));
        add(typet);
        add(Box.createRigidArea(new Dimension(1, 5)));
        add(post);
        add(Box.createRigidArea(new Dimension(1, 5)));
        add(old);
        add(Box.createRigidArea(new Dimension(1, 5)));
        add(label);
        add(Box.createRigidArea(new Dimension(1, 5)));
        add(dialog);
        add(calbtn);
        BoxLayout boxLayout = new BoxLayout(this, BoxLayout.Y_AXIS);
        setLayout(boxLayout);
        model.addObserver(this);
    }

    public void setText(){
        namet.setText(model.namet);
        typet.setText(model.typet);
        post.setText(model.post);
        old.setText(model.old);

    }


    @Override
    public void update(Observable o, Object arg) {
        setText();
        this.updateUI();
    }
}