package FileExplorerPannel;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Observable;
import java.util.Observer;

import static Utils.WorldTools.loadWorld;

public class FileExplorerView extends JTree implements Observer {



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