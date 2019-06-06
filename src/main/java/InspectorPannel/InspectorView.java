package InspectorPannel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class InspectorView extends JPanel  {
    JLabel namet;
    JLabel typet;
    JLabel post;
    JLabel old;
    JTextField dialog;
    JButton calbtn = new JButton("Save new dialog !");
    public InspectorModel model;

    public InspectorView(InspectorModel model) {
        this.model = model;
        if (InspectorModel.obj != null) {
            namet = new JLabel("Name: " + InspectorModel.obj.getName_());

            if (!InspectorModel.obj.getIs_player())
                typet = new JLabel("Type: NPC");
            else
                typet = new JLabel("Type: Player");
            post = new JLabel("Position: " + InspectorModel.obj.getPosition_());
            if (InspectorModel.obj.getDialog() != null)
                old = new JLabel("Current dialog: " + InspectorModel.obj.getDialog());
            else
                old = new JLabel("Current dialog: ");
        }
        else
        {
            namet = new JLabel("Name: ");
            typet = new JLabel("Type: ");
            post = new JLabel("Position: ");
            old = new JLabel("Current dialog: ");
        }


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
}