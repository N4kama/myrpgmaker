package Editor;

import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class EditorController {
    private EditorModel model;
    private EditorView view;

    public EditorController(EditorModel model, EditorView v) {
        this.model = model;
        this.view = v;
        set_controls();
    }

    public void set_controls() {

        //set listener for menu item
        view.file_open.addActionListener(open_action());
        view.file_save.addActionListener(save_action());
        view.file_new.addActionListener(new_action());
        view.edit_undo.addActionListener(undo_action());
        view.edit_redo.addActionListener(redo_action());
        view.file_exit.addActionListener(actionEvent -> view.frame.dispose());
        //set listener for toolbar
        view.openButton.addActionListener(open_action());
        view.saveButton.addActionListener(save_action());
        view.newButton.addActionListener(new_action());
        view.undoButton.addActionListener(undo_action());
        view.redoButton.addActionListener(redo_action());

    }

    private ActionListener redo_action() {
        return actionEvent -> {
            // redo
        };
    }

    private ActionListener undo_action() {
        return actionEvent -> {
            // undo
        };
    }

    private ActionListener new_action() {
        return actionEvent -> {
            // comportement new boutton
        };
    }

    private ActionListener save_action() {
        return actionEvent -> {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setDialogTitle("Choose a file to save");
            int returnVal = fileChooser.showSaveDialog(view);
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                File file = fileChooser.getSelectedFile();
                String filePath = file.getPath();

                //model save
            }
        };
    }

    private ActionListener open_action() {
        return actionEvent -> {
            JFileChooser fc = new JFileChooser();
            int returnValue = fc.showOpenDialog(view);
            if (returnValue == JFileChooser.APPROVE_OPTION) {
                File selectedFile = fc.getSelectedFile();
                String filePath = selectedFile.getPath();

                // model open
            }
        };
    }
}

