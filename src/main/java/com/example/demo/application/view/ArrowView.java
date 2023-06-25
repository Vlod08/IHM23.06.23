package com.example.demo.application.view;

import javafx.event.EventHandler;
import javafx.scene.SnapshotParameters;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.paint.Color;
import javafx.scene.transform.Transform;

public class ArrowView extends ImageView {
    public ArrowView(){
        String filePath = MurView.class.getResource("/images/RedArrow.gif").toString();
        Image image = new Image(filePath);
        this.setImage(image);
        this.setFitWidth(30);
        this.setFitHeight(30);


    }

}

