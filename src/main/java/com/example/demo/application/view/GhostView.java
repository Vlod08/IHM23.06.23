package com.example.demo.application.view;

import com.example.demo.events.PositionValueListener;
import com.example.demo.events.PostionValueChangedEvent;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class GhostView extends ImageView implements PositionValueListener {
    public GhostView(){
        String filePath = GhostView.class.getResource("/images/ghost.gif").toString();
        Image image = new Image(filePath);
        this.setImage(image);
        this.setFitWidth(20);
        this.setFitHeight(20);
    }

    public void setPosition(float x, float y ){
        this.setTranslateY(y);
        this.setTranslateX(x);
    }

    @Override
    public void positionValueChanged(PostionValueChangedEvent event) {
        System.out.println("G");
        setTranslateX((event.getNewPos().getX() * 20));
        setTranslateY((event.getNewPos().getY() * 20));
    }
}
