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
    JLabel teleport;

    JTextField get_telx;
    JTextField get_tely;
    JTextField dialog;
    JButton telbtn = new JButton("Save new teleport position !");

    JButton calbtn = new JButton("Save new dialog !");
    public InspectorModel model;

    public InspectorView(InspectorModel model) {
        this.model = model;
        namet = new JLabel(model.namet);
        typet = new JLabel(model.typet);
        post = new JLabel(model.post);
        old = new JLabel(model.old);
        teleport = new JLabel(model.teleport);

        JLabel tel_t = new JLabel("Set the teleport parameters:");
        get_telx = new JTextField(3);
        get_tely = new JTextField(3);

        JLabel label = new JLabel("Set the dialog parameters:", SwingConstants.LEFT);
        dialog = new JTextField(30);

        add(namet);
        add(Box.createRigidArea(new Dimension(1, 5)));
        add(typet);
        add(Box.createRigidArea(new Dimension(1, 5)));
        add(post);
        add(Box.createRigidArea(new Dimension(1, 5)));
        add(teleport);
        add(Box.createRigidArea(new Dimension(1, 5)));
        add(old);
        add(Box.createRigidArea(new Dimension(1, 5)));
        add(tel_t);
        add(get_telx);
        add(get_tely);
        add(telbtn);
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
        teleport.setText(model.teleport);
    }


    @Override
    public void update(Observable o, Object arg) {
        setText();
        this.updateUI();
    }
}