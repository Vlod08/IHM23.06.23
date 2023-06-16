package com.example.demo.application.controller;

import com.example.demo.application.elements.Element;
import com.example.demo.application.model.Coor;
import com.example.demo.application.model.Plateforme;
import com.example.demo.application.view.App;


import java.util.ArrayList;

public class Controller {
    Plateforme p;


    public Controller(Plateforme p){
        this.p = p;

        //MettreAJourMurs();

    }

    /*public ArrayList<Element> MettreAJourMurs(){


    }*/

    public ArrayList<Coor> getPositionMurs(){
        ArrayList<ArrayList<Element>> cases = p.getCases();
        ArrayList<Coor> coor = new ArrayList<>();
        int dim = p.getDim();
        int nbMurs = p.getNbMure();

        for (int i = 0; i < dim; i++) {
            for (int j = 0; j < dim; j++) {
                if(cases.get(i).get(j).getType()=='M') {
                    coor.add(new Coor(i*20,j*20));
                }
            }
        }

        return coor;
    }

    public int getNbMurs(){
        return p.getNbMure();
    }


}
