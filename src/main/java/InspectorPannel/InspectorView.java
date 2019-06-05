package InspectorPannel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class InspectorView extends JPanel implements ActionListener {
    JTextField txtdata;
    JButton calbtn = new JButton("Save new dialog !");
    public InspectorModel model;

    public InspectorView(InspectorModel model) {
        this.model = model;
        JLabel namet = new JLabel("Name: Zacky");
        JLabel typet = new JLabel("Type: NPC");
        JLabel post = new JLabel("Position: 10.5   8.5");
        JLabel label = new JLabel("Set the dialog parameters:", SwingConstants.LEFT);
        label.setFont(new Font("Courier New", Font.BOLD, 16));
        txtdata = new JTextField(30);
        add(namet);
        add(Box.createRigidArea(new Dimension(1, 5)));
        add(typet);
        add(Box.createRigidArea(new Dimension(1, 5)));
        add(post);
        add(Box.createRigidArea(new Dimension(1, 5)));
        add(label);
        add(Box.createRigidArea(new Dimension(1, 5)));
        add(txtdata);
        add(calbtn);
        calbtn.addActionListener(this);
        BoxLayout boxLayout = new BoxLayout(this, BoxLayout.Y_AXIS);
        setLayout(boxLayout);

    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == calbtn) {
            String data = txtdata.getText();
            System.out.println(data);
        }
    }
}