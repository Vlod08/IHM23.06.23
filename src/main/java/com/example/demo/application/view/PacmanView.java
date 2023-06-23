package com.example.demo.application.view;

import com.example.demo.application.elements.mobile.Pacman;
import com.example.demo.events.PositionValueListener;
import com.example.demo.events.PostionValueChangedEvent;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class PacmanView extends ImageView implements PositionValueListener {
    public PacmanView() {
        String filePath = PacmanView.class.getResource("/images/pacmanR.png").toString();
        Image image = new Image(filePath);
        this.setImage(image);
        this.setFitWidth(20);
        this.setFitHeight(20);

        // this.setFill(Color.rgb(0,255,0));
    }

    @Override
    public void positionValueChanged(PostionValueChangedEvent event) {
        setTranslateX((event.getNewPos().getX() * 20));
        setTranslateY((event.getNewPos().getY() * 20));
    }
}
