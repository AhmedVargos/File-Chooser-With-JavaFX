package sample;

import interfaces.Observer;
import interfaces.Subject;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable, Observer {

    @FXML
    public AnchorPane filesContainer;
    @FXML
    public AnchorPane treeContainer;
    @FXML
    public TextField pathField;
    @FXML
    public TreeView directoryTree;
    @FXML
    public ListView fileListView;

    private String path = null;
    private Subject treeFormater;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        pathField.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (event.getCode() == KeyCode.ENTER) {
                    path = pathField.getText();
                    setDirectoryTree(path);
                }
            }
        });
    }

    //create root view of the files in a directory
    private TreeItem<File> createNode(final File f) {
        return new TreeItem<File>(f) {
            private boolean isLeaf;
            private boolean isFirstTimeChildren = true;
            private boolean isFirstTimeLeaf = true;

            @Override
            public ObservableList<TreeItem<File>> getChildren() {
                if (isFirstTimeChildren) {
                    isFirstTimeChildren = false;
                    super.getChildren().setAll(buildChildren(this));
                }
                return super.getChildren();
            }

            @Override
            public boolean isLeaf() {
                if (isFirstTimeLeaf) {
                    isFirstTimeLeaf = false;
                    File f = (File) getValue();
                    isLeaf = f.isFile();
                }
                return isLeaf;
            }

            private ObservableList<TreeItem<File>> buildChildren(
                    TreeItem<File> TreeItem) {
                File f = TreeItem.getValue();
                if (f == null) {
                    return FXCollections.emptyObservableList();
                }
                if (f.isFile()) {
                    return FXCollections.emptyObservableList();
                }
                File[] files = f.listFiles();
                if (files != null) {
                    ObservableList<TreeItem<File>> children = FXCollections
                            .observableArrayList();
                    for (File childFile : files) {
                        children.add(createNode(childFile));
                    }
                    return children;
                }
                return FXCollections.emptyObservableList();
            }
        };
    }

    //list the files in a folder
    public ObservableList getFilesFromFolder(File dirName) throws IOException {
        File dir = dirName;
        File[] files = dir.listFiles();
        if (files != null) {
            ObservableList list = FXCollections
                    .observableArrayList();

            list.addAll(files);
            return list;
        } else {
            return FXCollections.emptyObservableList();
        }
    }

    private void setDirectoryTree(String path) {
        TreeItem<File> root = createNode(new File(path));
        directoryTree.setRoot(root);
        DirectoryTreeFactory factory = new DirectoryTreeFactory();
        factory.setController(this);
        directoryTree.setCellFactory(factory);
    }

    public void fillList(File item) {
        try {

            fileListView.setItems(getFilesFromFolder(item));
            fileListView.setCellFactory(new ListViewFactory());

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void update(File file) {
        fillList(file);
    }

}
