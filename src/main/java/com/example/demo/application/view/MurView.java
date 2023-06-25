package com.example.demo.application.view;


import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class MurView extends ImageView {
    //private Rectangle rect;

    public MurView(){
        String filePath = MurView.class.getResource("/images/greywall.jpeg").toString();
        Image image = new Image(filePath);
        this.setImage(image);
        this.setFitWidth(20);
        this.setFitHeight(20);
    }
    public MurView(float x, float y){
        this();
        this.setTranslateX(x);
        this.setTranslateY(y);
    }

    public void setPosition(float x, float y ){
        this.setTranslateY(y);
        this.setTranslateX(x);
    }

    EventHandler<MouseEvent> eve = new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent m){
            System.out.println("clicked");
        }
    };


    public double getWidth() {
        return 20;
    }

    public double getHeight(){
        return 20;
    }
}
