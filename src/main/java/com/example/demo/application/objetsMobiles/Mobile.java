package com.example.demo.application.objetsMobiles;
import com.example.demo.application.elements.Element;


public class Mobile extends Element{
    private char direction;
    private int vitesse;

    public void mouvement() {

        switch (direction) {

/**modifier pour prendre en compte l'existence des murs*/
            case 'N':
                this.setY(this.getY() + vitesse);
                break;
            case 'S':
                this.setY(this.getY() - vitesse);
                break;

            case 'W':
                this.setX(this.getX() - vitesse);
                break;
            case 'E':
                this.setX(this.getX() + vitesse);
                break;


        }
    }
}
