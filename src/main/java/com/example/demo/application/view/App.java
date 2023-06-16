package com.example.demo.application.view;

import com.example.demo.application.controller.Controller;
import com.example.demo.application.elements.mobile.Pacman;
import com.example.demo.application.model.Coor;
import com.example.demo.application.model.Plateforme;
import javafx.scene.layout.Pane;

import java.util.ArrayList;

public class App extends Pane {

    private Murs murs;
    private Pacman pacman;

    //private Ghosts ghosts;
    private Controller controller;

    private Plateforme plateforme;




    /*public GameView(Controller controller){
        this.controller = controller;
        this.murs = new Murs()
        murs.setPos(controller.getPositionMurs());
        pacman = controller.MettreAJourPacman();

    }*/

    public App(){

        this.plateforme = new Plateforme();
        this.controller = new Controller(plateforme);
        this.murs = new Murs(controller.getNbMurs());
        ArrayList<Coor> coor = controller.getPositionMurs();
        murs.setPositions(coor);
        /*for(Coor c : coor){
            c.Print();
        }*/
        for(Mur m: murs){
            System.out.println("x : "+ Double.toString(m.getX()) + ", y : "+ Double.toString(m.getY()) );
        }

        InisiatilerUI();

    }

    private void InisiatilerUI(){
        for(int i = 0 ; i< murs.size(); i++){
            this.getChildren().addAll(murs.get(i));
        }


    }




}
