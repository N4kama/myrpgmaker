package InspectorPannel;

import javax.swing.*;
import java.awt.*;


public class InspectorView extends JPanel {
    JLabel namet;
    JLabel typet;
    JLabel post;
    JLabel old;
    JTextField dialog;
    JButton calbtn = new JButton("Save new dialog !");
    public InspectorModel model;

    public InspectorView(InspectorModel model) {
        this.model = model;
        setText();

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

    }

    public void setText(){
        namet = new JLabel(model.namet);
        typet = new JLabel(model.typet);
        post = new JLabel(model.post);
        old = new JLabel(model.old);

    }


}