package com.example.demo.application.elements.mobile;

import com.example.demo.application.elements.Element;

public class Mobile extends Element {
    char dir;
    int vitesse;



    public void mouvement(){
        switch (this.dir) {
            case 'U':
                System.out.println(" x : "+this.getX()+" ; y : "+this.getY());
                this.setY(this.getY() - vitesse);
                System.out.println(" x : "+this.getX()+" ; y : "+this.getY());
                break;
            case 'D':
                System.out.println(" x : "+this.getX()+" ; y : "+this.getY());
                this.setY(this.getY() + vitesse);
                System.out.println(" x : "+this.getX()+" ; y : "+this.getY());
                break;
            case 'L':
                System.out.println(" x : "+this.getX()+" ; y : "+this.getY());
                this.setX(this.getX() - vitesse);
                System.out.println(" x : "+this.getX()+" ; y : "+this.getY());
                break;
            case 'R':
                System.out.println(" x : "+this.getX()+" ; y : "+this.getY());
                this.setX(this.getX() + vitesse);
                System.out.println(" x : "+this.getX()+" ; y : "+this.getY());
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

    public char getDir(){
        return dir;
    }

    public int getVitesse(){
        return vitesse;
    }

    public Mobile(){
        dir = 'R';
        vitesse = 1;
    }



}
