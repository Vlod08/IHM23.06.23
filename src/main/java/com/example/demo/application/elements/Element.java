package com.example.demo.application.elements;

import java.util.Random;



public class Element{
    final int MAX_X = 1000;
    final int MAX_Y = 1000;
    int x;
    int y;

    /***image a implementer*/
    char type;


    public Element(int x,int y,char type){
        setX(x);
        setY(y);
        setType(type);
    }

    public Element(){

        Random random = new Random();
        this.x = 0;
        this.y = 0;
        this.type = 'V';
    }

    /**Setters */
    public void setX(int x){

        if(x>0 && x < MAX_X ){
            this.x = x;

            System.out.println("setx : "+x+" ; sety : "+y);
        }

    }
    public void setY(int y){
        if(y>0 && y < MAX_Y ){
            this.y = y;

            //System.out.println("setx : "+x+" ; sety : "+y);
        }
    }

    public void setXY(int x,int y){
        setX(x);
        setY(y);
    }
    public void setType(char type){
        if(type == 'V' || type == 'M' || type == 'P' || type == 'G' || type == 'F'){
            this.type = type;
        }
        else{
            System.out.println("Unknow type error !!!");
        }
    }

    /**Getters*/
    public int getX(){
        return x;
    }

    public int getY(){
        return y;
    }

    public char getType(){
        return type;
    }
}

