package com.example.demo.application.view;

import com.example.demo.events.PositionValueListener;
import com.example.demo.events.PostionValueChangedEvent;
import javafx.scene.image.Image;
import javafx.scene.shape.Circle;

public class GhostViewN extends Circle implements PositionValueListener {
    public GhostViewN(){
        super(20);
    }

    public void setPosition(float x, float y ){
        this.setCenterX(y);
        this.setCenterX(x);
    }

    @Override
    public void positionValueChanged(PostionValueChangedEvent event) {
        System.out.println("G");
        setTranslateX((event.getNewPos().getX() * 20));
        setTranslateY((event.getNewPos().getY() * 20));
    }
}
