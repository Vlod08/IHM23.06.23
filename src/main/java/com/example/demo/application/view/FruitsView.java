package com.example.demo.application.view;

import com.example.demo.application.model.Coor;

import java.util.ArrayList;

public class FruitsView extends ArrayList<FruitView> {
    public int getNumFruits(){
        return this.size();
    }

    public FruitsView(int nb){
        for(int i = this.size(); i<nb; i++)
            this.add(new FruitView());
    }


    public void setPositions(ArrayList<Coor> c){
        for (int i = 0; i<c.size() && i<this.size();i++){
            this.get(i).setPosition(c.get(i).getX(),c.get(i).getY());
            //System.out.println("entered here ");
        }
    }
}
