package com.example.demo.application.view;

import com.example.demo.application.model.Coor;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;

public class Murs extends ArrayList<Mur>{

    public int getNumMurs(){
        return this.size();
    }

    public Murs (int nb){
        for(int i = this.size(); i<nb; i++)
        this.add(new Mur());
    }



    public void setPositions(ArrayList<Coor> c){
        for (int i = 0; i<c.size() && i<this.size();i++){
            this.get(i).setPosition(c.get(i).getX(),c.get(i).getY());
            System.out.println("entered here ");
        }
    }




}
