package com.example.demo.application.elements.mobile;

import com.example.demo.application.elements.Element;

public class Mobile extends Element {
    char dir;
    int vitesse;



    public void mouvement(){
        switch (this.dir) {
            case 'U':
                setX(getY() + vitesse);
                break;
            case 'D':
                setY(getY() - vitesse);
                break;
            case 'L':
                setX(getX() - vitesse);
                break;
            case 'R':
                setX(getX() + vitesse);
                break;
        }
    }

    public void setDir(char dir){
        if(dir == 'U' || dir == 'D' || dir == 'L' || dir == 'R'){
            this.dir = dir;
        }
        else{
            System.out.println("Invalid key");
        }
    }

    public void setVitesse(int vitesse){
        if(vitesse > 0){
            this.vitesse = vitesse;
        }
        else{
            System.out.println("negative ");
        }
    }



}
