package Editor;

        import javax.swing.*;
        import javax.swing.filechooser.FileSystemView;
        import java.awt.event.ActionEvent;
        import java.awt.event.ActionListener;
        import java.io.File;

public class EditorController {
    private EditorView view;

    public EditorController(EditorView v) {
        this.view = v;
    }

    public void set_controls() {


        ActionListener open_action = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                JFileChooser fc = new JFileChooser();
                int returnValue = fc.showOpenDialog(view);
                if (returnValue == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fc.getSelectedFile();
                    String filePath = selectedFile.getPath();

                    // model open
                }
            }
        };

        ActionListener save_action = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setDialogTitle("Choose a file to save");
                int returnVal = fileChooser.showSaveDialog(view);
                if (returnVal == JFileChooser.APPROVE_OPTION) {
                    File file = fileChooser.getSelectedFile();
                    String filePath = file.getPath();

                    //model save
                }
            }
        };


        ActionListener undo_action = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                // undo
            }
        };

        ActionListener redo_action = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                // redo
            }
        };


        ActionListener new_action = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                // comportement new boutton
            }
        };


        view.getExit_menuItem().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                view.getMainFrame().dispose();
            }
        });

        //set Action Listner for open menu item
        view.getOpen_menuItem().addActionListener(open_action);
        view.getOpenButton().addActionListener(open_action);

        view.getSave_menuItem().addActionListener(save_action);
        view.getSaveButton().addActionListener(save_action);

        view.getNewButton().addActionListener(new_action);
        view.getNew_menuItem().addActionListener(new_action);

        view.getEditUndo_menuItem().addActionListener(undo_action);
        view.getUndoButton().addActionListener(undo_action);

        view.getEditRedo_menuItem().addActionListener(redo_action);
        view.getRedoButton().addActionListener(redo_action);

    }
}

