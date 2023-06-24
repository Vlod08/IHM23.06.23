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
    @Override
    public void mouvement() {
        switch (this.getDir()) {
            case 'U':
                this.setY(this.getY() - vitesse);
                break;
            case 'D':
                this.setY(this.getY() + vitesse);
                break;
            case 'L':
                this.setX(this.getX() - vitesse);
                break;
            case 'R':
                this.setX(this.getX() + vitesse);
                break;
        }
    }

    @Override
    public void setDir(char c){
        super.setDir(c);


    }





}
