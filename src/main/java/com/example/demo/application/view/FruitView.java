package com.example.demo.application.view;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class FruitView extends ImageView {
    public FruitView(){
        String filePath = MurView.class.getResource("/images/fruit.gif").toString();
        Image image = new Image(filePath);
        this.setImage(image);
        this.setFitWidth(20);
        this.setFitHeight(20);
    }

    public void setPosition(float x, float y ){
        this.setTranslateY(y);
        this.setTranslateX(x);
    }
}
