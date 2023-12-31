package com.example.demo.application.model;

import com.example.demo.application.elements.Element;
import com.example.demo.application.elements.mobile.Ghost;
import com.example.demo.application.elements.mobile.Pacman;
import com.example.demo.events.*;

import java.util.ArrayList;
import java.util.Random;

import static java.lang.Math.abs;

public class Model {
    private int score;
    private Plateforme plateforme;
    private Pacman pacman;
    private ArrayList<Ghost> ghosts;
    private final int nbGhosts = 5;
    private PositionValueListener pacmanPostionListener;

    private DirectionListener pacmanDirectionListener;

    private ArrayList<PositionValueListener> ghostsPostionListener;
    private GameOverListener gameOverListener;

    private EatFruitListener eatFruitListener;


    public Model(){
        plateforme = new Plateforme();
        pacman = new Pacman();
        this.ghosts = new ArrayList<>();
        Coor coor;
        coor = plateforme.placePacman();
        this.pacman.setXY((int)coor.getX(),(int) coor.getY());
        //System.out.println(pacman.getX() +"     "+pacman.getY());
        this.ghosts = new ArrayList<>();
        ghostsPostionListener = new ArrayList<>();
        for(int i =0;i <5;i++){
            this.ghosts.add(new Ghost());
            coor = plateforme.placeGhost();
            this.ghosts.get(i).setX((int)coor.getX());
            //System.out.println("cootx: "+coor.getX());
            //System.out.println("cooty: "+coor.getY());
            this.ghosts.get(i).setY((int)coor.getY());
            score = 0;
        }


    }
    public int getDim(){
        return plateforme.getDim();
    }

    public int getNbMure(){
        return plateforme.getNbMure();
    }

    public ArrayList<ArrayList<Element>> getCases(){
        return plateforme.getCases();
    }

    public Coor getPacmanPosition(){
        return new Coor(pacman.getX(), pacman.getY());
    }

    public void setPacmanPosition(Coor coor){
        plateforme.setPacmanPosition(coor);

    }

    public Coor placePacman(){
        plateforme.placePacman();
        return plateforme.getPacmanPosition();
    }

    public char getPacmanDirection(){
        return pacman.getDir();
    }

    public void setPacmanDirection(char dir) {

        this.pacman.setDir(dir);
        firePacmanDirectionChanged(dir);
        //mouvementPacman();

    }

    private void firePacmanDirectionChanged(char dir){
        if(this.pacmanDirectionListener!=null){
            pacmanDirectionListener.directionChanged(new DirectionChangedEvent(null, dir));
        }
    }

    public void setPacmanDirectionListener(DirectionListener pacmanDirectionListener){
        this.pacmanDirectionListener = pacmanDirectionListener;
    }

    public void mouvementPacman(){
        //System.out.println("calling mouvementPacman");
        switch (pacman.getDir()){
            case 'R':
                if(plateforme.getCaseType(pacman.getX()+pacman.getVitesse(),pacman.getY())!='M'){

                    pacman.mouvement();
                    checkEatFruit(pacman.getX(),pacman.getY());
                    fireValueChangedP();
                }
                break;
            case 'L':
                if(plateforme.getCaseType(pacman.getX()-pacman.getVitesse(),pacman.getY())!='M'){
                    pacman.mouvement();
                    checkEatFruit(pacman.getX(),pacman.getY());
                    fireValueChangedP();
                }
                break;
            case 'U':
                if(plateforme.getCaseType(pacman.getX(),pacman.getY()-pacman.getVitesse())!='M'){
                    pacman.mouvement();
                    checkEatFruit(pacman.getX(),pacman.getY());
                    fireValueChangedP();
                }
                break;
            case 'D':
                if(plateforme.getCaseType(pacman.getX(),pacman.getY()+pacman.getVitesse())!='M'){
                    pacman.mouvement();
                    checkEatFruit(pacman.getX(),pacman.getY());
                    fireValueChangedP();
                }
                break;
        }

    }


    public void fireValueChangedP(){
        if(pacmanPostionListener != null) {
            pacmanPostionListener.positionValueChanged(new PostionValueChangedEvent(null, this.getPacmanPosition()));
        }
        /*for(PositionValueListener g : GhostsPostionListener ){
           // g.positionValueChanged(new PostionValueChangedEvent(null, this.something));
        }*/
    }

