package com.example.demo.application.view;

import com.example.demo.application.controller.Controller;
import com.example.demo.application.elements.mobile.Pacman;

public class GameView {

    private Murs murs;
    private Pacman pacman;
    private Controller controller;

    public GameView(Controller controller){
        this.controller = controller;
        murs = controller.MettreAJourMurs();
        pacman = controller.MettreAJourPacman();

    }

    //private Ghosts ghosts;


}
