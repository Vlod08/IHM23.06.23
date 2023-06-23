package com.example.demo.application.view;


import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class MurView extends Rectangle{
    //private Rectangle rect;

    public MurView(){
        super(20,20);
        this.setFill(Color.rgb(10,10,10));
        this.setX(20);
        this.setY(0);
    }
    public MurView(float x, float y){
        this();
        this.setX(x);
        this.setY(y);
    }

    public void setPosition(float x, float y ){
        this.setY(y);
        this.setX(x);
    }

    EventHandler<MouseEvent> eve = new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent m){
            System.out.println("clicked");
        }
    };







}
