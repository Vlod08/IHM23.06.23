package com.example.demo.application.model;

import com.example.demo.application.elements.Element;
import com.example.demo.application.elements.mobile.Ghost;
import com.example.demo.application.elements.mobile.Pacman;

import java.lang.reflect.GenericArrayType;
import java.util.ArrayList;
import java.util.Random;

public class Plateforme {

    final int MAX_X = 25;
    final int MAX_Y = 25;
    int dim;
    double ratioMurVide;
    private ArrayList<ArrayList<Element>> cases;

    int nbMure;

    int nbFruits;
    private ArrayList<Ghost> ghosts;

    public Plateforme() {
        this.nbFruits = 25;
        this.dim = 25;
        this.ratioMurVide = 0.1;
        int nVide = dim * dim;
        int nMure = 0;
        int xTemp, yTemp;
        cases = new ArrayList<ArrayList<Element>>();
        for (int i = 0; i < dim; i++) {
            cases.add(new ArrayList<Element>());
            for (int j = 0; j < dim; j++) {
                cases.get(i).add(new Element()); // Create empty Element
            }
        }
        Random random = new Random();
        while (nMure < 50) {
            xTemp = random.nextInt(1,dim-2);
            yTemp = random.nextInt(1,dim-2);

            if (cases.get(xTemp).get(yTemp).getType() == 'V') {
                cases.get(xTemp).get(yTemp).setType('M'); // Set the type to 'M' (wall)
                nMure++;
                nVide--;
            }
        }
        for(int i=0; i<dim; i++){
            cases.get(0).get(i).setType('M');
            cases.get(dim-1).get(i).setType('M');
            cases.get(i).get(0).setType('M');
            cases.get(i).get(dim-1).setType('M');
            nMure+= 4;
            nVide-= 4;
        }
        this.nbMure = nMure;
        placeFruits();
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

    public Coor placePacman(){
        for(int i = 12; i < this.dim;i++){
            for(int j = 12; j<dim; j++){
                if (this.cases.get(j).get(i).getType()=='V'){
                    this.cases.get(j).get(i).setType('P');
                    System.out.println("Placed pacman at : "+j+"  "+i);
                    Coor currentPosition = new Coor(i,j);
                    setPacmanPosition(currentPosition);
                    return currentPosition;

                }
            }
        }
        for(int i = 12; i < this.dim;i--){
            for(int j = 12; j<dim; j--){
                if (this.cases.get(i).get(j).getType()=='V'){
                    this.cases.get(i).get(j).setType('P');

                    System.out.println("Placed pacman at : "+i+"  "+j+"      sec");
                    Coor currentPosition = new Coor(j,i);
                    setPacmanPosition(currentPosition);
                    return currentPosition;


                }
            }
        }


        return new Coor(-10,-10);

    }

    public Coor getPacmanPosition(){
        for(int i =0;i<dim;i++){
            for (int j = 0;j<dim;j++){
                if(cases.get(i).get(j).getType()=='P'){
                    return new Coor(j,i);
                }
            }
        }
        return new Coor();
    }

    public void setPacmanPosition(Coor coor){
        if(cases.get((int)coor.getY()).get ((int)coor.getX()).getType()!='M'){
            Coor currentPosition = getPacmanPosition();
            setCaseType(currentPosition,'V');
            cases.get((int)coor.getY()).get ((int)coor.getX()).setType('P');
        }
    }

    public char getCaseType(int i, int j){
        return this.cases.get(j).get(i).getType();
    }

    private void setCaseType(Coor c, char type){
        this.cases.get((int)c.getX()).get((int)c.getY()).setType(type);
    }




    public Coor placeGhost() {
        Random random = new Random();
        boolean ok = false;
        int x = random.nextInt(25);
        int y = random.nextInt(25);
        while (!ok) {
            if (this.getCaseType(x,y) == 'V') {
                this.getCaseType(x,y);
                //System.out.println("printing the type : "+this.getCaseType(x,y));
                this.cases.get(y).get(x).setType('G');
                Coor currentPosition = new Coor(x, y);
                System.out.println("placeGhost : "+x+" : "+y);
                //printCases();
                return currentPosition;
            } else {
                //System.out.println("Position not empty, generating new coordinates...");
                x = random.nextInt(25);
                y = random.nextInt(25);
            }
        }
        Coor coor = new Coor(-10,-10);
        return coor;
    }


    public void updateGhosts(){
        this.ghosts = new ArrayList<>();
        for (int k=0; k< 5 ; k++){

            this.ghosts.add(new Ghost());
            for(int i =0;i<dim;i++){
                for (int j = 0;j<dim;j++){
                    if(cases.get(j).get(i).getType()=='G'){
                        this.ghosts.get(k).setXY(i,j);
                        System.out.println("printing "+ i+"  :  "+j);
                    }
                }
            }
        }
    }


    public void printCases() {
        String str = "[ \n";
        for (int i = 0; i < 25; i++) {
            str += "[ ";
            for (int j = 0; j < 25; j++) {
                str = str + cases.get(i).get(j).getType() + " ";
            }
            str += "] \n";
        }
        str += "]";

        System.out.println(str);
    }


    public ArrayList<Ghost> getGhosts(){
        return this.ghosts;
    }

    public ArrayList<Coor> placeFruits(){
        ArrayList<Coor> fruitsPositions = new ArrayList<>();
        int cntr = nbFruits;
        int x,y;
        Random rand = new Random();
        while(cntr > 0){
            x = rand.nextInt(1,25);
            y = rand.nextInt(1,25);
            if(cases.get(y).get(x).getType()=='V'){
                cases.get(y).get(x).setType('F');
                fruitsPositions.add(new Coor(x,y));
                cntr--;
            }

        }

        printCases();
        return fruitsPositions;

    }

    public  void removeFruit(int x, int y){
        this.cases.get(y).get(x).setType('V');
        this.nbFruits--;
        if(nbFruits==0){
            /*****gameWonEvent;************/
        }
    }
}


