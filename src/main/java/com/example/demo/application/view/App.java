package com.example.demo.application.view;

import com.example.demo.application.controller.Controller;
import com.example.demo.application.elements.mobile.Ghost;
import com.example.demo.application.model.Model;
import com.example.demo.application.model.Coor;
import com.example.demo.events.EatFruitEvent;
import com.example.demo.events.EatFruitListener;
import com.example.demo.events.GameOverEvent;
import com.example.demo.events.GameOverListener;
import javafx.animation.Transition;
import javafx.animation.TranslateTransition;
import javafx.scene.CacheHint;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.event.EventHandler;
import javafx.scene.paint.Color;
import javafx.util.Duration;

import java.util.ArrayList;

public class App extends Pane implements GameOverListener, EatFruitListener {

    private MursView murs;
    private PacmanView pacmanView;
    private ArrayList<GhostView> ghosts;
    private FruitsView fruits;
    private Controller controller;

    //private Plateforme plateforme;
    private Model model;
    private MurView mur;




    /*public GameView(Controller controller){
        this.controller = controller;
        this.murs = new Murs()
        murs.setPos(controller.getPositionMurs());
        pacman = controller.MettreAJourPacman();

    }*/

    public App(){
        /*****Implementing the model controller and the view***************************************/
        this.setMinWidth(500);
        this.setMinHeight(500);
        this.getStyleClass().clear();
        this.setStyle("-fx-background-color: #000000;");
        this.model = new Model();
        this.controller = new Controller(model);
        controller.setEatFruitListener(this);
        this.murs = new MursView(controller.getNbMurs());
        //controller.setPacmanPostionListener(new PacmanView());

        /****Placing the walls******************************************************************************/

        ArrayList<Coor> coor = controller.getPositionMurs();
        murs.setPositions(coor);

        /****Setting Pacman **********************************************************************************/
        this.pacmanView = new PacmanView();
        Coor c = controller.getPacmanPosition();
        this.pacmanView = new PacmanView();
        this.pacmanView.setTranslateX((c.getX() * 20));
        this.pacmanView.setTranslateY((c.getY() * 20));
        controller.setPacmanPostionListener(pacmanView);
        controller.setPacmanDirectionListener(pacmanView);
        /***************************************************************************************************/
        //System.out.println("DOne here");
        this.ghosts = new ArrayList<>();
        ArrayList<Coor> gPos = model.getGhostsPosition();
        System.out.println("Number of ghosts : "+gPos.size());
        for (int p = 0 ;p<5;p++){

            this.ghosts.add(new GhostView());
            this.ghosts.get(p).setTranslateX((gPos.get(p).getX()*20)+10);
            this.ghosts.get(p).setTranslateY((gPos.get(p).getY()*20)+10);
            controller.addGhostPostionListener(this.ghosts.get(p));
            //System.out.println("coors : "+gPos.get(p).getX()+" ; "+gPos.get(p).getY());
        }
        /***************************************************************************************************/
        ArrayList<Coor> fPos = controller.getPositionFruits();
        System.out.println("Number of fruits app : "+fPos.size());
        this.fruits = new FruitsView(fPos.size());

        //System.out.println("Number of ghosts : "+gPos.size());
        for (int p = 0 ;p<fPos.size();p++){

            //System.out.println("FRUIT : "+fPos.get(p).getX()+" : "+fPos.get(p).getX());
            this.fruits.get(p).setTranslateX((fPos.get(p).getX()*20));
            this.fruits.get(p).setTranslateY((fPos.get(p).getY()*20));
            //System.out.println("FRUIT : "+this.fruits.get(p).getX()+" : "+this.fruits.get(p).getX());
            System.out.println("APP COOR x : "+fPos.get(p).getX()+"  y : "+fPos.get(p).getY());


        }
        /****************************************************************************************************/



        //controller.
        //pacman.setCenterX();

        /*for(Coor c : coor){
            c.Print();
        }*/
        /*for(MurView m: murs){
            System.out.println("x : "+ Double.toString(m.getX()) + ", y : "+ Double.toString(m.getY()) );
        }*/
        setCache(true);
        setCacheShape(true);
        setCacheHint(CacheHint.SPEED);
        controller.setEatFruitListener(this);
        InisiatilerUI();
        controller.initialiseTimer();

    }


    private void InisiatilerUI(){
        for(int i = 0 ; i< murs.size(); i++){
            this.getChildren().addAll(murs.get(i));
        }
        this.setOnKeyPressed(moveEvent);
        this.setOnMouseClicked(mousemoveEvent);
        this.getChildren().addAll(fruits);
        this.getChildren().add(pacmanView);
        this.getChildren().addAll(ghosts);

        this.setPrefHeight(25*20);
        this.setPrefHeight(25*20);
        this.requestFocus();

        // Start the transition


    }

    EventHandler<MouseEvent> mousemoveEvent = new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent mouseEvent) {
            System.out.println("mouse was clicked");
        }
    };
    EventHandler<KeyEvent> moveEvent = new EventHandler<KeyEvent>() {
        @Override
        public void handle(KeyEvent event) {
            //Coor current = all.getPacmanPosition();

            switch (event.getCode()){
                case UP :
                    //System.out.println("changing the pacman direction to up");
                    controller.changePacmanDirection('U');
                    break;
                case DOWN :
                    //System.out.println("changing the pacman direction to down");
                    controller.changePacmanDirection('D');
                    break;
                case LEFT :
                    //System.out.println("changing the pacman direction to left");
                    controller.changePacmanDirection('L');
                    break;
                case RIGHT :
                    //System.out.println("changing the pacman direction to right");
                    controller.changePacmanDirection('R');
                    break;

            }

        }

    };


    @Override
    public void gameOver(GameOverEvent event) {
        this.getChildren().removeAll();
    }

    public void removefruit(int x, int y){
        for (FruitView f : this.fruits){
            if(f.getX()*20 ==(x*20) && f.getY()==(y*20)){
                this.fruits.remove(f);
                this.getChildren().removeAll(fruits);
            }
        }
    }

    @Override
    public void eatFruit(EatFruitEvent event) {
        removefruit(event.getX(),event.getY());
        System.out.println("calling eatFruit");
    }
}