    public void fireValueChangedG(){
        if(ghostsPostionListener != null) {


            PositionValueListener g;
            for (int i = 0; i < 5; i++) {
                g = this.ghostsPostionListener.get(i);
                g.positionValueChanged(new PostionValueChangedEvent(null, new Coor(this.ghosts.get(i).getX(), this.ghosts.get(i).getY())));
            }
        }
    }

    public void setPacmanPostionListener(PositionValueListener pacmanPostionListener) {
        this.pacmanPostionListener = pacmanPostionListener;
    }

    public void addGhostPositionListener(PositionValueListener g){
        this.ghostsPostionListener.add(g);
    }

   public ArrayList<Ghost> getGhosts(){
        return this.ghosts;
   }


   public ArrayList<Coor> getGhostsPosition(){
        ArrayList<Coor> coor = new ArrayList<>();
       for(int i =0; i<5;i++){
           coor.add(new Coor(this.ghosts.get(i).getX(),this.ghosts.get(i).getY() ));
           //System.out.println("Printing in get ghosts : "+this.ghosts.get(i).getX()+"  :  "+this.ghosts.get(i).getY());
       }
        return coor;
   }

    public void mouvementGhosts() {
        for (Ghost ghost : ghosts) {
            mouvementGhost(ghost);
        }
        fireValueChangedG();

        // Notify the registered listeners about the updated ghost positions
        /*for (PositionValueListener listener : ghostPositionListeners) {
            listener.onPositionValueChanged(ghosts);
        }*/
    }


    private void mouvementGhost(Ghost ghost) {
        //System.out.println("calling mouvementGhost");
        boolean rand = true;
        char[] directions = {'U', 'D', 'R', 'L'};


        switch (ghost.getDir()) {
            case 'U':
                if (plateforme.getCaseType(ghost.getX(), ghost.getY() - ghost.getVitesse()) != 'M') {
                    ghost.setY(ghost.getY() - ghost.getVitesse());
                    checkCollision(ghost);
                    rand = false;
                }
                break;
            case 'D':
                if (plateforme.getCaseType(ghost.getX(), ghost.getY() + ghost.getVitesse()) != 'M') {
                    ghost.setY(ghost.getY() + ghost.getVitesse());
                    checkCollision(ghost);
                    rand = false;
                }
                break;
            case 'R':
                if (plateforme.getCaseType(ghost.getX() + ghost.getVitesse(), ghost.getY()) != 'M') {
                    ghost.setX(ghost.getX() + ghost.getVitesse());
                    checkCollision(ghost);
                    rand = false;
                }
                break;
            case 'L':
                if (plateforme.getCaseType(ghost.getX() - ghost.getVitesse(), ghost.getY()) != 'M') {
                    ghost.setX(ghost.getX() - ghost.getVitesse());
                    checkCollision(ghost);
                    rand = false;
                }
                break;
        }

        if (rand) {
            // Handle random direction when unable to move in a random direction
            Random random = new Random();
            int randomIndex = random.nextInt(directions.length);
            char randomDirection = directions[randomIndex];
            char[] remainingDirections = new char[directions.length - 1];
            int index = 0;
            for (char direction : directions) {
                if (direction != randomDirection) {
                    remainingDirections[index++] = direction;
                }
            }

            randomIndex = random.nextInt(remainingDirections.length);
            char remainingDirection = remainingDirections[randomIndex];
            ghost.setDir(remainingDirection);
        }
    }

    public ArrayList<Coor> placeFruits(){
        return this.plateforme.placeFruits();
    }


    public void setGameOverListener(GameOverListener app){
        this.gameOverListener = app;
    }

    public void checkCollision(Ghost g){
        //System.out.println("Checking collision");
        if(g.getX()==pacman.getX() && g.getY()== pacman.getY()){
            //System.out.println("Found************************************");
            gameOverListener.gameOver(new GameOverEvent(this.score));
        }
    }

    public void checkEatFruit(int x, int y){
        if(plateforme.getCaseType(x,y)=='F'){
            plateforme.removeFruit();
            score++;
            System.out.println(score);
            plateforme.setCaseType(x,y,'V');
            this.eatFruitListener.eatFruit(new EatFruitEvent(x,y));

        }
    }

    public void setEatFruitListener(EatFruitListener e){
        this.eatFruitListener = e;
    }

    public void printCases(){
        this.plateforme.printCases();
    }
}


