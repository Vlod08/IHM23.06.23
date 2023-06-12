package com.example.demo.application.model;

import com.example.demo.application.elements.Element;

import java.util.ArrayList;
import java.util.Random;

public class Plateforme {

    final int MAX_X = 25;
    final int MAX_Y = 25;
    int dim;
    double ratioMurVide;
    private ArrayList<ArrayList<Element>> Cases;

    public Plateforme(){
        this.dim = 25;
        this.ratioMurVide = 0.25;
        int nVide = dim*dim;
        int nMure = 0;
        int xTemp, yTemp;
        Cases = new ArrayList<ArrayList<Element>>();
        Random random = new Random();
        while(nMure/nVide < ratioMurVide){

            xTemp = random.nextInt(dim);
            yTemp = random.nextInt(dim);

            if(Cases.get(xTemp).get(yTemp).getType()=='V'){
                Cases.get(xTemp).get(yTemp).setType('M');
                nMure++;
                nVide--;
            }
        }
    }

    public int getDim() {
        return dim;
    }

    public void setDim(int dim) {
        this.dim = dim;
    }

    public ArrayList<ArrayList<Element>> getCases() {
        return Cases;
    }





}
