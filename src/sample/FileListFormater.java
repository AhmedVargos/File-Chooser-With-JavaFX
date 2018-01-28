package sample;

import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class FileListFormater extends ListCell<File> {

    private ImageView imageView = new ImageView();

    @Override
    protected void updateItem(File item, boolean empty) {
        super.updateItem(item, empty);
        if (empty || item == null) {
            imageView.setImage(null);

            setGraphic(null);
            setText(null);
        } else {
            Image image = null;
            if (item.isFile()) {
                if (item.getName().contains(".png") || item.getName().contains(".jpg")||item.getName().contains(".PNG") || item.getName().contains(".JPG")) {
                    String path = null;
                    try {
                        path = item.getCanonicalPath();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    image = new Image(item.toURI().toString(), 40, 40, false, false);
                    imageView.setImage(image);
                    imageView.setPreserveRatio(true);

                } else {

                    image = new Image("file:/E:/ITI%20files/JAVA%20FX/lab%202%20filechooser/src/images/file-text-icon.png", 40, 40, false, false);
                    imageView.setImage(image);
                    imageView.setPreserveRatio(true);

                }
            }else {
                image = new Image("file:/E:/ITI%20files/JAVA%20FX/lab%202%20filechooser/src/images/480px-Icons8_flat_folder.svg.png", 40, 40, false, false);
                imageView.setImage(image);
                imageView.setPreserveRatio(true);

            }
            setText(item.getName());
            setGraphic(imageView);
        }

    }

    private String constructLabel(String prefix, String bird, String suffix) {
        return (prefix != null ? prefix : "")
                + bird
                + (suffix != null ? suffix : "");
    }

}
