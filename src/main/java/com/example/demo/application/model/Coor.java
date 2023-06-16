package com.example.demo.application.model;

public class Coor {
    float x;
    float y;

    public Coor(){
      this.x = 0;
      this.y = 0;
    }

    public Coor(float x, float y){
        this.x = x;
        this.y =y;
    }

    public float getX(){
        return this.x;
    }
    public float getY(){
        return this.y;
    }

    public void Print(){
        System.out.println("x : "+ Float.toString(x)+ " , y : "+ Float.toString(x));
    }
}
