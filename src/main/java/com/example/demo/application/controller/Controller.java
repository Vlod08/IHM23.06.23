package com.example.demo.application.controller;

import com.example.demo.application.elements.Element;
import com.example.demo.application.model.Model;
import com.example.demo.application.model.Coor;
import com.example.demo.application.view.PacmanView;
import com.example.demo.events.DirectionListener;
import com.example.demo.events.EatFruitListener;
import com.example.demo.events.GameOverListener;
import com.example.demo.events.PositionValueListener;


import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class Controller {
    private Model model;
    private Timer timer;
    private TimerTask task;



    public Controller(Model p){
        this.model = p;
        //MettreAJourMurs();

    }

    /*public ArrayList<Element> MettreAJourMurs(){


    }*/

    public ArrayList<Coor> getPositionMurs(){
        ArrayList<ArrayList<Element>> cases = model.getCases();
        ArrayList<Coor> coor = new ArrayList<>();
        int dim = model.getDim();
        int nbMurs = model.getNbMure();

        for (int i = 0; i < dim; i++) {
            for (int j = 0; j < dim; j++) {
                if(cases.get(j).get(i).getType()=='M') {
                    coor.add(new Coor(i*20,j*20));
                }
            }
        }

        return coor;
    }

    public ArrayList<Coor> getPositionFruits(){
        ArrayList<ArrayList<Element>> cases = model.getCases();
        ArrayList<Coor> coor = new ArrayList<>();
        int dim = model.getDim();

        for (int i = 0; i < dim; i++) {
            for (int j = 0; j < dim; j++) {
                if(cases.get(j).get(i).getType()=='F') {
                    coor.add(new Coor(i,j));
                    System.out.println("i : "+i +"   j : "+j );
                }
            }
        }


        return coor;
    }


    public int getNbMurs(){
        return model.getNbMure();
    }

    void UpdatePacmanPosition(Coor coor){
        this.model.setPacmanPosition(coor);
    }



    public Coor getPacmanPosition(){
        return model.getPacmanPosition();
    }

    public void changePacmanDirection(char newdir){
        char currentDir = model.getPacmanDirection();

        switch (newdir){
            case 'U':
                if(currentDir == 'L' || currentDir=='R'){
                    model.setPacmanDirection('U');
                    //System.out.println("changing the direction to up");
                }
                break;

            case 'D':
                if(currentDir == 'L' || currentDir=='R'){
                    model.setPacmanDirection('D');
                    //System.out.println("changing the direction to down");
                }
                break;

            case 'L':
                if(currentDir == 'U' || currentDir=='D'){
                    model.setPacmanDirection('L');
                    //System.out.println("changing the direction to left");
                }
                break;

            case 'R':
                if(currentDir == 'U' || currentDir=='D'){
                    model.setPacmanDirection('R');
                    //System.out.println("changing the direction to right");
                }
                break;
        }
    }

    public void setPacmanPostionListener(PositionValueListener p){
        model.setPacmanPostionListener(p);
    }

    public void addGhostPostionListener(PositionValueListener g){
        model.addGhostPositionListener(g);
    }

    public Coor modelCoortoViewCoor(Coor coor){
        coor.setX((coor.getX()*20)+10);
        coor.setY((coor.getY()*20)+10);
        return coor;
    }

    public void mouvementGame(){
        model.mouvementPacman();
        model.mouvementGhosts();
        model.checkCollision();
        //model.printCases();
    }


    public void setPacmanDirectionListener(DirectionListener pacmanDirectionListener) {
        model.setPacmanDirectionListener(pacmanDirectionListener);
    }

    public void setGameOverLister(GameOverListener g){
        model.setGameOverListener(g);
    }

    public void setEatFruitListener(EatFruitListener e){
        model.setEatFruitListener(e);
    }

    public void initialiseTimer(){
        timer = new Timer();
        int movementInterval= 550;


        task = new TimerTask() {
            @Override
            public void run() {
                //System.out.println("Called timer task");
                mouvementGame();

            }
        };

        timer.schedule(task, movementInterval,500);


    }
}
