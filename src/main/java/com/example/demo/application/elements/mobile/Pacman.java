package com.example.demo.application.elements.mobile;

import javafx.scene.image.Image;

public class Pacman extends Mobile{
    int vie;

    public Pacman(){
        vie = 100;
        setXY( 0, 0);
        this.setVitesse(1);
        this.setType('P');

    }




}
