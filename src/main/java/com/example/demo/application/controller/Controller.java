package com.example.demo.application.controller;

import com.example.demo.application.elements.Element;
import com.example.demo.application.model.Plateforme;
import com.example.demo.application.view.GameView;


import java.util.ArrayList;

public class Controller {
    Plateforme p;
    GameView v;

    public Controller(Plateforme p, GameView v ){
        this.p = p;
        this.v = v;
        MettreAJourMurs()

    }

    public ArrayList<Element> MettreAJourMurs(){

    }

}
