package com.example.demo.application.view;


import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Color;

public class Mur extends Rectangle {
    //private Rectangle rect;

    public Mur(){
        super(20,20);
        this.setFill(Color.rgb(10,10,10));
        this.setX(20);
        this.setY(0);
    }
    public Mur(float x, float y){
        this();
        this.setX(x);
        this.setY(y);
    }

    public void setPosition(float x, float y ){
        this.setY(y);
        this.setX(x);
    }
}
