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

        //set Action Listner for open menu item
        view.getOpen_menuItem().addActionListener(new ActionListener() {
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
        });

        view.getExit_menuItem().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                view.getMainFrame().dispose();
            }
        });

        view.getSave_menuItem().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setDialogTitle("Choose a file that you want to save");
                int returnVal = fileChooser.showSaveDialog(view);
                if (returnVal == JFileChooser.APPROVE_OPTION) {
                    File file = fileChooser.getSelectedFile();
                    String filePath = file.getPath();

                    //model save
                }
            }
        });

        view.getNew_menuItem().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                // comportement new boutton
            }
        });

        view.getEditUndo_menuItem().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                // undo
            }
        });


        view.getEditRedo_menuItem().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                // rendo
            }
        });
    }
}

