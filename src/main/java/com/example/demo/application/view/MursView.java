package com.example.demo.application.view;

import com.example.demo.application.model.Coor;

import java.util.ArrayList;

public class MursView extends ArrayList<MurView>{

    public int getNumMurs(){
        return this.size();
    }

    public MursView(int nb){
        for(int i = this.size(); i<nb; i++)
        this.add(new MurView());
    }


    public void setPositions(ArrayList<Coor> c){
        for (int i = 0; i<c.size() && i<this.size();i++){
            this.get(i).setPosition(c.get(i).getX(),c.get(i).getY());
            //System.out.println("entered here ");
        }
    }




}
