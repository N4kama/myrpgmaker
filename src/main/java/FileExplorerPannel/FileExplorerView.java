package FileExplorerPannel;

import javax.swing.*;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Observable;
import java.util.Observer;

import static Utils.WorldTools.loadWorld;

public class FileExplorerView extends JTree implements Observer {


    public FileExplorerView(DefaultMutableTreeNode TreeNode) {
        super(TreeNode);
        generateTree(TreeNode, "resources");
        addTreeSelectionListener(FileExplorerView::valueChanged);
    }

    private static void valueChanged(TreeSelectionEvent e) {
        DefaultMutableTreeNode file = (DefaultMutableTreeNode)
                e.getPath().getLastPathComponent();
        try {
            String path = "resources/maps/" + file.toString();
            //model.gameWorld =
            loadWorld (path);
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }
        System.out.println("You selected " + file);
    }

    private File[] getListFiles(String path) {
        File file = new File(path);
        return file.listFiles();
    }

    private boolean checkWrld(String path) {
        int i = path.lastIndexOf('.');
        return (i > 0 && path.substring(i + 1).equals("wrld"));
    }

    private void generateTree(DefaultMutableTreeNode TreeNode, String path) {
        File[] files = this.getListFiles(path);
        for (File file : files) {
            if (file.isDirectory()) {
                DefaultMutableTreeNode subDirectory = new DefaultMutableTreeNode(file.getName());
                generateTree(subDirectory, file.getAbsolutePath());
                TreeNode.add(subDirectory);
            } else if (checkWrld(file.getName())) {
                TreeNode.add(new DefaultMutableTreeNode(file.getName()));
            }


        }
    }

    @Override
    public void update(Observable o, Object arg) {

    }
}