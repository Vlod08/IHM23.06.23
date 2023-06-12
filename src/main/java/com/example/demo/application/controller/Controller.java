package com.example.demo.application.controller;

import com.example.demo.application.elements.Element;
import com.example.demo.application.model.Plateforme;

import java.util.ArrayList;

public class Controller {

    public Controller(Plateforme p){
        ArrayList<ArrayList<Element>> cases = p.getCases();

    }

}
