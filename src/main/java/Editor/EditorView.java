package Editor;

import javax.swing.*;
import java.awt.*;

public class EditorView {
    public EditorView() {
        //Creating Frame
        JFrame frame = new JFrame("My RPG Maker");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300,300);

        //Creating Menu bar
        JMenuBar menuBar = new JMenuBar();
        JMenu file = new JMenu("File");
        JMenu edit = new JMenu("Edit");
        menuBar.add(file);
        menuBar.add(edit);

        //Misc
        JButton button1 = new JButton("testButton");

        //Adding every component to the frame
        frame.getContentPane().add(BorderLayout.NORTH, menuBar);
        frame.getContentPane().add(BorderLayout.WEST, button1);
        frame.setVisible(true);
    }
}
