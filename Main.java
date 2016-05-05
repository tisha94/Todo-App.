package sample; //Day20

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene; // this is everything within the window
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage; // this is the whole window
import javafx.stage.WindowEvent;

import java.util.Scanner;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{

//        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
//        Controller myController = new Controller();

        // Just creating the Parent root differently, so that we can use
        // the FXMLLoader instance to get the controller
        FXMLLoader fxmlLoader = new FXMLLoader();
        Parent root = fxmlLoader.load(getClass().getResource("sample.fxml").openStream());
        // Get the controller so we can access the saveList() method ...
        Controller myController = (Controller) fxmlLoader.getController();

        primaryStage.setTitle("Hello Fellow Earthling");
        primaryStage.setScene(new Scene(root, 595, 200)); // set window size.
        primaryStage.show(); // show window


        primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>(){ // gives info on the key that u pressed

            @Override
            public void handle(WindowEvent event) {
                myController.saveItem(); // called the Controller class = myController + saveitem(); method to save list
            }

        });
    }


    public static void main(String[] args) {
        launch(args);
    }
}
