package Editor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

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

        //Creating Menu Items
        //NEW//
        JMenuItem file_new = new JMenuItem("new");
        file_new.setMnemonic(KeyEvent.VK_M);
        file_new.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, Event.CTRL_MASK, true));
        file_new.setEnabled(true);
        file.add(file_new);
        //OPEN//
        JMenuItem file_open = new JMenuItem("open");
        file_open.setMnemonic(KeyEvent.VK_M);
        file_open.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, Event.CTRL_MASK, true));
        file_open.setEnabled(true);
        file.add(file_open);
        //SAVE//
        JMenuItem file_save = new JMenuItem("save");
        file_save.setMnemonic(KeyEvent.VK_M);
        file_save.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, Event.CTRL_MASK, true));
        file_save.setEnabled(true);
        file.add(file_save);
        //EXIT//
        JMenuItem file_exit = new JMenuItem("exit");
        file_exit.setEnabled(true);
        file.add(file_exit);
        //UNDO//
        JMenuItem edit_undo = new JMenuItem("undo");
        edit_undo.setMnemonic(KeyEvent.VK_M);
        edit_undo.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Z, Event.CTRL_MASK, true));
        edit_undo.setEnabled(true);
        edit.add(edit_undo);
        //REDO//
        JMenuItem edit_redo = new JMenuItem("redo");
        edit_redo.setMnemonic(KeyEvent.VK_M);
        edit_redo.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_U, Event.CTRL_MASK, true));
        edit_redo.setEnabled(true);
        edit.add(edit_redo);

        //Misc
        JButton button1 = new JButton("testButton");

        //Adding every component to the frame
        frame.getContentPane().add(BorderLayout.NORTH, menuBar);
        frame.getContentPane().add(BorderLayout.WEST, button1);
        frame.setVisible(true);
    }
}
