package com.example.demo.application;

import com.example.demo.HelloApplication;
import com.example.demo.application.view.App;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;

public class main extends Application {
    @Override
    public void start(Stage primaryStage) throws IOException {
        FlowPane root = new FlowPane();
        App app = new App();
        root.getChildren().add(app);
        //Stage stage = new Stage();
        /*FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("Hello!");*/
        primaryStage.setTitle("Game");
        primaryStage.setScene(new Scene(root, 500,250));
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
