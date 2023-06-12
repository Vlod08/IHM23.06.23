package com.example.demo;

import com.example.demo.application.controller.Controller;
import com.example.demo.application.model.Plateforme;
import com.example.demo.application.view.GameView;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication {

    Plateforme plateforme;
    Controller controller;



    public HelloApplication(){
        plateforme = new Plateforme();
        GameView gameview = new GameView();

        controller = new Controller(plateforme, gameview);




    }
}