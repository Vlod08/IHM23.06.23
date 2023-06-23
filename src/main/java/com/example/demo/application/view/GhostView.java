package com.example.demo.application.view;

import com.example.demo.events.PositionValueListener;
import com.example.demo.events.PostionValueChangedEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class GhostView extends Circle implements PositionValueListener {
    public GhostView(){
        super(10);
        this.setFill(Color.rgb(255,0,0));
    }

    @Override
    public void positionValueChanged(PostionValueChangedEvent event) {
        setCenterX((event.getNewPos().getX()*20)+10);
        setCenterY((event.getNewPos().getY()*20)+10);
    }
}
