package com.example.demo.application.model;

import com.example.demo.application.elements.Element;
import com.example.demo.application.elements.mobile.Ghost;
import com.example.demo.application.elements.mobile.Pacman;
import com.example.demo.events.PostionValueChangedEvent;
import com.example.demo.events.PositionValueListener;

import java.util.ArrayList;
import java.util.Random;

import static java.lang.Math.abs;

public class Model {
    private Plateforme plateforme;
    private Pacman pacman;
    private ArrayList<Ghost> ghosts;
    private final int nbGhosts = 5;
    private PositionValueListener pacmanPostionListener;

    private ArrayList<PositionValueListener> ghostsPostionListener;


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
        }
        /*for(int i =0; i<5;i++){
            System.out.println("Printing in the model : "+this.ghosts.get(i).getX()+"  :  "+this.ghosts.get(i).getY());
        }*/

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
        //mouvementPacman();

    }

    public void mouvementPacman(){
        switch (pacman.getDir()){
            case 'R':
                if(plateforme.getCaseType(pacman.getX()+pacman.getVitesse(),pacman.getY())!='M'){
                    pacman.mouvement();
                    fireValueChangedP();
                }
                break;
            case 'L':
                if(plateforme.getCaseType(pacman.getX()-pacman.getVitesse(),pacman.getY())!='M'){
                    pacman.mouvement();
                    fireValueChangedP();
                }
                break;
            case 'U':
                if(plateforme.getCaseType(pacman.getX(),pacman.getY()-pacman.getVitesse())!='M'){
                    pacman.mouvement();
                    fireValueChangedP();
                }
                break;
            case 'D':
                if(plateforme.getCaseType(pacman.getX(),pacman.getY()+pacman.getVitesse())!='M'){
                    pacman.mouvement();
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
            for (int i = 0; i < ghostsPostionListener.size(); i++) {
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
        boolean rand = true;
        char[] directions = {'U', 'D', 'R', 'L'};


        switch (ghost.getDir()) {
            case 'U':
                if (plateforme.getCaseType(ghost.getX(), ghost.getY() - ghost.getVitesse()) != 'M') {
                    ghost.setY(ghost.getY() - ghost.getVitesse());
                    rand = false;
                }
                break;
            case 'D':
                if (plateforme.getCaseType(ghost.getX(), ghost.getY() + ghost.getVitesse()) != 'M') {
                    ghost.setY(ghost.getY() + ghost.getVitesse());
                    rand = false;
                }
                break;
            case 'R':
                if (plateforme.getCaseType(ghost.getX() + ghost.getVitesse(), ghost.getY()) != 'M') {
                    ghost.setX(ghost.getX() + ghost.getVitesse());
                    rand = false;
                }
                break;
            case 'L':
                if (plateforme.getCaseType(ghost.getX() - ghost.getVitesse(), ghost.getY()) != 'M') {
                    ghost.setX(ghost.getX() - ghost.getVitesse());
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

}


