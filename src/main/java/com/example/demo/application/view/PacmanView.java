package com.example.demo.application.view;

import com.example.demo.application.elements.mobile.Pacman;
import com.example.demo.events.PositionValueListener;
import com.example.demo.events.PostionValueChangedEvent;
import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class PacmanView extends Circle implements PositionValueListener {
    public PacmanView(){
        super(10);
        this.setFill(Color.rgb(0,255,0));
    }

    @Override
    public void positionValueChanged(PostionValueChangedEvent event) {
        setCenterX((event.getNewPos().getX()*20)+10);
        setCenterY((event.getNewPos().getY()*20)+10);
    }
}
