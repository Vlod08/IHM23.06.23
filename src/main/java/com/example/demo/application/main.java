package com.example.demo.application;

import com.example.demo.HelloApplication;
import com.example.demo.application.view.App;
import com.example.demo.events.GameOverEvent;
import com.example.demo.events.GameOverListener;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;


import java.io.IOException;

public class main extends Application implements GameOverListener{
    @Override
    public void start(Stage primaryStage) throws IOException {
        FlowPane root = new FlowPane();

        //FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/demo/HomeWindow.fxml"));
        //root = loader.load();

        App app = new App();

        root.getChildren().add(app);



        //Stage stage = new Stage();
        /*FXMLLoader fxmlLoader = new FXMLLoader(get.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("Hello!");*/
        primaryStage.setTitle("PolyPac");
        primaryStage.setScene(new Scene(root, 800,700));
        primaryStage.setResizable(false);
        primaryStage.show();
        app.requestFocus();


    }

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void gameOver(GameOverEvent event) {
        System.out.println("gameover");
    }
}
