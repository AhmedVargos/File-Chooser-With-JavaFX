package sample;

import interfaces.Observer;
import interfaces.Subject;
import javafx.event.EventHandler;
import javafx.scene.control.TreeCell;
import javafx.scene.input.MouseEvent;

import javax.xml.soap.Text;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class DirectoryTreeCellFormater extends TreeCell<File> implements Subject{
    List<Observer> observerList;



    @Override
    protected void updateItem(File item, boolean empty) {
        super.updateItem(item, empty);
        if(item != null && !empty){
            javafx.scene.text.Text  text = new javafx.scene.text.Text(item.getName());
            text.setOnMousePressed(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    System.out.println("WORKING");
                    notifyObservers(item);
                }
            });

            setGraphic(text);
        }else {
            setGraphic(null);
        }
    }

    @Override
    public void register(Observer obj) {
        observerList.add(obj);
    }

    @Override
    public void unregister(Observer obj) {
        observerList.remove(obj);
    }

    @Override
    public void notifyObservers(File file) {
        for (Observer observer :
                observerList) {
            observer.update(file);
        }
    }
}
