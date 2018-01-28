package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{

        FXMLLoader fXMLLoader = new FXMLLoader();
        Parent root = fXMLLoader.load(getClass()
                .getResource("NewDesign.fxml")
                .openStream());

        Controller controller =  fXMLLoader.getController();

        Scene scene = new Scene(root);
//        scene.setFill(Color.TRANSPARENT);
//        primaryStage.initStyle(StageStyle.TRANSPARENT);
//
        primaryStage.setTitle("File Chooser");
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
