package sample;

import javafx.scene.control.TreeCell;
import javafx.scene.control.TreeView;
import javafx.util.Callback;

import java.io.File;

public class DirectoryTreeFactory implements Callback<TreeView<File>,TreeCell<File>> {

    private Controller controler;

    @Override
    public TreeCell<File> call(TreeView<File> param) {
        DirectoryTreeCellFormater formater = new DirectoryTreeCellFormater();
        formater.register(controler);
        //formater.setController(controler);
        return formater;
    }

    public void setController(Controller controler) {
        this.controler = controler;
    }
}
