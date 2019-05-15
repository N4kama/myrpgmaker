package Editor;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.filechooser.FileSystemView;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;

public class EditorView extends JFrame {
    JMenuItem file_new;
    JMenuItem file_open;
    JMenuItem file_save;
    JMenuItem file_exit;
    JMenuItem edit_undo;
    JMenuItem edit_redo;
    JButton newButton;
    JButton openButton;
    JButton saveButton;
    JButton moveButton;
    JButton selectButton;
    JButton removeButton;
    JButton undoButton;
    JButton redoButton;
    JFrame frame;

    public JFrame getMainFrame() {
        return frame;
    }

    public JMenuItem getNew_menuItem() {
        return file_new;
    }

    public JMenuItem getOpen_menuItem() {
        return file_open;
    }

    public JMenuItem getSave_menuItem() {
        return file_save;
    }

    public JMenuItem getExit_menuItem() {
        return file_exit;
    }

    public JMenuItem getEditUndo_menuItem() {
        return edit_undo;
    }

    public JMenuItem getEditRedo_menuItem() {
        return edit_redo;
    }

    public JButton getNewButton() {
        return newButton;
    }

    public JButton getOpenButton() {
        return openButton;
    }

    public JButton getSaveButton() {
        return saveButton;
    }

    public JButton getMoveButton() {
        return moveButton;
    }

    public JButton getSelectButton() {
        return selectButton;
    }

    public JButton getRemoveButton() {
        return removeButton;
    }

    public JButton getUndoButton() {
        return undoButton;
    }

    public JButton getRedoButton() {
        return redoButton;
    }

    public EditorView() {
        //Creating Frame
        frame = new JFrame("My RPG Maker");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 600);

        //Creating Menu bar
        JMenuBar menuBar = create_menu_bar();
        //Creating Tool bar
        JToolBar toolBar = create_tool_bar();

        //Adding every component to the frame
        frame.setJMenuBar(menuBar);
        frame.add(toolBar, BorderLayout.NORTH);
        frame.setVisible(true);
    }

    private JToolBar create_tool_bar() {
        JToolBar toolBar = new JToolBar();
        toolBar.setFloatable(false);
        ImageIcon new_I = get_icon("new.png");
        newButton = new JButton(new_I);
        toolBar.add(newButton);
        ImageIcon open_I = get_icon("open.png");
        openButton = new JButton(open_I);
        toolBar.add(openButton);
        ImageIcon save_i = get_icon("save.png");
        saveButton = new JButton(save_i);
        toolBar.add(saveButton);
        ImageIcon move_I = get_icon("move.png");
        moveButton = new JButton(move_I);
        toolBar.add(moveButton);
        ImageIcon select_I = get_icon("select.png");
        selectButton = new JButton(select_I);
        toolBar.add(selectButton);
        ImageIcon remove_I = get_icon("remove.png");
        removeButton = new JButton(remove_I);
        toolBar.add(removeButton);
        ImageIcon undo_I = get_icon("undo.png");
        undoButton = new JButton(undo_I);
        toolBar.add(undoButton);
        ImageIcon redo_I = get_icon("redo.png");
        redoButton = new JButton(redo_I);
        toolBar.add(redoButton);

        return toolBar;
    }

    private ImageIcon get_icon(String file) {
        return new ImageIcon(new ImageIcon(System.getProperty("user.dir") + "/resources/icons/" + file)
                .getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH));
    }

    private JMenuBar create_menu_bar() {
        JMenuBar menuBar = new JMenuBar();
        JMenu file = new JMenu("File");
        JMenu edit = new JMenu("Edit");
        menuBar.add(file);
        menuBar.add(edit);

        //Creating Menu Items
        //NEW//
        file_new = new JMenuItem("new");
        file_new.setMnemonic(KeyEvent.VK_M);
        file_new.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, Event.CTRL_MASK, true));
        file_new.setEnabled(true);
        file.add(file_new);
        //OPEN//
        file_open = new JMenuItem("open");
        file_open.setMnemonic(KeyEvent.VK_M);
        file_open.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, Event.CTRL_MASK, true));
        file_open.setEnabled(true);
        file.add(file_open);
        //SAVE//
        file_save = new JMenuItem("save");
        file_save.setMnemonic(KeyEvent.VK_M);
        file_save.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, Event.CTRL_MASK, true));
        file_save.setEnabled(true);
        file.add(file_save);
        //EXIT//
        file_exit = new JMenuItem("exit");
        file_exit.setEnabled(true);
        file.add(file_exit);
        //UNDO//
        edit_undo = new JMenuItem("undo");
        edit_undo.setMnemonic(KeyEvent.VK_M);
        edit_undo.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Z, Event.CTRL_MASK, true));
        edit_undo.setEnabled(true);
        edit.add(edit_undo);
        //REDO//
        edit_redo = new JMenuItem("redo");
        edit_redo.setMnemonic(KeyEvent.VK_M);
        edit_redo.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_U, Event.CTRL_MASK, true));
        edit_redo.setEnabled(true);
        edit.add(edit_redo);

        return menuBar;
    }
}
