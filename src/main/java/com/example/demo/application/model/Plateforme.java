package com.example.demo.application.model;

import com.example.demo.application.elements.Element;

import java.util.ArrayList;
import java.util.Random;

public class Plateforme {

    final int MAX_X = 25;
    final int MAX_Y = 25;
    int dim;
    double ratioMurVide;
    private ArrayList<ArrayList<Element>> cases;

    int nbMure;

    public Plateforme(){
        this.dim = 25;
        this.ratioMurVide = 0.25;
        int nVide = dim*dim;
        int nMure = 0;
        int xTemp, yTemp;
        cases = new ArrayList<ArrayList<Element>>();
        for(int i = 0; i<dim; i++){
            cases.add(new ArrayList<Element>());
            for(int j = 0; j<dim; j++){
                cases.get(i).add(new Element());
            }
        }
        Random random = new Random();
        while(nMure/nVide < ratioMurVide){

            xTemp = random.nextInt(dim);
            yTemp = random.nextInt(dim);

            if(cases.get(xTemp).get(yTemp).getType()=='V'){
                cases.get(xTemp).get(yTemp).setType('M');
                nMure++;
                nVide--;
            }
        }
        this.nbMure = nMure;
        System.out.println("[ ");
        /*for(int i = 0; i<dim; i++){
            String str = "[ ";
            for(int j = 0; j< cases.get(i).size(); j++){
                str += " " + cases.get(i).get(j).getType()+" ";
            }
            str += " ] ";
            System.out.println(str);
        }
        System.out.println("] ");*/

    }

    public int getDim() {
        return dim;
    }

    public void setDim(int dim) {
        this.dim = dim;
    }

    public ArrayList<ArrayList<Element>> getCases() {
        return cases;
    }

    public int getNbMure(){
        return nbMure;
    }




}
