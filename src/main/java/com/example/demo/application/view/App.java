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
import javafx.application.Platform;
import javafx.scene.CacheHint;
import javafx.scene.Group;
import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.*;
import javafx.scene.layout.Pane;
import javafx.event.EventHandler;
import javafx.scene.paint.Color;
import javafx.scene.transform.Transform;
import javafx.util.Duration;


import java.util.ArrayList;


public class App extends Group implements GameOverListener, EatFruitListener {

    private MursView murs;
    private PacmanView pacmanView;
    private ArrayList<GhostView> ghosts;
    private FruitsView fruits;
    private Controller controller;

    private ArrayList<ArrowView> arrows;

    //private Plateforme plateforme;
    private Model model;
    private MurView mur;

    private ImageView m;




    /*public GameView(Controller controller){
        this.controller = controller;
        this.murs = new Murs()
        murs.setPos(controller.getPositionMurs());
        pacman = controller.MettreAJourPacman();

    }*/

    public App(){
        /*****Implementing the model controller and the view***************************************/
        /*this.setMinWidth(500);
        this.setMinHeight(500);*/

        this.getStyleClass().clear();
        this.setStyle("-fx-background-color: black");
        this.model = new Model();
        this.controller = new Controller(model);
        controller.setEatFruitListener(this);
        this.murs = new MursView(controller.getNbMurs());
        //controller.setPacmanPostionListener(new PacmanView());

        /****Placing the walls******************************************************************************/

        ArrayList<Coor> coor = controller.getPositionMurs();
        murs.setPositions(coor);
        //m = murs.createCachedWallsImageView();


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
            //System.out.println("APP COOR x : "+fPos.get(p).getX()+"  y : "+fPos.get(p).getY());


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
        //setCacheShape(true);
        setCacheHint(CacheHint.SPEED);
        controller.setEatFruitListener(this);
        arrows = new ArrayList<>();
        for(int i =0; i<3;i++){
            arrows.add(new ArrowView());
            arrows.get(i).setTranslateX((20*25)+30+(i*50));
            arrows.get(i).setTranslateY(20*13);
            arrows.get(i).addEventHandler(MouseEvent.MOUSE_DRAGGED, event -> {
                double endX = event.getX();
                double endY = event.getY();

                this.setTranslateX(endX);
                this.setTranslateY(endY);
            });


        }
        InisiatilerUI();
        controller.initialiseTimer();
        controller.setGameOverLister(this);

    }


    private void InisiatilerUI(){
        for(int i = 0 ; i< murs.size(); i++){
            this.getChildren().addAll(murs.get(i));
        }
        //this.getChildren().add(m);
        this.setOnKeyPressed(moveEvent);
        this.setOnMouseClicked(mousemoveEvent);
        this.getChildren().addAll(fruits);
        this.getChildren().add(pacmanView);
        this.getChildren().addAll(ghosts);
        this.getChildren().addAll(arrows);



        //this.setPrefHeight(25*20);
        //this.setPrefHeight(25*20);
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
        Platform.runLater(() -> {

            this.getChildren().removeAll(ghosts);

        });
    }

    public void removeFruit(int x, int y) {
        Platform.runLater(() -> {
            for (FruitView fruit : fruits) {
                if (fruit.getTranslateX() == (x * 20) && fruit.getTranslateY() == (y * 20)) {
                    getChildren().remove(fruit);
                }
            }
        });
    }

    @Override
    public void eatFruit(EatFruitEvent event) {
        System.out.println("x : "+event.getX()+"  y: "+event.getY());
        removeFruit(event.getX(),event.getY());
        System.out.println("calling eatFruit");
    }




}
